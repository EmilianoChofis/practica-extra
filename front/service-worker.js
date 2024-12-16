// Name of the cache storage for the PWA
const CACHE_NAME = 'pwa-task-manager';

// List of resources to cache for offline functionality
const urlsToCache = [
    './',
    'index.html',
    'service-worker.js',
    'manifest.json',
    'offline.html',
    'images/image.png',
    // External CDN resources
    'https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css',
    'https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js',
    'https://cdn.jsdelivr.net/npm/sweetalert2@11',
    'https://cdnjs.cloudflare.com/ajax/libs/pouchdb/7.0.0/pouchdb.min.js'
];

// Install event - Caches all required resources when SW is installed
self.addEventListener('install', event => {
    event.waitUntil(
        caches.open(CACHE_NAME)
            .then(cache => {
                return cache.addAll(urlsToCache);
            })
    );
});

// Activate event - Cleans up old caches when SW is activated
self.addEventListener('activate', event => {
    event.waitUntil(
        caches.keys().then(cacheNames => Promise.all(
            cacheNames.filter(cacheName => {
                return cacheName !== CACHE_NAME
            }).map(cacheName => caches.delete(cacheName))
        ))
    );
});

// Helper function to check online status
function isOnline() {
    return navigator.onLine;
}

// Fetch event - Handles network requests with cache-first strategy when offline
self.addEventListener('fetch', (event) => {
    event.respondWith(
        caches.match(event.request)
            .then((cachedResponse) => {
                // If online, try network first
                if(isOnline()){
                    return fetch(event.request);
                }
                // If offline and resource is in cache, return cached version
                if (cachedResponse) {
                    return cachedResponse;
                }
                // If resource not in cache, show offline page
                return caches.match('/offline.html');
            })
            .catch(() => {
                // On network error, show offline page
                return caches.match('/offline.html');
            })
    );
});

// Monitors online status and updates cache when connection is restored
function monitorOnlineStatus() {
    setInterval(() => {
        if (isOnline()) {
            caches.open(CACHE_NAME)
                .then(cache => {
                    return cache.keys().then(keys => {
                        if (keys.length === 0) {
                            return cache.addAll(urlsToCache);
                        }
                    });
                })
        }
    }, 500);
}

// Start monitoring online status
monitorOnlineStatus();
