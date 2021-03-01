package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RPNParser implements RPNParserProvider{
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String DIVISION = "/";
    public static final String MULTIPLICATION = "*";
    public static final String LEFT_PARENTHESIS = "(";
    public static final String RIGHT_PARENTHESIS = ")";
    public static final char DECIMAL_SEPARATOR = '.';
    
    private static final Map<String, Integer> operatorToPriority;
    private final List<String> rpnTokens = new ArrayList<>();
    private final Stack<String> operatorStack = new Stack<>();
    
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
            operatorStack.push(operator);
        }
        else if (operator.equals(RIGHT_PARENTHESIS)) {
            while (!operatorStack.peek().equals(LEFT_PARENTHESIS)) {
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
