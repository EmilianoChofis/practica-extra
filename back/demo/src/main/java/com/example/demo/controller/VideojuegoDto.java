package com.example.demo.controller;


import com.example.demo.model.Videojuego;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class VideojuegoDto {
    private String nombre;
    private String genero;
    private String plataforma;

    public Videojuego toVideojuego() {
        Videojuego videojuego = new Videojuego();
        videojuego.setNombre(getNombre());
        videojuego.setGenero(getGenero());
        videojuego.setPlataforma(getPlataforma());
        return videojuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
}
