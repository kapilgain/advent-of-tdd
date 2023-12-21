package org.advent.day21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Day21Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
            """
            .trim().split("\n")).toList();

    private Day21Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day21Part1();
    }

    @Test
    void countThrowsExceptionWhenNoStartFound() {
        assertThrows(RuntimeException.class, () -> underTest.countFor(1, List.of(".")));
    }

    @Test
    void testCounts() {
        assertEquals(2, underTest.countFor(1, TEST_DATA));
        assertEquals(4, underTest.countFor(2, TEST_DATA));
        assertEquals(6, underTest.countFor(3, TEST_DATA));
        assertEquals(16, underTest.countFor(6, TEST_DATA));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(underTest.countFor(64, TEST_DATA), underTest.solve(TEST_DATA));
    }

}
