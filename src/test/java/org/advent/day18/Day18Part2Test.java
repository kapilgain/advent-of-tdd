package org.advent.day18;

import org.advent.utils.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.advent.day18.Day18Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Part2Test {

    private Day18Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day18Part2();
    }

    @Test
    void testParseLine() {
        assertEquals('0', underTest.parseLine("R 6 (#70c710)")._1);
        assertEquals(461_937L, underTest.parseLine("R 6 (#70c710)")._2);
    }

    @Test
    void testParseDirection() {
        assertEquals(Direction.EAST, underTest.parseDirection('0'));
        assertEquals(Direction.SOUTH, underTest.parseDirection('1'));
        assertEquals(Direction.WEST, underTest.parseDirection('2'));
        assertEquals(Direction.NORTH, underTest.parseDirection('3'));
    }

    @Test
    // This test is slow so disabling it in local environment
    @DisabledIfEnvironmentVariable(named = "local", matches = "true")
    void solvesForTestInput() {
        assertEquals(952_408_144_115L, underTest.solve(TEST_DATA));
    }

}
