package org.advent.day17;

import io.vavr.collection.PriorityQueue;
import org.advent.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Part1Test {

    public static final String TEST_DATA = """
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
            """;

    private Day17Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day17Part1();
    }

    @Test
    void testEnqueueNextCrucibleStates() {
        var grid = GridUtils.createGrid(StringUtils.readLines(TEST_DATA), '0');
        Crucible originalItem = new Crucible(0, new Pointer(new Location(1, 1), Direction.SOUTH), 0);
        var pQ = PriorityQueue.of(originalItem);

        var crucible = new Crucible(0, new Pointer(new Location(1, 1), Direction.EAST), 0);
        var results = underTest.enqueueNextCrucibleStates(grid, crucible, pQ).toList();
        assertEquals(3, results.size());
        assertEquals(originalItem, results.get(0));

        // newly added items
        assertEquals(new Crucible(3, new Pointer(new Location(1, 2), Direction.SOUTH), 1), results.get(1));
        assertEquals(new Crucible(4, new Pointer(new Location(2, 1), Direction.EAST), 1), results.get(2));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(102, underTest.solve(StringUtils.readLines(TEST_DATA)));
    }

}
