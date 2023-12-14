package org.advent.day14;

import org.advent.utils.GridUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day14Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
            """
            .trim().split("\n")).toList();

    public static final char[][] TEST_DATA_GRID = GridUtils.createGrid(TEST_DATA, '#');

    public static final List<Character> TEST_COL_1 = GridUtils.col(TEST_DATA_GRID, 1);

    public static final char[][] TILTED_GRID_FOR_TEST_DATA = GridUtils.createGrid(
            Arrays.stream("""
                    OOOO.#.O..
                    OO..#....#
                    OO..O##..O
                    O..#.OO...
                    ........#.
                    ..#....#.#
                    ..O..#.O.O
                    ..O.......
                    #....###..
                    #....#....
                    """.trim().split("\n")).toList(),
            '#'
    );

    private Day14Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day14Part1();
    }

    @Test
    void tiltsColumnNorth() {
        assertEquals(
                // Note that we are using '#' as walls (first and last element) during the tilt calculation
                List.of('#', 'O', 'O', 'O', 'O', '.', '.', '.', '.', '#', '#', '#'),
                underTest.tiltColNorth(TEST_COL_1)
        );
    }

    @Test
    void tiltsGridNorth() {
        assertTrue(Arrays.deepEquals(TILTED_GRID_FOR_TEST_DATA, underTest.tiltGridNorth(TEST_DATA_GRID)));
    }

    @Test
    void calculatesColumnLoad() {
        var col = GridUtils.col(underTest.tiltGridNorth(TEST_DATA_GRID), 1);
        assertEquals(34L, underTest.calculateLoadOnCol(col));
    }

    @Test
    void calculatesGridLoad() {
        assertEquals(136L, underTest.calculateLoadOnGrid(TILTED_GRID_FOR_TEST_DATA));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(136L, underTest.solve(TEST_DATA));
    }

}
