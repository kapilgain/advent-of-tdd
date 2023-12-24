package org.advent.day24;

import io.vavr.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            19, 13, 30 @ -2,  1, -2
            18, 19, 22 @ -1, -1, -2
            20, 25, 34 @ -2, -2, -4
            12, 31, 28 @ -1, -2, -1
            20, 19, 15 @  1, -5, -3
            """
            .trim().split("\n")).toList();

    private Day24Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day24Part1();
    }

    @Test
    void parseLines() {
        assertEquals(List.of(
                Tuple.of(19L, 13L, 30L, -2L, 1L, -2L),
                Tuple.of(18L, 19L, 22L, -1L, -1L, -2L),
                Tuple.of(20L, 25L, 34L, -2L, -2L, -4L),
                Tuple.of(12L, 31L, 28L, -1L, -2L, -1L),
                Tuple.of(20L, 19L, 15L, 1L, -5L, -3L)
        ), underTest.parseLines(TEST_DATA));
    }

    @Test
    void processLinesToStandardLineEquation() {
        assertEquals(List.of(
                Tuple.of(-0.5, 22.5),
                Tuple.of(1.0, 1.0),
                Tuple.of(1.0, 5.0),
                Tuple.of(2.0, 7.0),
                Tuple.of(-5.0, 119.0)
        ), underTest.processLines(underTest.parseLines(TEST_DATA)));
    }

    @Test
    void countsIntersectionPoints() {
        assertEquals(2, underTest.countIntersectionPoints(TEST_DATA, Tuple.of(7L, 27L)));
    }

    @Test
    void findsIntersectionPointWithinTestArea() {
        assertEquals(Tuple.of(14.333333333333334, 15.333333333333334), underTest.findIntersectionPoint(
                Tuple.of(-0.5, 22.5),
                Tuple.of(1.0, 1.0)
        ));
    }

    @Test
    void calculatesTime() {
        assertEquals(5.0, underTest.calculateTime(0.0, 10.0, 2));
        assertEquals(-5.0, underTest.calculateTime(10.0, 0.0, 2));
    }

}
