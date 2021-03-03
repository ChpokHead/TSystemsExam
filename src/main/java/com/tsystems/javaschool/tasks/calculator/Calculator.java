package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;

public class Calculator {
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String DIVISION = "/";
    private static final String MULTIPLICATION = "*";
    private static final String ROUNDING_FORMAT = "#.####";
    
    private final Stack<String> operationStack = new Stack<>();
    private final Set<String> operators = new HashSet<>(Arrays.asList(PLUS, MINUS, DIVISION, MULTIPLICATION));
    
    public String evaluate(String statement) {
        if (isStatementInvalid(statement)) {
            return null;
        }
        
        final RPNParserProvider parser = new RPNParser();
        final List<String> tokens = parser.parseStatementToRPNTokens(statement);
        
        if (tokens == null) {
            return null;
        }
        
        final String statementResult = calculateRPNStatement(tokens);
        
        emptyStack();
        
        return statementResult;
    }
    
    private boolean isStatementInvalid(String statement) {
        return statement == null || statement.isEmpty() || statement.trim().isEmpty();
    }
    
    private String calculateRPNStatement(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            final String currentToken = tokens.get(i);
            
            if (isNumber(currentToken)) {
                addOperandToStack(currentToken);
            } else if (isOperator(currentToken)) {
                final String exprValue = calculateExpression(currentToken);
                
                if (exprValue == null) {
                    return null;
                }
                
                addOperandToStack(exprValue);
            } else {
                return null;
            }
        }
        
        return getStatementResult();
    }
    
    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            return false;
        }
        
        return true;
    }
    
    private boolean isOperator(String operator) {
        return operators.contains(operator);
    }
    
    private void addOperandToStack(String operand) {
        operationStack.push(operand);
    }
    
    private String calculateExpression(String operator) {
        if (!checkIfStackHasTwoOperandsForExpression()) {
            return null;
        }
        
        if (operator.equals(PLUS)) {
            return calculateSum();
        }
        
        if (operator.equals(MINUS)) {
            return calculateDifference();
        }
        
        if (operator.equals(DIVISION)) {
            return calculateQuotient();
        }
        
        if (operator.equals(MULTIPLICATION)) {
            return calculateMultiplication();
        }
        
        return null;
    }
    
    private String calculateSum() {
        final double firstAddend = Double.parseDouble(operationStack.pop());
        final double secondAddend = Double.parseDouble(operationStack.pop());
        
        return Double.toString(firstAddend + secondAddend);
    }
    
    private String calculateDifference() {
        final double subtrahent = Double.parseDouble(operationStack.pop());
        final double minuent = Double.parseDouble(operationStack.pop());
        
        return Double.toString(minuent - subtrahent);
    }
    
    private String calculateQuotient() {
        final double divisor = Double.parseDouble(operationStack.pop());
        final double dividend = Double.parseDouble(operationStack.pop());
        
        return Math.abs(divisor) < 2 * Double.MIN_VALUE ? null : Double.toString(dividend / divisor);
    }
    
    private String calculateMultiplication() {
        final double firstMultiplier = Double.parseDouble(operationStack.pop());
        final double secondMultiplier = Double.parseDouble(operationStack.pop());
        
        return Double.toString(firstMultiplier * secondMultiplier);
    }
    
    private boolean checkIfStackHasTwoOperandsForExpression() {
        return operationStack.size() >= 2;
    }
    private String getStatementResult() {
        return roundResultValue(operationStack.pop());
    }
    
    private String roundResultValue(String value) {
        final double result = Double.parseDouble(value);
        
        if (result == (int)result) {
            return String.valueOf((int)result);
        } else {
            final NumberFormat numFormat = NumberFormat.getNumberInstance(Locale.US);
            final DecimalFormat decFormat = (DecimalFormat) numFormat;
            
            decFormat.applyPattern(ROUNDING_FORMAT);
            
            return decFormat.format(result);
        }
    }
    
    private void emptyStack() {
        operationStack.clear();
    }
}
