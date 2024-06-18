package com.acalabuig;

import com.acalabuig.model.Empleado;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class EmpleadoTest {
    Empleado e = null;

    @Before
    public void setUp() {
        e = new Empleado("Paco", "Encargado", 1200.0);
    }

    // Getters Empleado
    @Test
    public void testEmpleadoGetNombre() {
        assertNotNull(e.getNombre());
        assertEquals("Paco", e.getNombre());
        assertNotEquals("SinNombre", e.getNombre());
        assertSame("Paco", e.getNombre());
        assertNotSame("SinNombre", e.getNombre());
        assertTrue(e.getNombre().equals("Paco"));
        assertFalse(e.getNombre().equals("SinNombre"));
    }

    @Test
    public void testEmpleadoGetCargo() {
        assertNotNull(e.getCargo());
        assertEquals("Encargado", e.getCargo());
        assertNotEquals("SinCargo", e.getCargo());
        assertSame("Encargado", e.getCargo());
        assertNotSame("SinCargo", e.getCargo());
        assertTrue(e.getCargo().equals("Encargado"));
        assertFalse(e.getCargo().equals("SinCargo"));
    }

    @Test
    public void testEmpleadoGetSalario() {
        assertNotNull(e.getSalario());
        assertNotEquals(0.0, e.getSalario());
        assertNotSame(0.0, e.getSalario());
        assertTrue(e.getSalario() == 1200.0);
        assertFalse(e.getSalario() == 0.0);
    }

    // Setters Empleado
    @Test
    public void testEmpleadoSetNombre() {
        e.setNombre("Antonio");

        assertNotNull(e.getNombre());
        assertEquals("Antonio", e.getNombre());
        assertNotEquals("Paco", e.getNombre());
        assertSame("Antonio", e.getNombre());
        assertNotSame("Paco", e.getNombre());
        assertTrue(e.getNombre().equals("Antonio"));
        assertFalse(e.getNombre().equals("Paco"));
    }

    @Test
    public void testEmpleadoSetCargo() {
        e.setCargo("Limpieza");

        assertNotNull(e.getCargo());
        assertEquals("Limpieza", e.getCargo());
        assertNotEquals("Encargado", e.getCargo());
        assertSame("Limpieza", e.getCargo());
        assertNotSame("Encargado", e.getCargo());
        assertTrue(e.getCargo().equals("Limpieza"));
        assertFalse(e.getCargo().equals("Encargado"));
    }

    @Test
    public void testEmpleadoSetSalario() {
        e.setSalario(1000.0);

        assertNotNull(e.getSalario());
        assertNotEquals(1200.0, e.getSalario());
        assertNotSame(1200.0, e.getSalario());
        assertTrue(e.getSalario() == 1000.0);
        assertFalse(e.getSalario() == 1200.0);
    }

    @Test
    public void testCalculaSalarioAnual() {
        Double ingresos = e.calculaSalarioAnual();

        assertNotNull(ingresos);
        assertNotEquals(1200., ingresos);
        assertNotSame(1200.0, ingresos);
        assertTrue(ingresos == 14400.0);
        assertFalse(ingresos == 1200.0);
    }

    @Test
    public void testDarAumento() {
        e.darAumento(10.0);

        assertNotNull(e.getSalario());
        assertNotEquals(1200., e.getSalario());
        assertNotSame(1200.0, e.getSalario());
        assertTrue(e.getSalario() == 1320.0);
        assertFalse(e.getSalario() == 1200.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNombreEmpleadoNoVacio() {
        Empleado e2 = new Empleado(null, "Organizador", 1000.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSalarioDebeSerPositivo() {
        Empleado e2 = new Empleado("Manolo", "Organizador", -1000.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmpleadoAumentoNegativoException() {
        e.darAumento(-10);
    }
}
