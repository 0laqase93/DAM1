package org.antonio;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Playlist que contiene una lista de canciones, se identifica la PlayList con un nombre.
 * @version 1.0
 */
class Playlist {
    private String nombre;
    private List<Cancion> canciones;

    /**
     * Constructor básico de la PlayList, hay que proporcionar un nombre de la PlayList.
     * @param nombre Nombre de la PlayList.
     */
    public Playlist(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    /**
     * Devuelve el nombre de la PlayList.
     * @return Nombre de la PlayList.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la PlayList por uno nuevo dado.
     * @param nombre Nuevo nombre de la PlayList.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la lista de canciones de la PlayList.
     * @return Lista de canciones de la PlayList.
     */
    public List<Cancion> getCanciones() {
        return canciones;
    }

    /**
     * Función que añade una canción a la lista de la PlayList.
     * @param cancion Canción para añadir.
     */
    public void agregarCancion(Cancion cancion) {
        canciones.add(cancion);
    }

    /**
     * Función que elimina una canción de la lista de la PlayList.
     * @param cancion Canción para eliminar.
     */
    public void eliminarCancion(Cancion cancion) {
        canciones.remove(cancion);
    }

    /**
     * Función que calcula y devuelve la suma total de duración en segundos de la PlayList.
     * @return Duración total de la PlayList.
     */
    public int getDuracionTotal() {
        int duracionTotal = 0;
        for (Cancion cancion : canciones) {
            duracionTotal += cancion.getDuracion();
        }
        return duracionTotal;
    }

    /**
     * Muestra por la consola y devuelve una cadena con las canciones de la PlayList con un formato específico (Título y artista).
     * @return Cadena con la lista de canciones de la PlayList.
     * @since JDK 1.7
     */
    public String mostrarCanciones() {
        System.out.println("Canciones en la playlist '" + nombre + "':");
        String salida_canciones = "Canciones en la playlist '" + nombre + "':";
        for (Cancion cancion : canciones) {
            System.out.println("- " + cancion.getTitulo() + " (" + cancion.getArtista() + ")");
            salida_canciones += "- " + cancion.getTitulo() + " (" + cancion.getArtista() + ")";
        }

        return salida_canciones;
    }
}
