package org.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class StringCalculatorTest {
    private StringCalculator str_calc;

    @BeforeEach
    public void setUp(){
        str_calc = new StringCalculator();
    }

    @Test
    public void CheckForEmptyString(){
        Assertions.assertEquals(0, str_calc.add(""));
    }

    @Test
    public void CheckForOneNumber(){
        Assertions.assertEquals(49543, str_calc.add("49543"));
        Assertions.assertEquals(-6, str_calc.add("-6"));
    }

    @Test
    public void CheckForTwoNumbers(){
        Assertions.assertEquals(1000, str_calc.add("199,801"));
        Assertions.assertEquals(-100, str_calc.add("300,-400"));
    }

    @Test
    public void CheckForManyNumbers(){
        Assertions.assertEquals(21, str_calc.add("1,2,3,4,5,6"));
        Assertions.assertEquals(-2, str_calc.add("2,-3,3,-4,5,-5"));
    }
}