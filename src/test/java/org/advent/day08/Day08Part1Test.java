package org.advent.day08;

import io.vavr.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Part1Test {

    public static final List<String> TEST_DATA_1 = Arrays.stream("""
            RL
                        
            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
            """
            .trim().split("\n")).toList();

    public static final List<String> TEST_DATA_2 = Arrays.stream("""
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
            """
            .trim().split("\n")).toList();

    private Day08Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day08Part1();
    }

    @Test
    void parsesNetworkNodeIntoTuple() {
        assertEquals(Tuple.of("AAA", "BBB", "CCC"), underTest.parseNetworkNode("AAA = (BBB, CCC)"));
    }

    @Test
    void createsNetworkMap() {
        var map = underTest.createNetworkMap(TEST_DATA_2);
        assertEquals(Tuple.of("BBB", "BBB"), map.get("AAA"));
        assertEquals(Tuple.of("AAA", "ZZZ"), map.get("BBB"));
        assertEquals(Tuple.of("ZZZ", "ZZZ"), map.get("ZZZ"));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(2, underTest.solve(TEST_DATA_1));
        assertEquals(6, underTest.solve(TEST_DATA_2));
    }

}
