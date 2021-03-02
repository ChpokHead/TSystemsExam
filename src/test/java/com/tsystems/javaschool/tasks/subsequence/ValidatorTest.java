package com.tsystems.javaschool.tasks.subsequence;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ValidatorTest {
    final private Validator validator = new Validator();
    
    @Test
    public void validateShouldThrowIllegalArgumentExceptionIfFirstSequenceIsNull() {
        final List firstSequence = null;
        final List secondSequence = new ArrayList();
        final String expected = "First sequence is null!";
        
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> validator.validate(firstSequence, secondSequence)).withMessage(expected);
    }
    
    @Test
    public void validateShouldThrowIllegalArgumentExceptionIfSecondSequenceIsNull() {
        final List firstSequence = new ArrayList();
        final List secondSequence = null;
        final String expected = "Second sequence is null!";
        
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> validator.validate(firstSequence, secondSequence)).withMessage(expected);
    }

}
