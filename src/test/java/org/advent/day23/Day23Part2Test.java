package org.advent.day23;


import org.advent.utils.Direction;
import org.advent.utils.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.advent.day23.Day23Part1Test.TEST_DATA;
import static org.advent.utils.GridUtils.createGrid;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23Part2Test {

    private Day23Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day23Part2();
    }

    @Test
    void getNextDirectionsAtEmpty() {
        var grid = new char[][]{
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'},
        };
        var location = new Location(1, 1);
        var expected = Direction.values();
        var actual = underTest.getNextDirectionsAt(grid, location);
        assertEquals(expected.length, actual.length);
        for (var i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void getNextDirectionsAtNorth() {
        var grid = new char[][]{
                {'.', '.', '.'},
                {'.', '^', '.'},
                {'.', '.', '.'},
        };
        var location = new Location(1, 1);
        var expected = Direction.values();
        var actual = underTest.getNextDirectionsAt(grid, location);
        assertEquals(expected.length, actual.length);
        for (var i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void getNextDirectionsAtSouth() {
        var grid = new char[][]{
                {'.', '.', '.'},
                {'.', 'v', '.'},
                {'.', '.', '.'},
        };
        var location = new Location(1, 1);
        var expected = Direction.values();
        var actual = underTest.getNextDirectionsAt(grid, location);
        assertEquals(expected.length, actual.length);
        for (var i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void getNextDirectionsAtWall() {
        var grid = new char[][]{
                {'.', '.', '.'},
                {'.', '#', '.'},
                {'.', '.', '.'},
        };
        var location = new Location(1, 1);
        var actual = underTest.getNextDirectionsAt(grid, location);
        assertEquals(0, actual.length);
    }

    @Test
    void getNextDirectionsAtWest() {
        var grid = new char[][]{
                {'.', '.', '.'},
                {'.', '<', '.'},
                {'.', '.', '.'},
        };
        var location = new Location(1, 1);
        var expected = Direction.values();
        var actual = underTest.getNextDirectionsAt(grid, location);
        assertEquals(expected.length, actual.length);
        for (var i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void getNextDirectionsAtEast() {
        var grid = new char[][]{
                {'.', '.', '.'},
                {'.', '>', '.'},
                {'.', '.', '.'},
        };
        var location = new Location(1, 1);
        var expected = Direction.values();
        var actual = underTest.getNextDirectionsAt(grid, location);
        assertEquals(expected.length, actual.length);
        for (var i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void countPassableNeighbours() {
        var grid = createGrid(TEST_DATA, '#');
        assertEquals(1, underTest.countPassableNeighbours(grid, new Location(2, 1)));
        assertEquals(2, underTest.countPassableNeighbours(grid, new Location(2, 2)));
        assertEquals(3, underTest.countPassableNeighbours(grid, new Location(4, 6)));
    }

    @Test
    void findWaypoints() {
        var expected = new LinkedHashSet<Location>();
        expected.add(new Location(2, 1)); // scenic walk start
        expected.add(new Location(4, 6));
        expected.add(new Location(6, 14));
        expected.add(new Location(12, 4));
        expected.add(new Location(14, 14));
        expected.add(new Location(14, 20));
        expected.add(new Location(20, 20));
        expected.add(new Location(22, 12));
        expected.add(new Location(22, 23)); // scenic walk end

        assertEquals(expected, underTest.findWaypoints(TEST_DATA));
    }

    @Test
    void createContractedGraph() {
        var expected = new LinkedHashMap<Location, Map<Location, Integer>>();
        expected.put(
                new Location(2, 1),
                Map.of(new Location(4, 6), 15)
        );
        expected.put(
                new Location(12, 4),
                Map.of(
                        new Location(4, 6), 22,
                        new Location(14, 14), 24,
                        new Location(22, 12), 30
                )
        );
        expected.put(
                new Location(4, 6),
                Map.of(
                        new Location(2, 1), 15,
                        new Location(6, 14), 22,
                        new Location(12, 4), 22
                )
        );
        expected.put(
                new Location(22, 12),
                Map.of(
                        new Location(20, 20), 10,
                        new Location(14, 14), 18,
                        new Location(12, 4), 30
                )
        );
        expected.put(
                new Location(6, 14),
                Map.of(
                        new Location(14, 14), 12,
                        new Location(4, 6), 22,
                        new Location(14, 20), 38
                )
        );
        expected.put(
                new Location(14, 14),
                Map.of(
                        new Location(14, 20), 10,
                        new Location(6, 14), 12,
                        new Location(22, 12), 18,
                        new Location(12, 4), 24
                )
        );
        expected.put(
                new Location(14, 20),
                Map.of(
                        new Location(14, 14), 10,
                        new Location(20, 20), 10,
                        new Location(6, 14), 38
                )
        );
        expected.put(
                new Location(20, 20),
                Map.of(
                        new Location(22, 23), 5,
                        new Location(22, 12), 10,
                        new Location(14, 20), 10
                )
        );
        expected.put(
                new Location(22, 23),
                Map.of(new Location(20, 20), 5)
        );

        assertEquals(expected, underTest.createContractedGraph(TEST_DATA));
    }

    @Test
    void countLongestPathFromStartToStartIsZero() {
        assertEquals(
                0,
                underTest.countLongestPath(
                        underTest.createContractedGraph(TEST_DATA),
                        new Location(2, 1),
                        new Location(2, 1),
                        new HashSet<>(),
                        0
                )
        );
    }

    @Test
    void countLongestPathFromStartToFirstWaypoint() {
        assertEquals(
                15,
                underTest.countLongestPath(
                        underTest.createContractedGraph(TEST_DATA),
                        new Location(2, 1),
                        new Location(4, 6),
                        new HashSet<>(),
                        0
                )
        );
    }

    @Test
    void solvesForTestInput() {
        assertEquals(154L, underTest.solve(TEST_DATA));
    }

}
