package org.advent.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilsTest {

    @Test
    void splitToIntListThrowsNumberFormatExceptionForInvalidInput() {
        assertThrows(NumberFormatException.class, () -> StringUtils.splitToIntList(""));
        assertThrows(NumberFormatException.class, () -> StringUtils.splitToIntList("hello world"));
    }

    @Test
    void splitsToIntListForIntegers() {
        assertEquals(List.of(1, 2, 3), StringUtils.splitToIntList("1 2 3"));
    }

    @Test
    void splitsToIntListUsingSeparator() {
        assertEquals(List.of(1, 2, 3), StringUtils.splitToIntList("1,2,3", ","));
    }

    @Test
    void canSplitToIntListDespitePaddedWhiteSpaces() {
        assertEquals(List.of(1, 2, 3), StringUtils.splitToIntList("  1 2 3"));
        assertEquals(List.of(1, 2, 3), StringUtils.splitToIntList("1 2 3  "));
        assertEquals(List.of(1, 2, 3), StringUtils.splitToIntList("1   2  3"));
    }

    @Test
    void splitsToLongListForLongs() {
        assertEquals(List.of(1L, 2L, 3866217991L), StringUtils.splitToLongList("1 2 3866217991"));
    }

    @Test
    void parsesSectionsFromLines() {
        var data = """
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
                """;

        var lines = data.lines().toList();
        var underTest = StringUtils.parseSections(lines);

        assertEquals(2, underTest.size());

        assertEquals("""
                        #.##..##.
                        ..#.##.#.
                        ##......#
                        ##......#
                        ..#.##.#.
                        ..##..##.
                        #.#.##.#.
                        """.trim(),
                underTest.getFirst());

        assertEquals("""
                        #...##..#
                        #....#..#
                        ..##..###
                        #####.##.
                        #####.##.
                        ..##..###
                        #....#..#
                        """.trim(),
                underTest.getLast());
    }

    @Test
    void readsLinesFromString() {
        var data = """
                2413432311323
                3215453535623
                3255245654254
                3446585845452
                4546657867536
                1438598798454
                4457876987766
                3637877979653
                4654967986887
                4564679986453
                1224686865563
                2546548887735
                4322674655533
                """;

        var lines = StringUtils.readLines(data);

        assertEquals(13, lines.size());
        assertEquals("2413432311323", lines.getFirst());
        assertEquals("4322674655533", lines.get(12));
    }

}
