package org.advent.day08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Part2Test {

    public static final List<String> TEST_DATA_3 = Arrays.stream("""
            LR
                            
            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
            """
            .trim().split("\n")).toList();

    private Day08Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day08Part2();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(6L, underTest.solve(TEST_DATA_3));
    }

}
