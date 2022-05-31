package com.hackerrank.weather.livecoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SquareOfArray {
    /**
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
     * <p>
     * input = [1, 2, 3]
     * output = [1, 4, 9]
     *
     * @param input
     * @return
     */
    public List<Integer> calcSquare(Integer[] input) {
        //  O(log n)
        PriorityQueue<Integer> list = new PriorityQueue<>(Arrays.asList(input));
        List<Integer> result = new ArrayList<>();
        for (Integer num : list) {
            result.add(num * num);
        }
        return result;
    }
}
