package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Validator implements ValidationProvider {

    @SuppressWarnings("rawtypes")
    @Override
    public void validate(List firstSequence, List secondSequence) {
        if (firstSequence == null) {
            throw new IllegalArgumentException("firstSequenceIsNull");
        }
        
        if (secondSequence == null) {
            throw new IllegalArgumentException("secondSequenceIsNull");
        }
        
    }

}
