package org.advent.day11;

import io.vavr.Tuple;
import org.advent.utils.GridUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Part1Test {

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

    private Day11Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day11Part1();
    }

    @Test
    void findsAllGalaxyIndices() {
        var grid = GridUtils.createGrid(TEST_DATA);
        assertEquals(
                List.of(
                        Tuple.of(1, 4),
                        Tuple.of(2, 8),
                        Tuple.of(3, 1),
                        Tuple.of(5, 7),
                        Tuple.of(6, 2),
                        Tuple.of(7, 10),
                        Tuple.of(9, 8),
                        Tuple.of(10, 1),
                        Tuple.of(10, 5)
                ),
                underTest.findAllGalaxies(grid)
        );
    }

    @Test
    void solvesForTestInput() {
        assertEquals(374L, underTest.solve(TEST_DATA));
    }

}
