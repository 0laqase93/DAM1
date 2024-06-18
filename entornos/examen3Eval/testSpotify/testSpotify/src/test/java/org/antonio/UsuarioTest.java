package org.antonio;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class UsuarioTest {
    private Usuario usuario;
    private Playlist playlist1;
    private Playlist playlist2;

    @Before
    public void setUp() {
        usuario = new Usuario("Juan");
        playlist1 = new Playlist("Mis Favoritas");
        playlist2 = new Playlist("Rock Clásico");
    }

    // Getters
    @Test
    public void testGetNombre() {
        assertNotNull(usuario.getNombre());
        assertEquals("Juan", usuario.getNombre());
        assertNotEquals("Hola :)", usuario.getNombre());
        assertSame("Juan", usuario.getNombre());
        assertNotSame("Hola :)", usuario.getNombre());
        assertTrue(usuario.getNombre().equals("Juan"));
        assertFalse(usuario.getNombre().equals("Hola :)"));
    }

    // Setters
    @Test
    public void testSetNombre() {
        usuario.setNombre("Paco");

        assertNotNull(usuario.getNombre());
        assertEquals("Paco", usuario.getNombre());
        assertNotEquals("Juan", usuario.getNombre());
        assertSame("Paco", usuario.getNombre());
        assertNotSame("Juan", usuario.getNombre());
        assertTrue(usuario.getNombre().equals("Paco"));
        assertFalse(usuario.getNombre().equals("Juan"));
    }

    @Test
    public void testAgregarPlaylist() {
        usuario.agregarPlaylist(playlist1);

        Playlist playlistGuardada = usuario.getPlaylists().get(0);

        assertNotNull(usuario.getPlaylists());
        assertEquals("Mis Favoritas", playlistGuardada.getNombre());
        assertNotEquals("Rock Clásico", playlistGuardada.getNombre());
        assertSame(0, playlistGuardada.getDuracionTotal());
        assertNotSame(200, playlistGuardada.getDuracionTotal());

        assertTrue(usuario.getPlaylists().size() == 1);
        assertFalse(usuario.getPlaylists().isEmpty());
    }

    @Test
    public void testEliminarPlaylist() {
        usuario.agregarPlaylist(playlist1);
        usuario.eliminarPlaylist(playlist1);

        assertNotNull(usuario.getPlaylists());
        assertArrayEquals(new Playlist[]{}, usuario.getPlaylists().toArray());

        assertTrue(usuario.getPlaylists().isEmpty());
        assertFalse(usuario.getPlaylists().size() == 1);
    }

    @Test
    public void testMostrarCanciones() {
        usuario.agregarPlaylist(playlist1);
        usuario.agregarPlaylist(playlist2);

        String playListEsperadas = "Playlists de 'Juan':" +
                                   "- Mis Favoritas" +
                                   "- Rock Clásico";
        String cancionesGuardadas = usuario.mostrarPlaylists();

        assertNotNull(cancionesGuardadas);
        assertEquals(playListEsperadas, cancionesGuardadas);
        assertNotEquals("Hola :)", cancionesGuardadas);
        assertNotSame("Hola :)", cancionesGuardadas);
        assertTrue(cancionesGuardadas.equals(playListEsperadas));
        assertFalse(cancionesGuardadas.equals("Hola :)"));
    }

    @Test
    public void testBuscarCancionEnPlaylists() {
        Cancion cancionParaAgregar1 = new Cancion("Bohemian Rhapsody", "Queen", 355);
        Cancion cancionParaAgregar2 = new Cancion("Cancion", "Artista", 132);
        playlist1.agregarCancion(cancionParaAgregar1);
        playlist2.agregarCancion(cancionParaAgregar2);
        usuario.agregarPlaylist(playlist1);
        usuario.agregarPlaylist(playlist2);
        String cancionGuardada1 = usuario.buscarCancionEnPlaylists("Bohemian Rhapsody");
        String cancionGuardada2 = usuario.buscarCancionEnPlaylists("Cancion");

        assertNotNull(cancionGuardada1);
        assertNotNull(cancionGuardada2);
        assertTrue(cancionGuardada1.contains("Bohemian Rhapsody"));
        assertTrue(cancionGuardada1.contains("Queen"));
        assertTrue(cancionGuardada2.contains("Cancion"));
    }
}
