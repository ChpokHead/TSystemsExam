package com.tsystems.javaschool.tasks.pyramid;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ValidatorTest {
    private final Validator validator = new Validator();
    
    @Test
    public void validateShouldThrowCannotBuildPyramidExceptionIfInputNumbersAreNull() {
        final List<Integer> inputNumbers = null;
                
        assertThatExceptionOfType(CannotBuildPyramidException.class).isThrownBy(() -> validator.validate(inputNumbers));
    }
    
    @Test
    public void validateShouldThrowCannotBuildPyramidExceptionIfInputNumbersAreEmpty() {
        final List<Integer> inputNumbers = Collections.emptyList();
                
        assertThatExceptionOfType(CannotBuildPyramidException.class).isThrownBy(() -> validator.validate(inputNumbers));
    }
    
    @Test
    public void validateShouldThrowCannotBuildPyramidExceptionIfInputNumbersContainNull() {
        final List<Integer> inputNumbers = Arrays.asList(1, 8, 5, null, 3);
                
        assertThatExceptionOfType(CannotBuildPyramidException.class).isThrownBy(() -> validator.validate(inputNumbers));
    }
    
    @Test
    public void validateShouldThrowCannotBuildPyramidExceptionIfInputNumbersAmountIsNotEnoughForPyramidLevel() {
        final List<Integer> inputNumbers = Arrays.asList(1, 2, 3, 4);
                
        assertThatExceptionOfType(CannotBuildPyramidException.class).isThrownBy(() -> validator.validate(inputNumbers));
    }
    
}
