package com.acalabuig;

import com.acalabuig.model.Cultivo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class CultivoTest {
    Cultivo c = null;

    @Before
    public void setUp() {
        c = new Cultivo("Nombre", 10.5, 2.0);
    }

    // Getters Cultivo
    @Test
    public void testCultivoGetNombre() {
        assertNotNull(c.getNombre());
        assertEquals("Nombre", c.getNombre());
        assertNotEquals("SinNombre", c.getNombre());
        assertSame("Nombre", c.getNombre());
        assertNotSame("SinNombre", c.getNombre());
        assertTrue(c.getNombre().equals("Nombre"));
        assertFalse(c.getNombre().equals("SinNombre"));
    }

    @Test
    public void testCultivoGetArea() {
        assertNotNull(c.getArea());
        assertNotEquals(0.0, c.getArea());
        assertNotSame(0.0, c.getArea());
        assertTrue(c.getArea() == 10.5);
        assertFalse(c.getArea() == 0.0);
    }

    @Test
    public void testCultivoGetRendimiento() {
        assertNotNull(c.getRendimiento());
        assertNotEquals(0.0, c.getRendimiento());
        assertNotSame(0.0, c.getRendimiento());
        assertTrue(c.getRendimiento() == 2.0);
        assertFalse(c.getRendimiento() == 0.0);
    }

    // Setters Cultivo
    @Test
    public void testCultivoSetNombre() {
        c.setNombre("NuevoNombre");

        assertNotNull(c.getNombre());
        assertEquals("NuevoNombre", c.getNombre());
        assertNotEquals("Nombre", c.getNombre());
        assertSame("NuevoNombre", c.getNombre());
        assertNotSame("Nombre", c.getNombre());
        assertTrue(c.getNombre().equals("NuevoNombre"));
        assertFalse(c.getNombre().equals("Nombre"));
    }

    @Test
    public void testCultivoSetArea() {
        c.setArea(20.5);

        assertNotNull(c.getArea());
        assertNotEquals(10.5, c.getArea());
        assertNotSame(10.5, c.getArea());
        assertTrue(c.getArea() == 20.5);
        assertFalse(c.getArea() == 10.5);
    }

    @Test
    public void testCultivoSetRendimiento() {
        c.setRendimiento(20.0);

        assertNotNull(c.getArea());
        assertNotEquals(2.0, c.getRendimiento());
        assertNotSame(2.0, c.getRendimiento());
        assertTrue(c.getRendimiento() == 20.0);
        assertFalse(c.getRendimiento() == 2.0);
    }

    @Test
    public void testCalcularRendimientoTotal() {
        Double rendimiento = c.calcularRendimientoTotal();

        assertNotNull(rendimiento);
        assertNotEquals(10.5, rendimiento);
        assertNotSame(10.5, rendimiento);
        assertTrue(rendimiento == 21.0);
        assertFalse(rendimiento == 10.5);
    }

    @Test
    public void testCalcularIngresos() {
        Double ingresos = c.calcularIngresos(10.0);

        assertNotNull(ingresos);
        assertNotEquals(100.5, ingresos);
        assertNotSame(100.5, ingresos);
        assertTrue(ingresos == 210.0);
        assertFalse(ingresos == 100.5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNombreCultivoNoPuedeSerVacio() {
        Cultivo c2 = new Cultivo(null, 20.0, 8.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAreaDebeSerPositivo() {
        Cultivo c2 = new Cultivo("Campo", -20.0, 8.0);
    }
}
