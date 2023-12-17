package org.advent.day18;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""

            """
            .trim().split("\n")).toList();

    private Day18Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day18Part1();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(-1L, underTest.solve(TEST_DATA));
    }

}
