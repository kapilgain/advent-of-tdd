package org.advent.day10;

import io.vavr.Tuple;
import org.advent.utils.GridUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day10Part1Test {

    public static final List<String> TEST_DATA_1 = Arrays.stream("""
            -L|F7
            7S-7|
            L|7||
            -L-J|
            L|-JF
            """
            .trim().split("\n")).toList();

    public static final List<String> TEST_DATA_2 = Arrays.stream("""
            7-F7-
            .FJ|7
            SJLL7
            |F--J
            LJ.LJ
            """
            .trim().split("\n")).toList();

    private Day10Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day10Part1();
    }

    @Test
    void findsAnyConnectingPipeToS() {
        var grid = GridUtils.createGrid(TEST_DATA_1);
        var foundLocation = underTest.findLocationOfAnyConnectingPipeTo(Tuple.of(2, 2), grid);
        assertTrue(Set.of(Tuple.of(2, 3), Tuple.of(3, 2)).contains(foundLocation));

        grid = GridUtils.createGrid(TEST_DATA_2);
        foundLocation = underTest.findLocationOfAnyConnectingPipeTo(Tuple.of(3, 1), grid);
        assertTrue(Set.of(Tuple.of(3, 2), Tuple.of(4, 1)).contains(foundLocation));
    }

    @Test
    void computesMainLoop() {
        var grid = GridUtils.createGrid(TEST_DATA_1);
        var loop = underTest.computeMainLoop(grid);
        assertEquals(Set.of(
                Tuple.of(2, 2), Tuple.of(3, 2), Tuple.of(4, 2),
                Tuple.of(4, 3), Tuple.of(4, 4), Tuple.of(3, 4),
                Tuple.of(2, 4), Tuple.of(2, 3)
        ), loop);

        grid = GridUtils.createGrid(TEST_DATA_2);
        loop = underTest.computeMainLoop(grid);
        assertEquals(Set.of(
                Tuple.of(1, 3), Tuple.of(2, 3), Tuple.of(2, 2), Tuple.of(3, 2), Tuple.of(3, 1), Tuple.of(4, 1),
                Tuple.of(4, 2), Tuple.of(4, 3), Tuple.of(5, 3), Tuple.of(5, 4),
                Tuple.of(4, 4), Tuple.of(3, 4), Tuple.of(2, 4), Tuple.of(2, 5),
                Tuple.of(1, 5), Tuple.of(1, 4)
        ), loop);
    }

    @Test
    void solvesForTestInput() {
        assertEquals(4, underTest.solve(TEST_DATA_1));
        assertEquals(8, underTest.solve(TEST_DATA_2));
    }

}
