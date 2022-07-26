package examples;

import org.junit.Assert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
     static Calculator calc;

    @BeforeAll
    static public void setUp(){
        calc = new Calculator();
    }

    @Test
    @DisplayName("This is addition test")
    public void additionTest(){
        //Call add method
        int result = calc.add(5,7);

        //Assertion
        Assertions.assertEquals(12, result, "The result should be 12");
    }

    @Test
    public void multiplyTest(){
        //Call add method
        int result = calc.multiply(5,7);

        //Assertion
        Assert.assertEquals(35, result);
    }
}
