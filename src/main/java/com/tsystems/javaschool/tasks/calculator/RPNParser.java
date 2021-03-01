package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RPNParser {
    private static final Map<String, Integer> operatorToPriority;
    private final List<String> rpnTokens = new ArrayList<>();
    private final Stack<String> operatorStack = new Stack<>();
    
    static {
        operatorToPriority = new HashMap<>();
        
        operatorToPriority.put("*", 2);
        operatorToPriority.put("/", 2);
        operatorToPriority.put("+", 1);
        operatorToPriority.put("-", 1);
        operatorToPriority.put(")", 0);
        operatorToPriority.put("(", 0);
    }
    
    public List<String> parseStatementToRPNTokens(String statement) {
        for (int i = 0; i < statement.length(); i++) {
            final String token = String.valueOf(statement.charAt(i));
            
            if (isNumber(token)) {
                addOperandToRPN(token);
                continue;
            }
            
            if (isOperator(token)) {
                addOperatorToRPN(token);
            }
        }
        
        getRemainingOperators();
        
        return rpnTokens;
    }
    
    private boolean isNumber(String token) {
        try {
            Integer.parseInt(String.valueOf(token));
        } catch (NumberFormatException nfe) {
            return false;
        }
        
        return true;
    }
    
    private boolean isOperator(String token) {
        return operatorToPriority.containsKey(token);
    }
    
    private void addOperandToRPN(String token) {
        rpnTokens.add(token);
    }
    
    private void addOperatorToRPN(String operator) {
        if ("(".equals(operator)) {
            operatorStack.push(operator);
        }
        else if (")".equals(operator)) {
            while (!operatorStack.peek().equals("(")) {
                rpnTokens.add(operatorStack.pop());
            }
            
            operatorStack.pop();
        }
        else {
            while (!operatorStack.isEmpty() && operatorToPriority.get(operator) <= operatorToPriority.get(operatorStack.peek())) {
                rpnTokens.add(operatorStack.pop());
            }
            
            operatorStack.push(operator);
        }
    }
    
    private void getRemainingOperators() {
        while (!operatorStack.isEmpty()) {
            rpnTokens.add(operatorStack.pop());
        }
    }
}
