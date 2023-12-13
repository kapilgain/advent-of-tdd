package org.advent.day13;

import org.advent.utils.GridUtils;
import org.advent.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.
                        
            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
            """
            .trim().split("\n")).toList();

    private Day13Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day13Part1();
    }

    @Test
    void testFindHorizontalReflectionLine() {
        var sections = StringUtils.parseSections(TEST_DATA);

        var grid = GridUtils.createGrid(sections.getFirst().lines().toList());
        assertEquals(0, underTest.findHorizontalReflectionLines(grid).size());

        grid = GridUtils.createGrid(sections.getLast().lines().toList());
        assertEquals(4, underTest.findHorizontalReflectionLines(grid).getFirst());
    }

    @Test
    void testFindVerticalReflectionLine() {
        var sections = StringUtils.parseSections(TEST_DATA);

        var grid = GridUtils.createGrid(sections.getFirst().lines().toList());
        assertEquals(5, underTest.findVerticalReflectionLines(grid).getFirst());

        grid = GridUtils.createGrid(sections.getLast().lines().toList());
        assertEquals(0, underTest.findVerticalReflectionLines(grid).size());
    }

    @Test
    void solvesForTestInput() {
        assertEquals(405L, underTest.solve(TEST_DATA));
    }

}
