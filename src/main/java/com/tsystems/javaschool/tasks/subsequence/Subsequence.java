package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {
    private final ValidationProvider validator = new Validator();
    
    @SuppressWarnings("rawtypes")
    public boolean find(List firstSequence, List secondSequence) {
        validator.validate(firstSequence, secondSequence);
        
        return isSecondSequenceContainsFirstSequenceElemsInRightOrder(firstSequence, secondSequence);
    }
    
    @SuppressWarnings("rawtypes")
    boolean isSecondSequenceContainsFirstSequenceElemsInRightOrder(List firstSequence, List secondSequence) {
        int elementsFound = 0;
        int secondSequencePos = 0;
        
        for (Object firstSequenceElem : firstSequence) {
            while (secondSequencePos < secondSequence.size()) {
                if (firstSequenceElem.equals(secondSequence.get(secondSequencePos))) {
                    elementsFound++;
                    break;
                }
                
                secondSequencePos++;
            }
        }
        
        return elementsFound == firstSequence.size();
    }
}
