package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {
    private final ValidationProvider validator = new Validator();
    
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        validator.validate(inputNumbers);
        
        final int rowsNumber = calculateRowsNumber(inputNumbers);
        final int colsNumber = calculateColumnsNumber(rowsNumber);
        
        Collections.sort(inputNumbers);
        
        return populatePyramid(rowsNumber, colsNumber, inputNumbers);
    }
    
    private int calculateRowsNumber(List<Integer> inputNumbers) {
        int rowNumber = 0;
        int numAmount = inputNumbers.size();
        
        while (numAmount != 0) {
            numAmount -= ++rowNumber;
        }
        
        return rowNumber;
    }
    
    private int calculateColumnsNumber(int rowsNumber) {
        return rowsNumber * 2 - 1;
    }
    
    private int[][] populatePyramid(int rowsNumber, int colsNumber, List<Integer> numbers) {
        final int[][] pyramid = new int[rowsNumber][colsNumber];
        int currentNumber = 0;
        
        for (int i = 0; i < rowsNumber; i++) {
            int numberPlacementIndex = rowsNumber - i - 1;
            
            for (int j = 0; j <= i; j++) {
                pyramid[i][numberPlacementIndex] = numbers.get(currentNumber++);
                numberPlacementIndex += 2;
            }
        }
        
        return pyramid;
    }
    
}
