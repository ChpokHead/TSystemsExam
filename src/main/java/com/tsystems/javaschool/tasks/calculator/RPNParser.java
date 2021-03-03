package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RPNParser implements RPNParserProvider{
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String DIVISION = "/";
    private static final String MULTIPLICATION = "*";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final char DECIMAL_SEPARATOR = '.';
    
    private static final Map<String, Integer> operatorToPriority;
    
    private final List<String> rpnTokens = new ArrayList<>();
    private final Stack<String> operatorsStack = new Stack<>();
    
    static {
        operatorToPriority = new HashMap<>();
        
        operatorToPriority.put(MULTIPLICATION, 2);
        operatorToPriority.put(DIVISION, 2);
        operatorToPriority.put(PLUS, 1);
        operatorToPriority.put(MINUS, 1);
        operatorToPriority.put(RIGHT_PARENTHESIS, 0);
        operatorToPriority.put(LEFT_PARENTHESIS, 0);
    }
    
    @Override
    public List<String> parseStatementToRPNTokens(String statement) {
        for (int i = 0; i < statement.length(); i++) {
            final String token = String.valueOf(statement.charAt(i));
            
            if (isNumber(token)) {
            	final String number = extractNumberFromStatement(statement.substring(i));
                
            	addOperandToRPN(number);
                
            	i += number.length() - 1;
            } else if (isOperator(token)) {
                addOperatorToRPN(token);
            } else return null;
            
        }
        
        getRemainingOperators();
        
        return rpnTokens;
    }
    
    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            return false;
        }
        
        return true;
    }
    
    private boolean isOperator(String token) {
        return operatorToPriority.containsKey(token);
    }
    
    private String extractNumberFromStatement(String statement) {
        final StringBuilder number = new StringBuilder();
        char currentSym;
        
        for (int i = 0; i < statement.length(); i++)       {
            currentSym = statement.charAt(i);
        
            if (Character.isDigit(currentSym) || currentSym == DECIMAL_SEPARATOR) {
                number.append(currentSym);
            } else {
                break;
            }        
        }
      
      return number.toString();
    }
    
    private void addOperandToRPN(String token) {
        rpnTokens.add(token);
    }
    
    private void addOperatorToRPN(String operator) {
        if (operator.equals(LEFT_PARENTHESIS)) {
            operatorsStack.push(operator);
        }
        else if (operator.equals(RIGHT_PARENTHESIS)) {
            while (!operatorsStack.peek().equals(LEFT_PARENTHESIS)) {
                rpnTokens.add(operatorsStack.pop());
            }
            
            operatorsStack.pop();
        }
        else {
            while (!operatorsStack.isEmpty() && operatorToPriority.get(operator) <= operatorToPriority.get(operatorsStack.peek())) {
                rpnTokens.add(operatorsStack.pop());
            }
            
            operatorsStack.push(operator);
        }
    }
    
    private void getRemainingOperators() {
        while (!operatorsStack.isEmpty()) {
            rpnTokens.add(operatorsStack.pop());
        }
    }
}
