package org.advent.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointerTest {

    @Test
    void testPointerMove() {
        var underTest = new Pointer(new Location(0, 0), Direction.EAST).move();
        assertEquals(new Pointer(new Location(1, 0), Direction.EAST), underTest);

        underTest = new Pointer(new Location(0, 0), Direction.NORTH).move();
        assertEquals(new Pointer(new Location(0, -1), Direction.NORTH), underTest);

        underTest = new Pointer(new Location(0, 0), Direction.WEST).move();
        assertEquals(new Pointer(new Location(-1, 0), Direction.WEST), underTest);

        underTest = new Pointer(new Location(0, 0), Direction.SOUTH).move();
        assertEquals(new Pointer(new Location(0, 1), Direction.SOUTH), underTest);
    }

    @Test
    void testPointerTurnLeft() {
        var underTest = new Pointer(new Location(0, 0), Direction.EAST).turnLeft();
        assertEquals(new Pointer(new Location(0, 0), Direction.NORTH), underTest);

        underTest = new Pointer(new Location(0, 0), Direction.NORTH).turnLeft();
        assertEquals(new Pointer(new Location(0, 0), Direction.WEST), underTest);

        underTest = new Pointer(new Location(0, 0), Direction.WEST).turnLeft();
        assertEquals(new Pointer(new Location(0, 0), Direction.SOUTH), underTest);

        underTest = new Pointer(new Location(0, 0), Direction.SOUTH).turnLeft();
        assertEquals(new Pointer(new Location(0, 0), Direction.EAST), underTest);
    }

    @Test
    void testPointerTurnRight() {
        var underTest = new Pointer(new Location(0, 0), Direction.EAST).turnRight();
        assertEquals(new Pointer(new Location(0, 0), Direction.SOUTH), underTest);

        underTest = new Pointer(new Location(0, 0), Direction.NORTH).turnRight();
        assertEquals(new Pointer(new Location(0, 0), Direction.EAST), underTest);

        underTest = new Pointer(new Location(0, 0), Direction.WEST).turnRight();
        assertEquals(new Pointer(new Location(0, 0), Direction.NORTH), underTest);

        underTest = new Pointer(new Location(0, 0), Direction.SOUTH).turnRight();
        assertEquals(new Pointer(new Location(0, 0), Direction.WEST), underTest);
    }

}
