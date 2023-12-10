package org.advent.day09;

import org.advent.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day09.Day09Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Part2Test {

    private Day09Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day09Part2();
    }

    @Test
    void calculatesPrevNumberInSequence() {
        assertEquals(0L, underTest.prev(StringUtils.splitToLongList("0 0")));
        assertEquals(2L, underTest.prev(StringUtils.splitToLongList("2 2 2")));
        assertEquals(-2L, underTest.prev(StringUtils.splitToLongList("0 2 4 6")));
        assertEquals(5L, underTest.prev(StringUtils.splitToLongList("3 3 5 9 15")));
        assertEquals(5L, underTest.prev(StringUtils.splitToLongList("10 13 16 21 30 45")));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(2L, underTest.solve(TEST_DATA));
    }

}
