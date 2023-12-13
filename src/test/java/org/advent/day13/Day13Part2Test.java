package org.advent.day13;

import io.vavr.Tuple;
import org.advent.utils.GridUtils;
import org.advent.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day13.Day13Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Part2Test {

    private Day13Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day13Part2();
    }

    @Test
    void fixesSmudgeAndFindsNewReflectionLine() {
        var sections = StringUtils.parseSections(TEST_DATA);

        var grid = GridUtils.createGrid(sections.getFirst().lines().toList());
        assertEquals(Tuple.of(3, 0), underTest.fixSmudge(grid));

        grid = GridUtils.createGrid(sections.getLast().lines().toList());
        assertEquals(Tuple.of(1, 0), underTest.fixSmudge(grid));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(400L, underTest.solve(TEST_DATA));
    }

}
