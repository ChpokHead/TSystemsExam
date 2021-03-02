package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public interface ValidationProvider {
    
    @SuppressWarnings("rawtypes")
    void validate(List firstSequence, List secondSequence);
}
