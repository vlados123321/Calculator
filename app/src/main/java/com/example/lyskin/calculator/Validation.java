package com.example.lyskin.calculator;


import java.util.Arrays;
import org.mariuszgromada.math.mxparser.Expression;

class Validation {

    private static final String[] signs = {"+", "-", "*", "/", "."};

    private Validation() {}

    static double calculate(String expressionString) {
        Expression expression = new Expression(expressionString);
        return expression.calculate();
    }

    static boolean isValidResult(double result) {
        return Double.compare(result, Double.NaN) != 0;
    }

    static boolean isOperationCanBeEvaluate(String expression) {
        if(!expression.equals(""))
        {
            String match = String.valueOf(expression.charAt(expression.length() - 1));
            return Arrays.stream(signs).noneMatch(match::equals);
        }
        return false;
    }
}