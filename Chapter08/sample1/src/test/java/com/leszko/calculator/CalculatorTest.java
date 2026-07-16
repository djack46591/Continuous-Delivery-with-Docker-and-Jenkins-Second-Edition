package com.leszko.calculator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the Calculator class.
 */
public class CalculatorTest {
     private Calculator calculator = new Calculator();

     @Test
     public void testSum() {
          // Changed to intentionally fail for the Exercise 7 audit
          assertEquals(4, 2 + 2); 
     }
}
