package com.example.demo.service;

import com.example.demo.model.Videojuego;
import com.example.demo.model.VideojuegoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VideojuegoService {

    @Autowired
    VideojuegoRepository videojuegoRepository;

    @Transactional(rollbackFor = Exception.class)
    public String deleteById(Long id) {
        Optional<Videojuego> videojuegoOptional = videojuegoRepository.findById(id);
        if (videojuegoOptional.isEmpty()) {
            return "El videojuego no existe";
        }
        videojuegoRepository.deleteById(id);
        return "Videojuego eliminado correctamente";
    }

    @Transactional(rollbackFor = Exception.class)
    public Videojuego save(Videojuego videojuego) {

        return videojuegoRepository.save(videojuego);
    }

    @Transactional(rollbackFor = Exception.class)
    public Videojuego update(Videojuego videojuego) {
        Optional<Videojuego> videojuegoOptional = videojuegoRepository.findById(videojuego.getId());
        if (videojuegoOptional.isEmpty()) {
            return null;
        }
        return videojuegoRepository.save(videojuego);
    }

    public Videojuego findById(Long id) {
        return videojuegoRepository.findById(id).orElse(null);
    }

    public List<Videojuego> findAll() {
        return videojuegoRepository.findAll();
    }

}
