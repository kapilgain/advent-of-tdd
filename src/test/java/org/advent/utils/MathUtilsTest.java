package org.advent.utils;

import io.vavr.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    private MathUtils underTest;

    @BeforeEach
    public void setup() {
        underTest = new MathUtils();
    }

    @Test
    public void sumsCollectionOfIntegers() {
        assertEquals(10, underTest.sum(List.of(1, 2, 3, 4)));
    }

    @Test
    public void solvesQuadraticEquations() {
        // Solve (x - 2) * (x - 3) = 0
        var a = 1;
        var b = -5;
        var c = 6;
        assertEquals(Tuple.of(2.0, 3.0), underTest.solveQuadratic(1, -5, 6));
    }

}
