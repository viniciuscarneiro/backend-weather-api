package com.hackerrank.weather;


import com.hackerrank.weather.livecoding.SquareOfArray;
import com.hackerrank.weather.livecoding.ValidString;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class LiveCodingTest {

    private final ValidString validString = new ValidString();
    private final SquareOfArray squareOfArray = new SquareOfArray();

    @Test
    public void should_validate_string() {
        Assertions.assertTrue(validString.isValid("([])"));
        //Assertions.assertFalse(validString.isValid("([)])"));
    }

    @Test
    public void should_return_square_of_given_array() {
        Assertions.assertEquals(Arrays.asList(4, 1, 4, 9, 16), squareOfArray.calcSquare(new Integer[]{1, -2, 2, 3, 4}));
    }
}
