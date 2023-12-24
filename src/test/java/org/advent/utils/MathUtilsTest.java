package org.advent.utils;

import io.vavr.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void solvesLinearCoefficients() {
        // Solve 2x + 3 = f(x)
        assertEquals(
                Tuple.of(2.0, 3.0),
                MathUtils.solveLinearCoefficients(
                        Tuple.of(0L, 3L),
                        Tuple.of(1L, 5L)
                )
        );
    }

    @Test
    void roundsToThreeDecimalPlaces() {
        assertEquals(1.000, MathUtils.round(1.0004, 3));
        assertEquals(2.000, MathUtils.round(1.9998, 3));
        assertEquals(-5.000, MathUtils.round(-4.9998, 3));
    }

    @Test
    void findsIntersectionPointOfTwoLines() {
        // y = x + 1 <=> -x + y = 1
        // y = 2x + 7 <=> -2x + y = 7
        assertEquals(
                Tuple.of(-6.0, -5.0),
                MathUtils.intersectLines(
                        Tuple.of(-1.0, 1.0, 1.0),
                        Tuple.of(-2.0, 1.0, 7.0)
                )
        );
    }

    @Test
    void returnsNullIfLinesAreParallel() {
        // y = x + 1 <=> -x + y = 1
        // y = x + 7 <=> -x + y = 7
        assertNull(MathUtils.intersectLines(
                Tuple.of(-1.0, 1.0, 1.0),
                Tuple.of(-1.0, 1.0, 7.0)
        ));

    }

    @Test
    void findsFactors() {
        assertEquals(Set.of(1L, 2L, 3L, 4L, 6L, 12L), MathUtils.findFactors(12));
    }

}
