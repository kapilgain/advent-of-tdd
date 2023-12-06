package org.advent.day06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Part2Test {

    private Day06Part2 underTest;

    @BeforeEach
    public void setup() {
        underTest = new Day06Part2();
    }

    @Test
    public void solvesPartOneSampleInput() {
        assertEquals(71503, underTest.solve(Day06Part1Test.TEST_DATA));
    }

}

