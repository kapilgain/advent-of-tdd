package org.advent.day04;

import io.vavr.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Part1Test {

    private Day04Part1 underTest;

    @BeforeEach
    public void setup() {
        underTest = new Day04Part1();
    }

    @Test
    public void parsesCardId() {
        assertEquals(1, underTest.parseCardId("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"));
        assertEquals(2, underTest.parseCardId("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"));
    }

    @Test
    public void parsesWinners() {
        assertEquals(
                List.of(41, 48, 83, 86, 17),
                underTest.parseWinners("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        );

        assertEquals(
                List.of(13, 32, 20, 16, 61),
                underTest.parseWinners("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19")
        );
    }

    @Test
    public void parsesScratchedCards() {
        assertEquals(
                List.of(83, 86, 6, 31, 17, 9, 48, 53),
                underTest.parseScratched("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        );

        assertEquals(
                List.of(61, 30, 68, 82, 17, 32, 24, 19),
                underTest.parseScratched("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19")
        );
    }

    @Test
    public void parsesLineIntoTuples() {
        assertEquals(
                Tuple.of(1, List.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53)),
                underTest.parseLine("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        );

        assertEquals(
                Tuple.of(2, List.of(13, 32, 20, 16, 61), List.of(61, 30, 68, 82, 17, 32, 24, 19)),
                underTest.parseLine("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19")
        );
    }

    @Test
    public void countMatches() {
        assertEquals(4, underTest.countMatches("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"));
        assertEquals(2, underTest.countMatches("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"));
        assertEquals(2, underTest.countMatches("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"));
        assertEquals(1, underTest.countMatches("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"));
        assertEquals(0, underTest.countMatches("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"));
        assertEquals(0, underTest.countMatches("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"));
    }

    @Test
    public void calculatesPointsFromNumberOfMatches() {
        assertEquals(0, underTest.toPoints(-1));
        assertEquals(0, underTest.toPoints(0));
        assertEquals(1, underTest.toPoints(1));
        assertEquals(2, underTest.toPoints(2));
        assertEquals(4, underTest.toPoints(3));
        assertEquals(8, underTest.toPoints(4));
    }

    @Test
    public void solvesPartOneSampleInput() {
        var lines = Arrays.stream("""
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"""
                .trim().split("\n")).toList();

        assertEquals(13, underTest.solve(lines));
    }

}
