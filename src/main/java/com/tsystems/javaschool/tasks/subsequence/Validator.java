package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Validator implements ValidationProvider {

    @SuppressWarnings("rawtypes")
    @Override
    public void validate(List firstSequence, List secondSequence) {
        if (firstSequence == null) {
            throw new IllegalArgumentException("First sequence is null!");
        }
        
        if (secondSequence == null) {
            throw new IllegalArgumentException("Second sequence is null!");
        }
        
    }

}
