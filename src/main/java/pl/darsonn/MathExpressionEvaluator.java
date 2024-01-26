package pl.darsonn;

import java.util.Stack;

public class MathExpressionEvaluator {
    public static double evaluateMathExpression(String expression) {
        expression = expression.replaceAll("\\s", "");
        return evaluateExpressionRecursively(expression);
    }

    private static double evaluateExpressionRecursively(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                i--;
                numbers.push(Double.parseDouble(numBuilder.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperator(numbers, operators);
                }
                operators.pop();
            } else if (c == '+' || c == '-' || c == 'X' || c == '/' || c == '%') {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    applyOperator(numbers, operators);
                }
                operators.push(c);
            } else {
                return 0;
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(numbers, operators);
        }

        return numbers.pop();
    }

    private static void applyOperator(Stack<Double> numbers, Stack<Character> operators) {
        char operator = operators.pop();
        double b = numbers.pop();
        double a = numbers.pop();
        double result = performOperation(a, b, operator);
        numbers.push(result);
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if ((op1 == 'X' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-')) {
            return true;
        }
        return false;
    }

    private static double performOperation(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'X':
                return a * b;
            case '/':
                if (b != 0) {
                    return a / b;
                } else {
                    return 0;
                }
            case '%':
                return a % b;
            default:
                return 0;
        }
    }
}