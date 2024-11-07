package com.crio.qcalc;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        System.out.println("Starting QCalc..");

        StandardCalculator calc1 = new StandardCalculator();
        calc1.multiply(-10,2);
        calc1.printResult();
        // calc.add(1, 2);

        // System.out.println(calc.getResult());

        // calc.subtract(Double.MAX_VALUE, Double.MAX_VALUE);
        // calc.printResult();

        
        LogicCalculator calc = new LogicCalculator();
        calc.OR(8, 6);
        calc.printResult();

        calc.AND(8, 6);
        calc.printResult();
        
    }
}
