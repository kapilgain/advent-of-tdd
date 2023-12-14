package org.advent.day14;

import org.advent.utils.GridUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.advent.day14.Day14Part1Test.TEST_DATA;
import static org.advent.day14.Day14Part1Test.TEST_DATA_GRID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day14Part2Test {

    private Day14Part2 underTest;

    public static final char[][] AFTER_CYCLE_1 = GridUtils.createGrid(
            Arrays.stream("""
                    .....#....
                    ....#...O#
                    ...OO##...
                    .OO#......
                    .....OOO#.
                    .O#...O#.#
                    ....O#....
                    ......OOOO
                    #...O###..
                    #..OO#....
                    """.trim().split("\n")).toList(),
            '#'
    );

    public static final char[][] AFTER_CYCLE_2 = GridUtils.createGrid(
            Arrays.stream("""
                    .....#....
                    ....#...O#
                    .....##...
                    ..O#......
                    .....OOO#.
                    .O#...O#.#
                    ....O#...O
                    .......OOO
                    #..OO###..
                    #.OOO#...O
                    """.trim().split("\n")).toList(),
            '#'
    );

    public static final char[][] AFTER_CYCLE_3 = GridUtils.createGrid(
            Arrays.stream("""
                    .....#....
                    ....#...O#
                    .....##...
                    ..O#......
                    .....OOO#.
                    .O#...O#.#
                    ....O#...O
                    .......OOO
                    #...O###.O
                    #.OOO#...O
                    """.trim().split("\n")).toList(),
            '#'
    );

    @BeforeEach
    void setup() {
        underTest = new Day14Part2();
    }

    @Test
    void testSpin() {
        assertTrue(Arrays.deepEquals(AFTER_CYCLE_1, underTest.spin(TEST_DATA_GRID)));
        assertTrue(Arrays.deepEquals(AFTER_CYCLE_2, underTest.spin(AFTER_CYCLE_1)));
        assertTrue(Arrays.deepEquals(AFTER_CYCLE_3, underTest.spin(AFTER_CYCLE_2)));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(64L, underTest.solve(TEST_DATA));
    }

}
