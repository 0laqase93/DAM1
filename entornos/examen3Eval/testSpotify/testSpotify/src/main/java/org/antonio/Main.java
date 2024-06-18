package org.antonio;

/**
 * Clase principal donde se ejecuta el programa y se relacionan todas las demás clases.
 * @version 1.0
 */
public class Main {
    /**
     * Método main que es la raíz del programa general, ya que es lo primero que ese ejecuta.
     * @param args Argumentos que se le pueden pasar al programa para que reciba.
     */
    public static void main(String[] args) {
        // Ejemplo de uso
        Usuario usuario = new Usuario("Juan");

        // Crear playlists
        usuario.agregarPlaylist(new Playlist("Mis Favoritas"));
        usuario.agregarPlaylist(new Playlist("Rock Clásico"));

        // Agregar canciones a la playlist "Mis Favoritas"
        Playlist misFavoritas = usuario.getPlaylists().get(0);
        Cancion cancion1 = new Cancion("Bohemian Rhapsody", "Queen", 355);
        Cancion cancion2 = new Cancion("Imagine", "John Lennon", 180);
        Cancion cancion3 = new Cancion("Hotel California", "Eagles", 390);
        misFavoritas.agregarCancion(cancion1);
        misFavoritas.agregarCancion(cancion2);
        misFavoritas.agregarCancion(cancion3);

        // Agregar canciones a la playlist "Rock Clásico"
        Playlist rockClasico = usuario.getPlaylists().get(1);
        Cancion cancion4 = new Cancion("Stairway to Heaven", "Led Zeppelin", 480);
        Cancion cancion5 = new Cancion("Paint It Black", "The Rolling Stones", 230);
        rockClasico.agregarCancion(cancion4);
        rockClasico.agregarCancion(cancion5);

        // Mostrar playlists del usuario
        usuario.mostrarPlaylists();

        // Mostrar canciones en la playlist "Mis Favoritas"
        misFavoritas.mostrarCanciones();

        // Eliminar canción "Imagine" de la playlist "Mis Favoritas"
        misFavoritas.eliminarCancion(cancion2);

        // Mostrar canciones en la playlist "Mis Favoritas" después de eliminar "Imagine"
        misFavoritas.mostrarCanciones();

        // Obtener duración total de la playlist "Mis Favoritas"
        int duracionTotal = misFavoritas.getDuracionTotal();
        System.out.println("Duración total de la playlist 'Mis Favoritas': " + duracionTotal + " segundos");

        // Buscar canción "Hotel California" en las playlists del usuario
        usuario.buscarCancionEnPlaylists("Hotel California");
    }
}