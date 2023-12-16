package org.advent.utils;

import io.vavr.Tuple;
import org.junit.jupiter.api.Test;

import static org.advent.utils.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DirectionTest {

    @Test
    void testNorthHasCorrectCoordinates() {
        assertEquals(0, NORTH.getX());
        assertEquals(-1, NORTH.getY());
    }

    @Test
    void testSouthHasCorrectCoordinates() {
        assertEquals(0, SOUTH.getX());
        assertEquals(1, SOUTH.getY());
    }

    @Test
    void testEastHasCorrectCoordinates() {
        assertEquals(1, EAST.getX());
        assertEquals(0, EAST.getY());
    }

    @Test
    void testWestHasCorrectCoordinates() {
        assertEquals(-1, WEST.getX());
        assertEquals(0, WEST.getY());
    }

    @Test
    void testFromReturnsCorrectDirection() {
        assertEquals(NORTH, from(0, -1));
        assertEquals(SOUTH, from(0, 1));
        assertEquals(EAST, from(1, 0));
        assertEquals(WEST, from(-1, 0));
    }

    @Test
    void testFromReturnsNullForInvalidVectors() {
        assertNull(from(1, 1));
    }

    @Test
    void testAddDirectionToVector() {
        var origin = Tuple.of(0, 0);
        var north = Tuple.of(0, -1);
        var south = Tuple.of(0, 1);
        var east = Tuple.of(1, 0);
        var west = Tuple.of(-1, 0);

        assertEquals(north, NORTH.addTo(origin));
        assertEquals(south, SOUTH.addTo(origin));
        assertEquals(east, EAST.addTo(origin));
        assertEquals(west, WEST.addTo(origin));
    }

    @Test
    void testTurnLeft() {
        assertEquals(WEST, NORTH.turnLeft());
        assertEquals(EAST, SOUTH.turnLeft());
        assertEquals(NORTH, EAST.turnLeft());
        assertEquals(SOUTH, WEST.turnLeft());
    }

    @Test
    void testTurnRight() {
        assertEquals(EAST, NORTH.turnRight());
        assertEquals(WEST, SOUTH.turnRight());
        assertEquals(SOUTH, EAST.turnRight());
        assertEquals(NORTH, WEST.turnRight());
    }

}
