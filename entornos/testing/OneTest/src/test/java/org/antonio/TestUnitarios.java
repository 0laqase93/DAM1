package org.antonio;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnitarios {

    Luffy luffy = null;
    Zoro zoro = null;
    Fruta fruta = null;
    Barco barco = null;

    @Before
    public void setUp() {
        luffy = new Luffy("Luffy", 100);
        zoro = new Zoro("Zoro", 100);
        fruta = new Fruta("Manzana", true);
        barco = new Barco("Barco", 10);
    }

    @Test
    public void testLuffyGetNombre() {
        assertNotNull(luffy.getNombre());
        assertEquals("Luffy", luffy.getNombre());
        assertTrue(luffy.getNombre().equals("Luffy"));
        assertSame("Luffy", luffy.getNombre());
    }

    @Test
    public void testLuffyGetPoder() {
        assertNotNull(luffy.getPoder());
        assertEquals(100, luffy.getPoder());
        assertTrue(luffy.getPoder() == 100);
        assertSame(100, luffy.getPoder());
    }

    @Test
    public void testZoroGetNombre() {
        assertNotNull(zoro.getNombre());
        assertEquals("Zoro", zoro.getNombre());
        assertTrue(zoro.getNombre().equals("Zoro"));
        assertSame("Zoro", zoro.getNombre());
    }

    @Test
    public void testZoroGetPoder() {
        assertNotNull(zoro.getPoder());
        assertEquals(100, zoro.getPoder());
        assertTrue(zoro.getPoder() == 100);
        assertSame(100, zoro.getPoder());
    }

    @Test
    public void testFrutaGetNombre() {
        assertNotNull(fruta.getNombre());
        assertEquals("Manzana", fruta.getNombre());
        assertTrue(fruta.getNombre().equals("Manzana"));
        assertSame("Manzana", fruta.getNombre());
    }

    @Test
    public void testFrutaEsDeliciosa() {
        assertNotNull(fruta.esDeliciosa());
        assertEquals(true, fruta.esDeliciosa());
        assertTrue(fruta.esDeliciosa());
        assertSame(true, fruta.esDeliciosa());
    }

    @Test
    public void testFrutaPuedoComer() {
        assertNotNull(fruta.puedoComer());
        assertEquals(true, fruta.puedoComer());
        assertTrue(fruta.puedoComer());
        assertSame(true, fruta.puedoComer());
    }

    @Test
    public void testBarcoGetNombre() {
        assertNotNull(barco.getNombre());
        assertEquals("Barco", barco.getNombre());
        assertTrue(barco.getNombre().equals("Barco"));
        assertSame("Barco", barco.getNombre());
    }

    @Test
    public void testBarcoGetCapacidad() {
        assertNotNull(barco.getCapacidad());
        assertEquals(10, barco.getCapacidad());
        assertTrue(barco.getCapacidad() == 10);
        assertSame(10, barco.getCapacidad());
    }


}
