package org.advent.day12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day12.Day12Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Part2Test {

    private Day12Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day12Part2();
    }

    @Test
    void testUnfold() {
        assertEquals(".#?.#?.#?.#?.# 1,1,1,1,1", underTest.unfold(".# 1"));
        assertEquals(
                "???.###????.###????.###????.###????.### 1,1,3,1,1,3,1,1,3,1,1,3,1,1,3",
                underTest.unfold("???.### 1,1,3")
        );
    }

    @Test
    void solvesForTestInput() {
        assertEquals(525152L, underTest.solve(TEST_DATA));
    }

}
