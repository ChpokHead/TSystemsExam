package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Calculator {
    private static final Stack<String> operationStack = new Stack<>();
    private static final Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "/", "*"));
    
    public String evaluate(String statement) {
        if (isNotValid(statement)) {
            return null;
        }
        
        final List<String> tokens = new RPNParser().parseStatementToRPNTokens(statement);
        
        
        return calculateRPNStatement(tokens);
    }
    
    private boolean isNotValid(String statement) {
        return statement == null || statement.isEmpty() || statement.trim().isEmpty();
    }
    
    private String calculateRPNStatement(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            final String currentToken = tokens.get(i);
            
            if (isNumber(currentToken)) {
                addOperandToStack(currentToken);
            }
            
            if (isOperator(currentToken)) {
                final String exprValue = calculateExpression(currentToken);
                
                if (exprValue == null) {
                    return null;
                }
                
                addOperandToStack(exprValue);
            }
        }
        
        return getStatementResult();
    }
    
    private boolean isNumber(String token) {
        try {
            Integer.parseInt(String.valueOf(token));
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
        if (operator.equals("+")) {
            return calculateSum();
        }
        
        if (operator.equals("-")) {
            return calculateDifference();
        }
        
        if (operator.equals("/")) {
            return calculateQuotient();
        }
        
        if (operator.equals("*")) {
            return calculateMultiplication();
        }
        
        return null;
    }
    
    private String calculateSum() {
        return Double.toString(Double.valueOf(operationStack.pop()) + Double.valueOf(operationStack.pop()));
    }
    
    private String calculateDifference() {
        return Double.toString(Double.valueOf(operationStack.pop()) - Double.valueOf(operationStack.pop()));
    }
    
    private String calculateQuotient() {
        final double divisor = Double.parseDouble(operationStack.pop());
        final double divident = Double.parseDouble(operationStack.pop());
        
        return Math.abs(divisor) < 2 * Double.MIN_VALUE ? null : Double.toString(divident / divisor);
    }
    
    private String calculateMultiplication() {
        return Double.toString(Double.valueOf(operationStack.pop()) * Double.valueOf(operationStack.pop()));
    }
    
    private String getStatementResult() {
        return roundResultValue(operationStack.pop());
    }
    
    private String roundResultValue(String value) {
        final double result = Double.parseDouble(value);
        
        if (result == (int)result) {
            return String.valueOf((int)result);
        } else {
            final DecimalFormat df = new DecimalFormat("#.####");
            
            return df.format(result);
        }
    }
}
