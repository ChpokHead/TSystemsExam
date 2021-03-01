package com.tsystems.javaschool.tasks.pyramid;

import java.util.List;

public class PyramidBuilder {
    private final ValidationProvider validator = new Validator();
    
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        validator.validate(inputNumbers);;
        
        return new int[0][0];
    }
    
}
