package org.advent.day23;

import org.advent.utils.Direction;
import org.advent.utils.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            #.#####################
            #.......#########...###
            #######.#########.#.###
            ###.....#.>.>.###.#.###
            ###v#####.#v#.###.#.###
            ###.>...#.#.#.....#...#
            ###v###.#.#.#########.#
            ###...#.#.#.......#...#
            #####.#.#.#######.#.###
            #.....#.#.#.......#...#
            #.#####.#.#.#########v#
            #.#...#...#...###...>.#
            #.#.#v#######v###.###v#
            #...#.>.#...>.>.#.###.#
            #####v#.#.###v#.#.###.#
            #.....#...#...#.#.#...#
            #.#########.###.#.#.###
            #...###...#...#...#.###
            ###.###.#.###v#####v###
            #...#...#.#.>.>.#.>.###
            #.###.###.#.###.#.#v###
            #.....###...###...#...#
            #####################.#
            """
            .trim().split("\n")).toList();

    private Day23Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day23Part1();
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
        var expected = new Direction[]{Direction.NORTH};
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
        var expected = new Direction[]{Direction.SOUTH};
        var actual = underTest.getNextDirectionsAt(grid, location);
        assertEquals(expected.length, actual.length);
        for (var i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void getNextDirectionsAtWest() {
        var grid = new char[][]{
                {'.', '.', '.'},
                {'.', '<', '.'},
                {'.', '.', '.'},
        };
        var location = new Location(1, 1);
        var expected = new Direction[]{Direction.WEST};
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
        var expected = new Direction[]{Direction.EAST};
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
    void solvesForTestInput() {
        assertEquals(94L, underTest.solve(TEST_DATA));
    }

}
