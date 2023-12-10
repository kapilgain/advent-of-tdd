package org.advent.day09;

import org.advent.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
            """
            .trim().split("\n")).toList();

    private Day09Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day09Part1();
    }

    @Test
    void calculatesDeltasForGivenSequence() {
        assertEquals(
                List.of(3L, 3L, 5L, 9L, 15L),
                underTest.deltas(StringUtils.splitToLongList("10 13 16 21 30 45"))
        );
    }

    @Test
    void calculatesNextNumberInSequence() {
        assertEquals(0L, underTest.next(StringUtils.splitToLongList("0 0")));
        assertEquals(2L, underTest.next(StringUtils.splitToLongList("2 2 2")));
        assertEquals(8L, underTest.next(StringUtils.splitToLongList("0 2 4 6")));
        assertEquals(23L, underTest.next(StringUtils.splitToLongList("3 3 5 9 15")));
        assertEquals(68L, underTest.next(StringUtils.splitToLongList("10 13 16 21 30 45")));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(114L, underTest.solve(TEST_DATA));
    }

}
