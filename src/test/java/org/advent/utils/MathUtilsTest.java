package org.advent.utils;

import io.vavr.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathUtilsTest {

    @Test
    void sumsCollectionOfLongs() {
        assertEquals(10L, MathUtils.sum(List.of(1L, 2L, 3L, 4L)));
    }

    @Test
    void solvesQuadraticEquations() {
        // Solve (x - 2) * (x - 3) = 0
        assertEquals(Tuple.of(2.0, 3.0), MathUtils.solveQuadratic(1, -5, 6));
    }

    @Test
    void solvesQuadraticCoefficients() {
        // Solve (x - 2) * (x - 3) = f(x)
        assertEquals(Tuple.of(1.0, -5.0, 6.0), MathUtils.solveQuadraticCoefficients(
                Tuple.of(2L, 0L),
                Tuple.of(3L, 0L),
                Tuple.of(0L, 6L)
        ));
    }

    @Test
    void closesTheLoopByAddingLastBoundaryPointIfNeededWhenCalculatingPolygonArea() {
        assertEquals(1.0, MathUtils.polygonArea(List.of(
                Tuple.of(0, 0),
                Tuple.of(0, 1),
                Tuple.of(1, 1),
                Tuple.of(1, 0)
        )));
    }

    @Test
    void calculatesPolygonArea() {
        assertEquals(1.0, MathUtils.polygonArea(List.of(
                Tuple.of(0, 0),
                Tuple.of(0, 1),
                Tuple.of(1, 1),
                Tuple.of(1, 0),
                Tuple.of(0, 0)
        )));
    }

    @Test
    void calculatesManhattanDistanceBetweenTwoPoints() {
        assertEquals(4, MathUtils.manhattanDistance(Tuple.of(2, 2), Tuple.of(3, 5)));
    }

    @Test
    void calculatesIntersectionOfTwoRanges() {
        assertEquals(Tuple.of(5, 10), MathUtils.intersectRange(Tuple.of(1, 10), Tuple.of(5, 15)));
        assertEquals(Tuple.of(5, 10), MathUtils.intersectRange(Tuple.of(5, 15), Tuple.of(1, 10)));
        assertEquals(Tuple.of(5, 5), MathUtils.intersectRange(Tuple.of(5, 10), Tuple.of(1, 5)));
        assertEquals(Tuple.of(0, 0), MathUtils.intersectRange(Tuple.of(1, 4), Tuple.of(5, 10)));
    }

    @Test
    void throwsExceptionIfEmptyListPassedToLCM() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.lcm(null));
        assertThrows(IllegalArgumentException.class, () -> MathUtils.lcm(List.of()));
    }

    @Test
    void calculatesLCM() {
        assertEquals(2, MathUtils.lcm(List.of(2L)));
        assertEquals(6, MathUtils.lcm(List.of(2L, 3L)));
        assertEquals(12, MathUtils.lcm(List.of(2L, 3L, 4L)));
    }

}
