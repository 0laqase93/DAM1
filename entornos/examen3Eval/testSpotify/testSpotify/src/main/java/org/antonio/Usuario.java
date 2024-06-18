package org.antonio;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Usuario donde se pueden almacenar el nombre del usuario junto con una lista de PlayLists.
 * @version 1.0
 */
class Usuario {
    private String nombre;
    private List<Playlist> playlists;

    /**
     * Constructor básico del Usuario, hay que proporcionar un nombre del Usuario.
     * @param nombre Nombre del Usuario.
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.playlists = new ArrayList<>();
    }

    /**
     * Devuelve el nombre del Usuario.
     * @return Nombre del Usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del Usuario por uno nuevo dado.
     * @param nombre Nuevo nombre del Usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la lista de PlayLists del Usuario.
     * @return lista de PlayLists del Usuario.
     */
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Función para agregar PlayList a la lista de PlayList del Usuario.
     * @param playlist PlayList a agregar a la lista.
     */
    public void agregarPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    /**
     * Función para eliminar PlayList de la lista de PlayList del Usuario.
     * @param playlist PlayList a eliminar de la lista.
     */
    public void eliminarPlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    /**
     * Muestra por la consola y devuelve una cadena con las PlayLists con un formato específico (Nombre).
     * @return Cadena con la lista de las PlayLists.
     * @since JDK 1.7
     */
    public String mostrarPlaylists() {
        System.out.println("Playlists de '" + nombre + "':");
        String salida_playlists = "Playlists de '" + nombre + "':";
        for (Playlist playlist : playlists) {
            System.out.println("- " + playlist.getNombre());
            salida_playlists += "- " + playlist.getNombre();
        }

        return salida_playlists;
    }

    /**
     * Función que busca una canción entre todas las PlayLists del usuario y lo retorna en formato de cadena.
     * @param tituloCancion Nombre de la canción.
     * @return Datos de la canción en un formato específico.
     */
    public String buscarCancionEnPlaylists(String tituloCancion) {
        System.out.println("Resultados de la búsqueda de '" + tituloCancion + "' en las playlists de '" + nombre + "':");
        String salida_cancion_playlist = "Resultados de la búsqueda de '" + tituloCancion + "' en las playlists de '" + nombre + "':";
        for (Playlist playlist : playlists) {
            System.out.println("- Playlist: " + playlist.getNombre());
            System.out.println(playlist.getCanciones().size());
            for (Cancion cancion : playlist.getCanciones()) {
                if (cancion.getTitulo().equalsIgnoreCase(tituloCancion)) {
                    System.out.println("- " + cancion.getTitulo() + " (" + cancion.getArtista() + ") - Playlist: " + playlist.getNombre());
                    salida_cancion_playlist += "- " + cancion.getTitulo() + " (" + cancion.getArtista() + ") - Playlist: " + playlist.getNombre();
                }
            }
        }

        return salida_cancion_playlist;
    }
}
