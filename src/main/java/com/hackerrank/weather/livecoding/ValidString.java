package com.hackerrank.weather.livecoding;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

@Slf4j
public class ValidString {
    /**
     * Given a strings containing just the characters'(',')','{','}','['and']', determine if the input string is valid.
     * An input string is valid if:
     * 1. Open brackets must be closed by the same type of brackets.
     * 2. Open brackets must be closed in the correct order.
     * <p>
     * <p>
     * Example 1:
     * Input: input = "()"
     * Output: true
     * <p>
     * Example 2:
     * Input: input = "()[]{}"
     * Output: true
     * <p>
     * Example 3:
     * Input: input = "(]"
     * Output: false
     *
     * @param input
     * @return Boolean
     */
    public boolean isValid(String input) {
        log.info("input: {}", input);
        Deque<Character> deque = new ArrayDeque<>();
        for (char currentChar : input.toCharArray()) {
            log.info("current char: {}", currentChar);
            log.info("deque: {}", deque);
            if (currentChar == '(')
                deque.push(')');
            else if (currentChar == '{')
                deque.push('}');
            else if (currentChar == '[')
                deque.push(']');
            else if (deque.isEmpty() || deque.pop() != currentChar)
                return false;
        }
        return deque.isEmpty();
    }
}
