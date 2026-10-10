package com.example.calculatorapp;

/**
 * Performs normal calculator operations and evaluates
 * complete mathematical expressions.
 */
public class Normal_Calc {

    /**
     * Adds two numbers together.
     *
     * @param num1 first number
     * @param num2 second number
     * @return the sum of the two numbers
     */
    public double add(double num1, double num2) {

        return num1 + num2;
    }

    /**
     * Subtracts the second number from the first number.
     *
     * @param num1 first number
     * @param num2 second number
     * @return the difference between the two numbers
     */
    public double subtract(double num1, double num2) {

        return num1 - num2;
    }

    /**
     * Multiplies two numbers together.
     *
     * @param num1 first number
     * @param num2 second number
     * @return the product of the two numbers
     */
    public double multiply(double num1, double num2) {

        return num1 * num2;
    }

    /**
     * Divides the first number by the second number.
     *
     * @param num1 first number
     * @param num2 second number
     * @return the quotient of the two numbers
     * @throws ArithmeticException if the second number is zero
     */
    public double divide(double num1, double num2) {

        if (num2 == 0) {

            throw new ArithmeticException("Cannot divide by zero");
        }

        return num1 / num2;
    }

    /**
     * Performs a calculation using the supplied operator.
     *
     * @param num1 first number
     * @param num2 second number
     * @param operator arithmetic operator
     * @return calculated result
     */
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
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    /**
     * Evaluates a complete mathematical expression.
     *
     * @param expression mathematical expression
     * @return calculated result
     */
    public double calculateExpression(String expression) {

        ExpressionParser parser = new ExpressionParser(expression);

        double result = parser.parseExpression();

        if (parser.hasRemainingCharacters()) {

            throw new IllegalArgumentException("Invalid expression");
        }

        return result;
    }

    /**
     * Formats a calculated result so whole numbers do not
     * display unnecessary decimal places.
     *
     * @param result calculated result
     * @return formatted result
     */
    public String formatResult(double result) {

        if (result == (long) result) {

            return String.valueOf((long) result);
        }

        return String.valueOf(result);
    }

    /**
     * Parses mathematical expressions while following
     * normal arithmetic order of operations.
     */
    private static class ExpressionParser {

        private final String expression;

        private int position = 0;

        /**
         * Creates an expression parser.
         *
         * @param expression expression to parse
         */
        ExpressionParser(String expression) {

            this.expression = expression.replace(" ", "");
        }

        /**
         * Parses addition and subtraction.
         *
         * @return calculated value
         */
        double parseExpression() {

            double value = parseTerm();

            while (position < expression.length()) {

                char operator = expression.charAt(position);

                if (operator == '+') {

                    position++;

                    value += parseTerm();

                } else if (operator == '-') {

                    position++;

                    value -= parseTerm();

                } else {

                    break;
                }
            }

            return value;
        }

        /**
         * Parses multiplication and division.
         *
         * @return calculated value
         */
        double parseTerm() {

            double value = parseFactor();

            while (position < expression.length()) {

                char operator = expression.charAt(position);

                if (operator == '*') {

                    position++;

                    value *= parseFactor();

                } else if (operator == '/') {

                    position++;

                    double divisor = parseFactor();

                    if (divisor == 0) {

                        throw new ArithmeticException("Cannot divide by zero");
                    }

                    value /= divisor;

                } else {

                    break;
                }
            }

            return value;
        }

        /**
         * Parses numbers, parentheses, and negative values.
         *
         * @return calculated value
         */
        double parseFactor() {

            if (position >= expression.length()) {

                throw new IllegalArgumentException("Missing number");
            }

            char character = expression.charAt(position);

            if (character == '(') {

                position++;

                double value = parseExpression();

                if (position >= expression.length()
                        || expression.charAt(position) != ')') {

                    throw new IllegalArgumentException("Missing closing parenthesis");
                }

                position++;

                return value;
            }

            if (character == '-') {

                position++;

                return -parseFactor();
            }

            int start = position;

            while (position < expression.length()) {

                char current = expression.charAt(position);

                if ((current >= '0' && current <= '9')
                        || current == '.') {

                    position++;

                } else {

                    break;
                }
            }

            if (start == position) {

                throw new IllegalArgumentException("Invalid number");
            }

            return Double.parseDouble(
                    expression.substring(start, position)
            );
        }

        /**
         * Checks whether characters remain after parsing.
         *
         * @return true if characters remain
         */
        boolean hasRemainingCharacters() {

            return position < expression.length();
        }
    }
}