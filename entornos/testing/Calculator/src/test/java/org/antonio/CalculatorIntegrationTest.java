package org.antonio;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorIntegrationTest {

    public Calculator calc = new Calculator();

    @Test
    public void testAddAndSubstract() {
        assertEquals(0, calc.add(calc.subtract(2, 3), 1));
        assertNotEquals(0, calc.add(calc.subtract(-1, 2), -3));
        assertTrue((calc.add(calc.subtract(-100, 0), 100)) == 0);
        assertFalse((calc.add(calc.subtract(19, -20),-100)) == 0);
    }

    @Test
    public void testAddWithZero() {
        assertEquals(0, calc.add(0, 0));
        assertNotEquals(0, calc.add(0, 3));
        assertTrue((calc.add(0, 100)) == 100);
        assertFalse((calc.add(0,-100)) == 0);
    }

    @Test
    public void testSubstractWithZero() {
        assertEquals(0, calc.subtract(0, 0));
        assertNotEquals(0, calc.subtract(0, 3));
        assertTrue((calc.subtract(0, 100)) == -100);
        assertFalse((calc.subtract(0,-100)) == 0);
    }

    @Test
    public void testMultilyAndDivide() {
        assertEquals(0, calc.multiply((int)calc.divide(123, 456), 0));
        assertNotEquals(1, calc.multiply((int)calc.divide(-123, 456), -789));
        assertTrue((calc.multiply((int)calc.divide(4, 2), 10)) == 20);
        assertFalse((calc.multiply((int)calc.divide(-123, 456),789)) == 1);
    }

    @Test
    public void testMultiplyWithZero() {
        assertEquals(0, calc.multiply(0, 0));
        assertNotEquals(1, calc.multiply(0, 3));
        assertTrue((calc.multiply(0, 100)) == 0);
        assertFalse((calc.multiply(0,-100)) == 100);
    }

    @Test
    public void testDivideWithZero() {
        assertEquals(0, calc.divide(0, 1), 2);
        assertNotEquals(1, calc.divide(0, 1));
        assertTrue((calc.divide(0, 100)) == 0);
        assertFalse((calc.divide(0,-100)) == 100);
    }
}
