package org.advent.day18;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day18.Day18Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Part2Test {

    private Day18Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day18Part2();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(-1L, underTest.solve(TEST_DATA));
    }

}
