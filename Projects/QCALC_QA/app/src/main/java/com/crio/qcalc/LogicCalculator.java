package com.crio.qcalc;

public class LogicCalculator extends StandardCalculator {

    public static void getVersion() {
        System.out.println("Logic Calculator 1.0");
    }

    public void AND(int num1, int num2) {
        result = num1 & num2; // Perform bitwise AND operation
    }

    public void OR(int num1, int num2) {
        result = num1 | num2; // Perform bitwise OR operation
    }

    public void XOR(int num1, int num2) {
        result = num1 ^ num2; // Perform bitwise XOR operation
    }

    public void NOT(int num1) {
        result = ~num1; // Perform bitwise NOT operation
    }

    @Override
    public void printResult() {
        System.out.println("Logic Calculator Result: " + result);
    }

}
