package org.advent.day06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            Time:      7  15   30
            Distance:  9  40  200
            """
            .trim().split("\n")).toList();

    private Day06Part1 underTest;

    @BeforeEach
    public void setup() {
        underTest = new Day06Part1();
    }

    @Test
    public void countsNumberOfSolutionsForRaces() {
        assertEquals(4, underTest.countSolutions(7, 9));
        assertEquals(8, underTest.countSolutions(15, 40));
        assertEquals(9, underTest.countSolutions(30, 200));
    }

    @Test
    public void solvesForTestInput() {
        assertEquals(288, underTest.solve(TEST_DATA));
    }

}
