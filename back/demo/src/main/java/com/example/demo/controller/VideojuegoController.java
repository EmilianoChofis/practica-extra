package com.example.demo.controller;

import com.example.demo.model.Videojuego;
import com.example.demo.service.VideojuegoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
@CrossOrigin(origins = {"*"})
public class VideojuegoController {
    @Autowired
    VideojuegoService videojuegoService;

    @GetMapping
    public List<Videojuego> findAll() {
        return videojuegoService.findAll();
    }

    @PostMapping
    public Videojuego save(@RequestBody VideojuegoDto videojuego) {
        return videojuegoService.save(videojuego.toVideojuego());
    }

    @PutMapping
    public Videojuego update(@RequestBody Videojuego videojuego) {
        return videojuegoService.update(videojuego);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return videojuegoService.deleteById(id);
    }


}
