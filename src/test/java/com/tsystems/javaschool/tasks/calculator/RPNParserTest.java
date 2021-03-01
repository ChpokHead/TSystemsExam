package com.tsystems.javaschool.tasks.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class RPNParserTest {
    private final RPNParser parser = new RPNParser();
    
    @Test
    public void parseStatementToRPNTokensShouldReturnCorrectTokensIfSubtractionStatementIsCorrect() {
        final String statement = "2-10";
        final List<String> expected = Arrays.asList("2","10","-");
        final List<String> actual = parser.parseStatementToRPNTokens(statement);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    public void parseStatementToRPNTokensShouldReturnCorrectTokensIfAdditionStatementIsCorrect() {
        final String statement = "2+5";
        final List<String> expected = Arrays.asList("2","5","+");
        final List<String> actual = parser.parseStatementToRPNTokens(statement);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    public void parseStatementToRPNTokensShouldReturnCorrectTokensIfDivisionStatementIsCorrect() {
        final String statement = "153/12";
        final List<String> expected = Arrays.asList("153","12","/");
        final List<String> actual = parser.parseStatementToRPNTokens(statement);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    public void parseStatementToRPNTokensShouldReturnCorrectTokensIfMultStatementIsCorrect() {
        final String statement = "8*14";
        final List<String> expected = Arrays.asList("8","14","*");
        final List<String> actual = parser.parseStatementToRPNTokens(statement);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    public void parseStatementToRPNTokensShouldReturnCorrectTokensIfStatementIsCorrectAndComplex() {
        final String statement = "(12+10)/2+5";
        final List<String> expected = Arrays.asList("12","10","+","2","/","5","+");
        final List<String> actual = parser.parseStatementToRPNTokens(statement);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    public void parseStatementToRPNTokensShouldReturnNullIfStatementIsIncorrect() {
        final String statement = "12,,2+5";
        final List<String> expected = null;
        final List<String> actual = parser.parseStatementToRPNTokens(statement);
        
        assertThat(actual).isEqualTo(expected);
    }

}
