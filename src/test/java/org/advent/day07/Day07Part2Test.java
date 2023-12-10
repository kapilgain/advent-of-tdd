package org.advent.day07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day07.Day07Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Part2Test {

    private Day07Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day07Part2();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(5905L, underTest.solve(TEST_DATA));
    }

}
