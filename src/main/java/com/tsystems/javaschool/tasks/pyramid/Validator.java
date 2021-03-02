package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;

public class Validator implements ValidationProvider{
    
    @Override
    public void validate(List<Integer> inputNumbers) {
        if (inputNumbers == null) {
            throw new CannotBuildPyramidException();
        }
        
        if (inputNumbers.isEmpty()) {
            throw new CannotBuildPyramidException();
        }
        
        if (inputNumbers.contains(null)) {
            throw new CannotBuildPyramidException();
        }
        
        checkNumbersEnoughForEveryPyramidLevel(inputNumbers);
    }
    
    private void checkNumbersEnoughForEveryPyramidLevel(List<Integer> inputNumbers) {
        int rowNum = 0;
        int numAmount = inputNumbers.size();
        
        while (numAmount > 0) {
            numAmount -= ++rowNum;
        }
        
        if (numAmount != 0) {
            throw new CannotBuildPyramidException();
        }
    }
}
