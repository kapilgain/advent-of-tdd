package org.advent.day24;

import io.vavr.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.advent.day24.Day24Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Part2Test {

    private Day24Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day24Part2();
    }

    @Test
    void findsRockStartingCoordinates() {
        assertEquals(
                Set.of(
                        // The site only mentions the first solution, but I found the second one as well :)
                        Tuple.of(24.0, 13.0, 10.0),
                        Tuple.of(22.0, 19.0, 6.0)
                ),
                underTest.findPossibleRockStartingCoordinates(TEST_DATA)
        );
    }

    @Test
    void calculatesFeasibleVelocities() {
        var hails = underTest.parseLines(TEST_DATA);
        assertEquals(Set.of(-3L), underTest.calculateFeasibleVelocities(hails, 0));
        assertEquals(new TreeSet<>(Set.of(-8L, -5L, -4L, -3L, -1L, 1L, 4L)), underTest.calculateFeasibleVelocities(hails, 1));
        assertEquals(new TreeSet<>(Set.of(-10L, -6L, -4L, -3L, -1L, 2L, 6L)), underTest.calculateFeasibleVelocities(hails, 2));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(47L, underTest.solve(TEST_DATA));
    }

}
