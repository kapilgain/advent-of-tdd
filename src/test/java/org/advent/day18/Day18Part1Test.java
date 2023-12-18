package org.advent.day18;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
            """
            .trim().split("\n")).toList();

    public static final String TEST_DATA_DIG = """
            #######
            #.....#
            ###...#
            ..#...#
            ..#...#
            ###.###
            #...#..
            ##..###
            .#....#
            .######
            """.trim();

    private Day18Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day18Part1();
    }

    @Test
    void testParseLine() {
        assertEquals('R', underTest.parseLine("R 6 (#70c710)")._1);
        assertEquals(6L, underTest.parseLine("R 6 (#70c710)")._2);
    }

    @Test
    void testGetDigInstructions() {
        var results = underTest.getDigInstructions(TEST_DATA);
        assertEquals(14, results.size());
        assertEquals('R', results.getFirst()._1);
        assertEquals(6L, results.getFirst()._2);
        assertEquals('U', results.getLast()._1);
        assertEquals(2L, results.getLast()._2);
    }

    @Test
    void testDig() {
        var instructions = underTest.getDigInstructions(TEST_DATA);
        var results = underTest.dig(instructions);
        assertEquals(38, results._1.size());
        assertEquals(TEST_DATA_DIG, results._2.toString());
    }

    @Test
    void solvesForTestInput() {
        assertEquals(62L, underTest.solve(TEST_DATA));
    }

}
