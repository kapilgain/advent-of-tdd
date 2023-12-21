package org.advent.day21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.advent.day21.Day21Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Part2Test {

    private Day21Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day21Part2();
    }

    @Test
    void testRepeatMapNineTimes() {
        assertEquals("""
                        .................................
                        .....###.#......###.#......###.#.
                        .###.##..#..###.##..#..###.##..#.
                        ..#.#...#....#.#...#....#.#...#..
                        ....#.#........#.#........#.#....
                        .##...####..##...####..##...####.
                        .##..#...#..##..#...#..##..#...#.
                        .......##.........##.........##..
                        .##.#.####..##.#.####..##.#.####.
                        .##..##.##..##..##.##..##..##.##.
                        .................................
                        .................................
                        .....###.#......###.#......###.#.
                        .###.##..#..###.##..#..###.##..#.
                        ..#.#...#....#.#...#....#.#...#..
                        ....#.#........#.#........#.#....
                        .##...####..##..S####..##...####.
                        .##..#...#..##..#...#..##..#...#.
                        .......##.........##.........##..
                        .##.#.####..##.#.####..##.#.####.
                        .##..##.##..##..##.##..##..##.##.
                        .................................
                        .................................
                        .....###.#......###.#......###.#.
                        .###.##..#..###.##..#..###.##..#.
                        ..#.#...#....#.#...#....#.#...#..
                        ....#.#........#.#........#.#....
                        .##...####..##...####..##...####.
                        .##..#...#..##..#...#..##..#...#.
                        .......##.........##.........##..
                        .##.#.####..##.#.####..##.#.####.
                        .##..##.##..##..##.##..##..##.##.
                        .................................
                        """.trim(),
                String.join("\n", underTest.repeatMapNineTimes(TEST_DATA)).trim()
        );
    }

    @Test
    void solvesForTestInputSmallerMaps() {
        // Test data has grid size of 11x11
        // For a given number of steps, we will repeat thw grid enough times so that the size is large enough to serve
        // as infinite map for the given test case
        var map = underTest.repeatMapNineTimes(TEST_DATA);
        assertEquals(16, underTest.countFor(6, map));
        assertEquals(50, underTest.countFor(10, map));

        map = underTest.repeatMapNineTimes(map);
        assertEquals(1594, underTest.countFor(50, map));

        map = underTest.repeatMapNineTimes(map);
        assertEquals(6536, underTest.countFor(100, map));
    }

    @Test
    // This test is slow so disabling it in local environment
    @DisabledIfEnvironmentVariable(named = "local", matches = "true")
    void solvesForTestInputLargerMaps() {
        var map = underTest.repeatMapNineTimes(TEST_DATA);
        map = underTest.repeatMapNineTimes(map);
        map = underTest.repeatMapNineTimes(map);
        map = underTest.repeatMapNineTimes(map);
        map = underTest.repeatMapNineTimes(map);
        assertEquals(167004, underTest.countFor(500, map));
        assertEquals(668697, underTest.countFor(1000, map));
    }

}
