package com.example.calculatorapp;

public class Normal_Calc {

    // Addition
    public double add(double num1, double num2) {
        return num1 + num2;
    }

    // Subtraction
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }

    // Multiplication
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    // Division
    public double divide(double num1, double num2) {

        // Prevent division by zero
        if (num2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        return num1 / num2;
    }

    // Selects the operation
    public double calculate(double num1, double num2, String operator) {

        switch (operator) {

            case "+":
                return add(num1, num2);

            case "-":
                return subtract(num1, num2);

            case "*":
                return multiply(num1, num2);

            case "/":
                return divide(num1, num2);

            default:
                throw new IllegalArgumentException(
                        "Invalid operator"
                );
        }
    }
}