package org.advent.day03;

import org.advent.utils.GridUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day03Part1Test {

    private Day03Part1 part1;
    private char[][] testGrid;

    @BeforeEach
    void setUp() {
        part1 = new Day03Part1();
        testGrid = GridUtils.createEmptyGrid(5, 5);
    }

    @Test
    void testSolveWithEmptyInput() {
        List<String> lines = Collections.emptyList();
        assertEquals(0, part1.solve(lines));
    }

    @Test
    void testSolveWithSampleInput() {
        List<String> lines = Arrays.asList(
                "467..114..",
                "...*......",
                "..35..633."
        );
        assertEquals(502, part1.solve(lines));
    }

    @Test
    void testFindPartNumbers() {
        GridUtils.populateGrid(testGrid, Arrays.asList(
                "467",
                "*..",
                "35."
        ));
        List<Integer> partNumbers = part1.findPartNumbers(testGrid);
        assertEquals(Arrays.asList(467, 35), partNumbers);
    }

    @Test
    void testHasAdjacentSymbol() {
        GridUtils.populateGrid(testGrid, Arrays.asList(
                "467",
                "*..",
                "35."
        ));
        assertTrue(part1.hasAdjacentSymbol(testGrid, 1, 1));
        assertFalse(part1.hasAdjacentSymbol(testGrid, 3, 3));
    }

}
