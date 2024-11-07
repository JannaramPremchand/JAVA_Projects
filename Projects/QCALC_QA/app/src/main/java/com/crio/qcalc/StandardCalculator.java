package com.crio.qcalc;

public class StandardCalculator {

    protected double result; // Updated to double data type for accurate decimal calculations

    public double getResult() {
        return result;
    }

    public void clearResult() {
        result = 0.0; // Reset result to 0.0
    }

    public void printResult() {
        System.out.println("Standard Calculator Result: " + result);
    }

    public void setResult(double value) {
        this.result = value;
    }

    // Overloaded method for addition with double parameters
    public final void add(double num1, double num2) {
        double result = num1 + num2;
        if((result == Double.MAX_VALUE) || (result == Double.POSITIVE_INFINITY)){
            throw new ArithmeticException("Double overflow");
        }
        this.result = result;
    }

    // Overloaded method for subtraction with double parameters
    public final void subtract(double num1, double num2){
        double result = num1 - num2;
        if((result == -Double.MAX_VALUE) || (result == Double.NEGATIVE_INFINITY)){
            throw new ArithmeticException("Double overflow");
        }
        this.result = result;
    }

    // Overloaded method for multiplication with double parameters
    public final void multiply(double num1, double num2) {
        double result = num1 * num2;
        if((result == Double.MAX_VALUE) || (result == Double.POSITIVE_INFINITY) || (result == Double.NEGATIVE_INFINITY)){
            throw new ArithmeticException("Double overflow");
        }
        this.result = result;
    }

    // Overloaded method for division with double parameters
    public final void divide(double num1, double num2) {
        if (num2 != 0) {
            double result = num1 / num2;
            if((result == Double.MAX_VALUE) || (result == Double.POSITIVE_INFINITY)){
                throw new ArithmeticException("Double overflow");
            }
            this.result = result;
        } else {
            System.out.println("Error: Division by zero");
            throw new ArithmeticException("Divide By Zero");
        }
    }

    // Method to add integers by converting them to doubles and using the double parameter method
    public void add(int num1, int num2) {
        add((double) num1, (double) num2);
    }

    // Method to subtract integers by converting them to doubles and using the double parameter method
    public void subtract(int num1, int num2) {
        subtract((double) num1, (double) num2);
    }

    // Method to multiply integers by converting them to doubles and using the double parameter method
    public void multiply(int num1, int num2) {
        multiply((double) num1, (double) num2);
    }

    // Method to divide integers by converting them to doubles and using the double parameter method
    public void divide(int num1, int num2) {
        divide((double) num1, (double) num2);
    }
}
