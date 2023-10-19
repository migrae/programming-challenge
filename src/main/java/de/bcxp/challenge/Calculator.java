package de.bcxp.challenge;

public class Calculator {

    public static float calculate(float a, char op, float b) {
        switch (op) {
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                throw new UnsupportedOperationException("Unsupported operation: " + op);
        }
    }
}
