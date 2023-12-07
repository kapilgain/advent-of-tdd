package org.advent.day07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
            """
            .trim().split("\n")).toList();

    private Day07Part1 underTest;

    @BeforeEach
    public void setup() {
        underTest = new Day07Part1();
    }

    @Test
    public void solvesForTestInput() {
        assertEquals(6440L, underTest.solve(TEST_DATA));
    }

}
