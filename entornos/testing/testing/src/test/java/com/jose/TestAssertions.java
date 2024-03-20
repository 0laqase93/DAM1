package com.jose;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestAssertions {

   // Esto es para inicializar las variables siempre que se haga un test
   @Test
   public void setUp() {
      // test data
      
      String str1 = new String ("abc");
      String str2 = new String ("abc");
      String str3 = null;
      String str4 = "abc";
      String str5 = "abc";
      
      int val1 = 5;
      int val2 = 6;

      String[] expectedArray = {"one", "two", "three"};
      String[] resultArray =  {"one", "two", "three"};
   }

   @Test
   public void testTwoObjectsAreEquals() {
      String str1 = new String ("abc");
      String str2 = new String ("abc");

      assertEquals(str1, str2);
   }

   @Test
   public void testOneNumberIsLowest() {
      int val1 = 5;
      int val2 = 6;

      assertTrue(val1 < val2);
   }

   @Test
   public void testOneNumberIsBigger() {
      int val1 = 5;
      int val2 = 6;

      assertFalse(val1 > val2);
   }

   @Test
   public void testTwoNumbersNotAreEquals() {
      int val1 = 5;
      int val2 = 6;

      assertNotEquals(val1, val2);
   }

   @Test
   public void testObjectIsNotNull() {
      String str2 = new String ("abc");

      assertNotNull(str2);
   }

   @Test
   public void testObjectIsNull() {
      String str3 = null;

      assertNull(str3);
   }

   @Test
   public void testTwoObjectsAreTheSame() {
      String str4 = "abc";
      String str5 = "abc";

      //Dos objetos apuntan a la misma dirección de memoria
      assertSame(str4, str5);
   }

   @Test
   public void testTwoObjectsAreNotTheSame() {
      String str1 = new String ("abc");
      String str2 = new String ("abc");

      assertNotSame(str1, str2);
   }

   @Test
   public void testTwoArraysAreEquals() {
      String[] expectedArray = {"one", "two", "three"};
      String[] resultArray =  {"one", "two", "three"};

      assertArrayEquals(expectedArray, resultArray);
   }
}