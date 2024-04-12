package org.antonio;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.Rule;

public class CalculatorTest {

    public Calculator calc = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calc.add(3, 2));
        assertEquals(-1, calc.add(-3, 2));
        assertNotEquals(0, calc.add(1, 0));
        assertNotEquals(0, calc.add(-40, 0));
        assertTrue((calc.add(-3, 3)) == 0);
        assertFalse((calc.add(-100, 150)) == 0);
    }

    @Test
    public void testSubstract() {
        assertEquals(1, calc.subtract(3, 2));
        assertNotEquals(0, calc.subtract(1, 0));
        assertTrue((calc.subtract(-3, 3)) == -6);
        assertFalse((calc.subtract(-100, 150)) == 0);
    }

    @Test
    public void testMultily() {
        assertEquals(6, calc.multiply(3, 2));
        assertNotEquals(1, calc.multiply(1, 0));
        assertTrue((calc.multiply(-3, 3)) == -9);
        assertFalse((calc.multiply(-100, 150)) == 0);
    }

    @Test
    public void testDivide() {
        assertEquals(1, calc.divide(3, 3), 3);
        assertNotEquals(1, calc.divide(1, 10));
        assertTrue((calc.divide(-3, 3)) == -1);
        assertFalse((calc.divide(-100, 150)) == 0);
    }

    @Test
    public void testDivideByZero1() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.divide(10, 0);
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero2() {
        calc.divide(-10, 0);
    }

    @Rule 
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testDivideByZero3() {
        exceptionRule.expect(IllegalArgumentException.class);
        calc.divide(10, 0);
    }
}
