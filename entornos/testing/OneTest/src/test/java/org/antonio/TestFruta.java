package org.antonio;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestFruta {

    @Test
    public void testPuedoComerFrutaDeliciosa() {
        Fruta frutaBuena = new Fruta("Manzana", true);

        assertTrue(frutaBuena.puedoComer());
    }

    @Test
    public void testPuedoComerFrutaDelDiablo() {
        Fruta frutaDevil = new Fruta("Fruta del Diablo", true);

        assertFalse(frutaDevil.puedoComer());
    }

    @Test
    public void testPuedoComerFrutaNoDeliciosa() {
        Fruta frutaMala = new Fruta("Durian", false);

        assertFalse(frutaMala.puedoComer());
    }
}
