package org.antonio;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CancionTest {
    private Cancion cancion;

    @Before
    public void setUp() {
        cancion = new Cancion("Bohemian Rhapsody", "Queen", 355);
    }

    // Getters
    @Test
    public void testGetTitulo() {
        assertNotNull(cancion.getTitulo());
        assertEquals("Bohemian Rhapsody", cancion.getTitulo());
        assertNotEquals("Hola :)", cancion.getTitulo());
        assertSame("Bohemian Rhapsody", cancion.getTitulo());
        assertNotSame("Hola :)", cancion.getTitulo());
        assertTrue(cancion.getTitulo().equals("Bohemian Rhapsody"));
        assertFalse(cancion.getTitulo().equals("Hola :)"));
    }

    @Test
    public void testGetArtista() {
        assertNotNull(cancion.getArtista());
        assertEquals("Queen", cancion.getArtista());
        assertNotEquals("Hola :)", cancion.getArtista());
        assertSame("Queen", cancion.getArtista());
        assertNotSame("Hola :)", cancion.getArtista());
        assertTrue(cancion.getArtista().equals("Queen"));
        assertFalse(cancion.getArtista().equals("Hola :)"));
    }

    @Test
    public void testGetDuracion() {
        assertNotNull(cancion.getDuracion());
        assertEquals(355, cancion.getDuracion());
        assertNotEquals(0, cancion.getDuracion());
        assertNotSame(0, cancion.getDuracion());
        assertTrue(cancion.getDuracion() == 355);
        assertFalse(cancion.getDuracion() < 355);
    }

    // Setters
    @Test
    public void testSetTitulo() {
        cancion.setTitulo("Caballo homosexual de las montañas");

        assertNotNull(cancion.getTitulo());
        assertEquals("Caballo homosexual de las montañas", cancion.getTitulo());
        assertNotEquals("Bohemian Rhapsody", cancion.getTitulo());
        assertSame("Caballo homosexual de las montañas", cancion.getTitulo());
        assertNotSame("Bohemian Rhapsody", cancion.getTitulo());
        assertTrue(cancion.getTitulo().equals("Caballo homosexual de las montañas"));
        assertFalse(cancion.getTitulo().equals("Bohemian Rhapsody"));
    }

    @Test
    public void testSetArtista() {
        cancion.setArtista("Robe");

        assertNotNull(cancion.getArtista());
        assertEquals("Robe", cancion.getArtista());
        assertNotEquals("Queen", cancion.getArtista());
        assertSame("Robe", cancion.getArtista());
        assertNotSame("Queen", cancion.getArtista());
        assertTrue(cancion.getArtista().equals("Robe"));
        assertFalse(cancion.getArtista().equals("Queen"));
    }

    @Test
    public void testSetDuracion() {
        cancion.setDuracion(314);

        assertNotNull(cancion.getDuracion());
        assertEquals(314, cancion.getDuracion());
        assertNotEquals(355, cancion.getDuracion());
        assertNotSame(355, cancion.getDuracion());
        assertTrue(cancion.getDuracion() == 314);
        assertFalse(cancion.getDuracion() < 314);
    }
}
