package org.advent.utils;

import io.vavr.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    @Test
    public void sumsCollectionOfLongs() {
        assertEquals(10L, MathUtils.sum(List.of(1L, 2L, 3L, 4L)));
    }

    @Test
    public void solvesQuadraticEquations() {
        // Solve (x - 2) * (x - 3) = 0
        assertEquals(Tuple.of(2.0, 3.0), MathUtils.solveQuadratic(1, -5, 6));
    }

}
