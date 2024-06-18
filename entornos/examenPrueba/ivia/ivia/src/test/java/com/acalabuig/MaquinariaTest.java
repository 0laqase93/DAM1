package com.acalabuig;

import com.acalabuig.model.Cultivo;
import com.acalabuig.model.Maquinaria;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaquinariaTest {
    Maquinaria m = null;

    @Before
    public void setUp() {
        m = new Maquinaria("Empaquetado", 350.0, 7.0);
    }

    // Getters Maquinaria
    @Test
    public void testMaquinariaGetTipo() {
        assertNotNull(m.getTipo());
        assertEquals("Empaquetado", m.getTipo());
        assertNotEquals("SinTipo", m.getTipo());
        assertSame("Empaquetado", m.getTipo());
        assertNotSame("SinTipo", m.getTipo());
        assertTrue(m.getTipo().equals("Empaquetado"));
        assertFalse(m.getTipo().equals("SinTipo"));
    }

    @Test
    public void testMaquinariaGetHoraUso() {
        assertNotNull(m.getHoraUso());
        assertNotEquals(0.0, m.getHoraUso());
        assertNotSame(0.0, m.getHoraUso());
        assertTrue(m.getHoraUso() == 350.0);
        assertFalse(m.getHoraUso() == 0.0);
    }

    @Test
    public void testMaquinariaGetEficiencia() {
        assertNotNull(m.getEficiencia());
        assertNotEquals(0.0, m.getEficiencia());
        assertNotSame(0.0, m.getEficiencia());
        assertTrue(m.getEficiencia() == 7.0);
        assertFalse(m.getEficiencia() == 0.0);
    }

    // Setters Maquinaria
    @Test
    public void testMaquinariaSetTipo() {
        m.setTipo("Distribución");

        assertNotNull(m.getTipo());
        assertEquals("Distribución", m.getTipo());
        assertNotEquals("Empaquetado", m.getTipo());
        assertSame("Distribución", m.getTipo());
        assertNotSame("Empaquetado", m.getTipo());
        assertTrue(m.getTipo().equals("Distribución"));
        assertFalse(m.getTipo().equals("Empaquetado"));
    }

    @Test
    public void testMaquinariaSetHoraUso() {
        m.setHoraUso(30.0);

        assertNotNull(m.getHoraUso());
        assertNotEquals(350.0, m.getHoraUso());
        assertNotSame(350.0, m.getHoraUso());
        assertTrue(m.getHoraUso() == 30.0);
        assertFalse(m.getHoraUso() == 350.0);
    }

    @Test
    public void testMaquinariaSetEficiencia() {
        m.setEficiencia(10.0);

        assertNotNull(m.getEficiencia());
        assertNotEquals(7.0, m.getEficiencia());
        assertNotSame(7.0, m.getEficiencia());
        assertTrue(m.getEficiencia() == 10.0);
        assertFalse(m.getEficiencia() == 7.0);
    }

    @Test
    public void testCalcularTiempoArado() {
        Double tiempo = m.calcularTiempoArado(70);

        assertNotNull(tiempo);
        assertNotEquals(0.0, tiempo);
        assertNotSame(0.0, tiempo);
        assertTrue(tiempo == 10.0);
        assertFalse(tiempo == 0.0);
    }

    @Test
    public void testAnyadirHorasTrabajo() {
        m.anyadirHorasTrabajo(70);

        assertNotNull(m.getHoraUso());
        assertNotEquals(0.0, m.getHoraUso());
        assertNotSame(0.0, m.getHoraUso());
        assertTrue(m.getHoraUso() == 420.0);
        assertFalse(m.getHoraUso() == 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTipoMaquinariaNoVacio() {
        Maquinaria m2 = new Maquinaria(null, 350.0, 7.0);;
    }

    @Test (expected = IllegalArgumentException.class)
    public void testHorasUsoNoNegativo() {
        Maquinaria m2 = new Maquinaria("Empaquetado", -350.0, 7.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaquinariaHectareasNegativasException() {
        m.calcularTiempoArado(-350.0);
    }
}
