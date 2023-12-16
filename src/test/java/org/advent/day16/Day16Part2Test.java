package org.advent.day16;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day16.Day16Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Part2Test {

    private Day16Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day16Part2();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(51, underTest.solve(TEST_DATA));
    }

}
