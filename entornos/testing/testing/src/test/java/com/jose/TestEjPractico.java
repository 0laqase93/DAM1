package com.jose;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestEjPractico {

    @Test
    public void testSuma() {
        EjPractico ejPractico = new EjPractico();
        int result = ejPractico.suma(7, 5);
    
        assertEquals(12, result);
        assertNotEquals(5, result);
        assertTrue(12 == result);
    }
}
