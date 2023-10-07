package org.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class StringCalculatorTest {
    private StringCalculator str_calc;

    @BeforeEach
    public void setUp() {
        str_calc = new StringCalculator();
    }

    @Test
    public void CheckForEmptyString() {
        Assertions.assertEquals(0, str_calc.add(""));
    }

    @Test
    public void CheckForOneNumber() {
        Assertions.assertEquals(495, str_calc.add("495"));
        Assertions.assertEquals(6, str_calc.add("6"));
    }

    @Test
    public void CheckForManyNumbers() {
        Assertions.assertEquals(21, str_calc.add("1,2,3,4,5,6"));
        Assertions.assertEquals(620, str_calc.add("2,1,3,4,55,555"));
    }

    @Test
    public void CheckForNewLineDenominator() {
        Assertions.assertEquals(10, str_calc.add("1,2\n3\n4"));
        Assertions.assertEquals(4, str_calc.add("1\n1\n1\n1"));
    }

    @Test
    public void CheckForUserDenominator(){
        Assertions.assertEquals(15, str_calc.add("//[;]\n1;2\n3;4,5"));
        Assertions.assertEquals(1055, str_calc.add("//[#]\n1#22,3,4#25\n1000"));
        Assertions.assertEquals(8, str_calc.add("//[1]\n2121212"));
    }

    @Test
    public void CheckForErrors(){
        Assertions.assertEquals(-1, str_calc.add("1,2,,3"));
        Assertions.assertEquals(-1, str_calc.add("1;2,3"));
        Assertions.assertEquals(-1, str_calc.add("-1,2,-3\n5"));
    }

    @Test
    public void CheckForBigNumbers(){
        Assertions.assertEquals(1003, str_calc.add("1,10001,1001,2,1000"));
        Assertions.assertEquals(999, str_calc.add("999999\n9999\n999,999999999"));
        Assertions.assertEquals(0, str_calc.add("1001,1002,10003,7787878"));
    }

    @Test
    public void CheckForUserBigDenominator(){
        Assertions.assertEquals(15, str_calc.add("//[JOHN]\n1JOHN2\n3JOHN4,5"));
        Assertions.assertEquals(8, str_calc.add("//[111]\n2111211121112"));
        Assertions.assertEquals(6, str_calc.add("//[denominator]\n1denominator2denominator3denominator4000"));
    }

    @Test
    public void CheckForUserManyDenominators(){
        Assertions.assertEquals(15, str_calc.add("//[;][#]\n1;2\n3#4,5"));
        Assertions.assertEquals(12, str_calc.add("//[1][%][@]\n21212%2@2%2"));
        Assertions.assertEquals(21, str_calc.add("//[ ][p][l][n]\n1 2p3l4000n5p6 4"));
    }
}