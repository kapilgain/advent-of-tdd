package org.advent.day19;

import io.vavr.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day19.Day19Part1Test.TEST_DATA;
import static org.advent.day19.Day19Part1Test.TEST_WORKFLOW_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Part2Test {

    private Day19Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day19Part2();
    }

    @Test
    void testCountForTestInput() {
        var workflowMap = underTest.createWorkflowMap(TEST_WORKFLOW_DATA);
        var ranges = io.vavr.collection.HashMap.of(
                'x', Tuple.of(1, 4000),
                'm', Tuple.of(1, 4000),
                'a', Tuple.of(1, 4000),
                's', Tuple.of(1, 4000)
        );

        assertEquals(
                167_409_079_868_000L,
                underTest.countAcceptedCombinations(ranges, workflowMap, "in")
        );
    }

    @Test
    void solvesForTestInput() {
        assertEquals(167_409_079_868_000L, underTest.solve(TEST_DATA));
    }

}
