package org.antonio;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBatalla {

    Luffy luffy = null;
    Zoro zoro = null;

    @Before
    public void setUp() {
        luffy = new Luffy("Monkey D. Luffy", 100);
        zoro = new Zoro("Roronoa Zoro", 80);
    }

    @Test
    public void testAtaqueLuffy() {
        luffy.atacar(zoro);

        assertNotNull(zoro.getPoder());
        assertEquals(-20, zoro.getPoder());
        assertTrue(zoro.getPoder() == -20);
        assertSame(-20, zoro.getPoder());
    }

    @Test
    public void testAtaqueZoro() {
        zoro.atacar(luffy);

        assertNotNull(luffy.getPoder());
        assertEquals(20, luffy.getPoder());
        assertTrue(luffy.getPoder() == 20);
        assertSame(20, luffy.getPoder());
    }
}
