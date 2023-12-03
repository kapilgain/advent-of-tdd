package org.advent.day03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Part2Test {

    private Part2 part2;
    private char[][] testGrid;

    @BeforeEach
    void setUp() {
        part2 = new Part2();
        testGrid = part2.createEmptyGrid(5, 5);
    }

    @Test
    void testSolveWithEmptyInput() {
        List<String> lines = Collections.emptyList();
        assertEquals(0, part2.solve(lines));
    }

    @Test
    void testSolveWithValidInput() {
        List<String> lines = Arrays.asList(
                "467..114..",
                "...*......",
                "..35..633."
        );
        assertEquals(16345, part2.solve(lines)); // Assuming specific behavior for gear ratios
    }

    @Test
    void testEstimateGearNeighbourhoodMap() {
        part2.populateGrid(testGrid, Arrays.asList(
                "467",
                "*..",
                "35."
        ));
        Map<String, List<Integer>> gearMap = part2.estimateGearNeighbourhoodMap(testGrid);
        assertFalse(gearMap.isEmpty());
        // Note that because we have padded the grid with '.' around the borders, this is effectively 1-indexed
        assertTrue(gearMap.containsKey("(2,1)"));
        assertEquals(Arrays.asList(467, 35), gearMap.get("(2,1)"));
    }

    @Test
    void testEstimateGearNeighbourhoodMapNoGears() {
        part2.populateGrid(testGrid, Arrays.asList(
                "467",
                "...",
                "35."
        ));
        Map<String, List<Integer>> gearMap = part2.estimateGearNeighbourhoodMap(testGrid);
        assertTrue(gearMap.isEmpty());
    }

    @Test
    void testFindAdjacentGears() {
        part2.populateGrid(testGrid, Arrays.asList(
                "467",
                "*..",
                "35."
        ));
        List<String> gears = part2.findAdjacentGears(testGrid, 1, 1);
        assertFalse(gears.isEmpty());
        assertEquals(1, gears.size());
        // Note that because we have padded the grid with '.' around the borders, this is effectively 1-indexed
        assertEquals("(2,1)", gears.get(0));
    }

    @Test
    void testFindAdjacentGearsNoGears() {
        part2.populateGrid(testGrid, Arrays.asList(
                "467",
                "...",
                "35."
        ));
        List<String> gears = part2.findAdjacentGears(testGrid, 1, 1);
        assertTrue(gears.isEmpty());
    }
}
