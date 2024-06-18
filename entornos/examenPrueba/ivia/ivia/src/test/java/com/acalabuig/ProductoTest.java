package com.acalabuig;

import com.acalabuig.model.Maquinaria;
import com.acalabuig.model.Producto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ProductoTest {
    Producto p = null;

    @Before
    public void setUp() throws Exception {
        p = new Producto("Zanahoria", 0.15);
    }

    // Getters Producto
    @Test
    public void testProductoGetNombre() {
        assertNotNull(p.getNombre());
        assertEquals("Zanahoria", p.getNombre());
        assertNotEquals("SinNombre", p.getNombre());
        assertSame("Zanahoria", p.getNombre());
        assertNotSame("SinNombre", p.getNombre());
        assertTrue(p.getNombre().equals("Zanahoria"));
        assertFalse(p.getNombre().equals("SinNombre"));
    }

    @Test
    public void testProductoGetPrecio() {
        assertNotNull(p.getPrecio());
        assertNotEquals(0.0, p.getPrecio());
        assertNotSame(0.0, p.getPrecio());
        assertTrue(p.getPrecio() == 0.15);
        assertFalse(p.getPrecio() == 0.0);
    }

    // Setters Producto
    @Test
    public void testProductoSetNombre() {
        p.setNombre("Manzana");

        assertNotNull(p.getNombre());
        assertEquals("Manzana", p.getNombre());
        assertNotEquals("Zanahoria", p.getNombre());
        assertSame("Manzana", p.getNombre());
        assertNotSame("Zanahoria", p.getNombre());
        assertTrue(p.getNombre().equals("Manzana"));
        assertFalse(p.getNombre().equals("Zanahoria"));
    }

    @Test
    public void testProductoSetPrecio() {
        p.setPrecio(0.20);

        assertNotNull(p.getPrecio());
        assertNotEquals(0.15, p.getPrecio());
        assertNotSame(0.15, p.getPrecio());
        assertTrue(p.getPrecio() == 0.20);
        assertFalse(p.getPrecio() == 0.15);
    }

    @Test
    public void testCalcularPrecioConDescuento() {
        Double precio = p.calcularPrecioConDescuento(50);

        assertNotNull(precio);
        assertNotEquals(0.0, precio);
        assertNotSame(0.0, precio);
        assertTrue(precio == 0.075);
        assertFalse(precio == 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNombreProductoNoVacio() {
        Producto p2 = new Producto(null, 0.15);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPrecioDebeSerPositivo() {
        Producto p2 = new Producto("Zanahoria", -0.15);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testProductoDescuentoInvalidoPorAbajoException() {
        Double precio = p.calcularPrecioConDescuento(-10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testProductoDescuentoInvalidoPorArribaException() {
        Double precio = p.calcularPrecioConDescuento(150);
    }
}
