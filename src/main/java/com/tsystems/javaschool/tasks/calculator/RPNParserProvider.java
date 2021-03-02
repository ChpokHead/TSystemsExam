package com.tsystems.javaschool.tasks.calculator;

import java.util.List;

public interface RPNParserProvider {
    public List<String> parseStatementToRPNTokens(String statement);
}
