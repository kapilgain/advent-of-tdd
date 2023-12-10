package org.advent.day06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day06.Day06Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Part2Test {

    private Day06Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day06Part2();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(71503, underTest.solve(TEST_DATA));
    }

}

