package org.advent.day11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Part2Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
            """
            .trim().split("\n")).toList();

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
