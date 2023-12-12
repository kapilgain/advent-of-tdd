package org.advent.day11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day11.Day11Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Part2Test {

    private Day11Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day11Part2();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(1030L, underTest.solve(TEST_DATA, 10));
        assertEquals(8410L, underTest.solve(TEST_DATA, 100));
    }

    @Test
    void expansionFactorOf2SolvesPart1() {
        assertEquals(374L, underTest.solve(TEST_DATA, 2));
    }

}
