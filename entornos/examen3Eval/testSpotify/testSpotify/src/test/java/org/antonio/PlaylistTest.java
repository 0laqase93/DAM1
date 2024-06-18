package org.antonio;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class PlaylistTest {
    private Cancion cancion1;
    private Cancion cancion2;
    private Cancion cancion3;
    private Playlist playlist;

    @Before
    public void setUp() {
        cancion1 = new Cancion("Bohemian Rhapsody", "Queen", 355);
        cancion2 = new Cancion("Imagine", "John Lennon", 180);
        cancion3 = new Cancion("Hotel California", "Eagles", 390);
        playlist = new Playlist("Mis Favoritas");
    }

    // Getters
    @Test
    public void testGetNombre() {
        assertNotNull(playlist.getNombre());
        assertEquals("Mis Favoritas", playlist.getNombre());
        assertNotEquals("Hola :)", playlist.getNombre());
        assertSame("Mis Favoritas", playlist.getNombre());
        assertNotSame("Hola :)", playlist.getNombre());
        assertTrue(playlist.getNombre().equals("Mis Favoritas"));
        assertFalse(playlist.getNombre().equals("Hola :)"));
    }

    // Setters
    @Test
    public void testSetNombre() {
        playlist.setNombre("Mejores canciones 2024");

        assertNotNull(playlist.getNombre());
        assertEquals("Mejores canciones 2024", playlist.getNombre());
        assertNotEquals("Mis Favoritas", playlist.getNombre());
        assertSame("Mejores canciones 2024", playlist.getNombre());
        assertNotSame("Mis Favoritas", playlist.getNombre());
        assertTrue(playlist.getNombre().equals("Mejores canciones 2024"));
        assertFalse(playlist.getNombre().equals("Mis Favoritas"));
    }

    @Test
    public void testAgregarCancion() {
        playlist.agregarCancion(cancion1);
        Cancion cancionGuardada = playlist.getCanciones().get(0);


        assertNotNull(cancionGuardada);
        assertEquals(cancion1.getTitulo(), cancionGuardada.getTitulo());
        assertNotEquals("Hola :)", cancionGuardada.getArtista());
        assertSame(cancion1, cancionGuardada);
        assertNotSame(cancion2, cancion1);

        assertTrue(playlist.getCanciones().contains(cancion1));
        assertFalse(playlist.getCanciones().isEmpty());
    }

    @Test
    public void testEliminarCancion() {
        playlist.agregarCancion(cancion1);
        playlist.eliminarCancion(cancion1);

        assertNotNull(playlist.getCanciones());
        assertEquals(0, playlist.getCanciones().size());
        assertNotEquals(3, playlist.getCanciones().size());
        assertArrayEquals(new Cancion[]{}, playlist.getCanciones().toArray());

        assertTrue(playlist.getCanciones().isEmpty());
        assertFalse(playlist.getCanciones().contains(cancion1));
    }

    @Test
    public void testGetDuracionTotal() {
        playlist.agregarCancion(cancion1);
        playlist.agregarCancion(cancion3);
        int duracionCalculada = playlist.getDuracionTotal();

        assertNotNull(duracionCalculada);
        assertEquals(745, duracionCalculada);
        assertNotEquals(0, duracionCalculada);
        assertNotSame(0, duracionCalculada);
        assertTrue(duracionCalculada == 745);
        assertFalse(duracionCalculada < 745);
    }

    @Test
    public void testMostrarCanciones() {
        playlist.agregarCancion(cancion1);
        playlist.agregarCancion(cancion2);
        playlist.agregarCancion(cancion3);

        String cancionesEsperadas = "Canciones en la playlist 'Mis Favoritas':" +
                                    "- Bohemian Rhapsody (Queen)" +
                                    "- Imagine (John Lennon)" +
                                    "- Hotel California (Eagles)";
        String cancionesGuardadas = playlist.mostrarCanciones();

        assertNotNull(cancionesGuardadas);
        assertEquals(cancionesEsperadas, cancionesGuardadas);
        assertNotEquals("Hola :)", cancionesGuardadas);
        assertNotSame("Hola :)", cancionesGuardadas);
        assertTrue(cancionesGuardadas.equals(cancionesEsperadas));
        assertFalse(cancionesGuardadas.equals("Hola :)"));
    }
}
