package org.advent.day17;

import org.advent.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day17.Day17Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Part2Test {

    public static final String TEST_DATA_2 = """
            111111111111
            999999999991
            999999999991
            999999999991
            999999999991
            """;

    private Day17Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day17Part2();
    }

    @Test
    void solvesForTestInputs() {
        assertEquals(94, underTest.solve(StringUtils.readLines(TEST_DATA)));
        assertEquals(71, underTest.solve(StringUtils.readLines(TEST_DATA_2)));
    }

}
