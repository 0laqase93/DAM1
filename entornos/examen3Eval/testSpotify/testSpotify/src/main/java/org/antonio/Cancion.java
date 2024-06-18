package org.antonio;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Canción que almacena datos de una canción tales como: Título, Artista y Duración.
 * @version 1.0
 */
class Cancion {
    private String titulo;
    private String artista;
    private int duracion;

    /**
     * Constructor básico de la Canción, hay que proporcionar un nombre de la Canción, el artista y la duración.
     * @param titulo Título de la Canción.
     * @param artista Artista de la Canción.
     * @param duracion Duración de la Canción.
     */
    public Cancion(String titulo, String artista, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
    }

    // Métodos getter y setter para los atributos
    /**
     * Devuelve el título de la Canción.
     * @return Título de la Canción.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la Canción por uno nuevo dado.
     * @param titulo Nuevo título.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Devuelve el artista de la Canción.
     * @return Artista de la Canción.
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Establece el artista de la Canción por uno nuevo dado.
     * @param artista Nuevo artista.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Devuelve la duración de la Canción.
     * @return Duración de la Canción.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración de la Canción por uno nuevo dado.
     * @param duracion Nueva duración.
     * @since JDK 1.7
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
