package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean find(List firstSequence, List secondSequence) {
        int secondSequencePos = 0;
        
        final List list = new ArrayList(firstSequence);
        
        for (Object firstSequenceElem : firstSequence) {
            while (secondSequencePos < secondSequence.size()) {
                if (firstSequenceElem.equals(secondSequence.get(secondSequencePos))) {
                    list.remove(firstSequenceElem);
                    break;
                }
                
                secondSequencePos++;
            }
        }
        
        return list.isEmpty();
    }
    
}
