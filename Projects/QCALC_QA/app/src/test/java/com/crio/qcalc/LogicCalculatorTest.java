package com.crio.qcalc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LogicCalculatorTest {

    private LogicCalculator logicCalculator;

    @BeforeEach
    void setup() {
        logicCalculator = new LogicCalculator();
    }

    @Test
    @DisplayName("Test AND of two Integers")
    void testANDOperation() {
        // Arrange
        int num1 = 5;
        int num2 = 7;
        int expectedResult = num1 & num2; // Expected result of bitwise AND

        // Act
        logicCalculator.AND(num1, num2);
        double actualResult = logicCalculator.getResult();

        // Assert
        Assertions.assertEquals((double) expectedResult, actualResult);
    }

    @Test
    @DisplayName("Test OR of two Integers")
    void testOROperation() {
        // Arrange
        int num1 = 5;
        int num2 = 7;
        int expectedResult = num1 | num2; // Expected result of bitwise OR

        // Act
        logicCalculator.OR(num1, num2);
        double actualResult = logicCalculator.getResult();

        // Assert
        Assertions.assertEquals((double) expectedResult, actualResult);
    }

    @Test
    @DisplayName("Test XOR of two Integers")
    void testXOROperation() {
        // Arrange
        int num1 = 5;
        int num2 = 7;
        int expectedResult = num1 ^ num2; // Expected result of bitwise XOR

        // Act
        logicCalculator.XOR(num1, num2);
        double actualResult = logicCalculator.getResult();

        // Assert
        Assertions.assertEquals((double) expectedResult, actualResult);
    }

    @Test
    @DisplayName("Test NOT of given Integer")
    void testNOTOperation() {
        // Arrange
        int num = 5;
        int expectedResult = ~num; // Expected result of bitwise NOT

        // Act
        logicCalculator.NOT(num);
        double actualResult = logicCalculator.getResult();

        // Assert
        Assertions.assertEquals((double) expectedResult, actualResult);
    }
}
