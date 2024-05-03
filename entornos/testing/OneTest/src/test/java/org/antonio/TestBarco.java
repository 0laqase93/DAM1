package org.antonio;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBarco {
    Luffy luffy = null;
    Zoro zoro = null;

    @Before
    public void setUp() {
        luffy = new Luffy("Monkey D. Luffy", 100);
        zoro = new Zoro("Roronoa Zoro", 100);
    }

    @Test
    public void testAgregarTripulante() {
        Barco barco = new Barco("Thousand Sunny", 5);

        assertTrue(barco.agregarTripulante());
        assertEquals(6, barco.getCapacidad());
    }

    @Test
    public void testAgregarTripulanteMaximo() {
        Barco barco = new Barco("Going Merry", 10);

        assertFalse(barco.agregarTripulante());
        assertEquals(10, barco.getCapacidad());
    }
}
