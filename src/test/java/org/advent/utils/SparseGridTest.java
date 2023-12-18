package org.advent.utils;

import io.vavr.Tuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SparseGridTest {

    @Test
    void testSet() {
        var underTest = new SparseGrid<Character>();
        underTest.set(Tuple.of(0, 1), 'a');
        underTest.set(Tuple.of(1, 0), 'b');
        assertEquals(underTest.getGrid().get(Tuple.of(0, 1)), 'a');
        assertEquals(underTest.getGrid().get(Tuple.of(1, 0)), 'b');
    }

    @Test
    void testGet() {
        var underTest = new SparseGrid<Character>();
        underTest.set(Tuple.of(0, 1), 'a');
        underTest.set(Tuple.of(1, 0), 'b');
        assertEquals(underTest.get(Tuple.of(0, 1)), 'a');
        assertEquals(underTest.get(Tuple.of(1, 0)), 'b');
    }

    @Test
    void testToString() {
        var underTest = new SparseGrid<Character>();
        underTest.set(Tuple.of(0, 1), 'a');
        underTest.set(Tuple.of(1, 0), 'b');
        assertEquals("""
                .a
                b.
                """.trim(), underTest.toString());
    }

}
