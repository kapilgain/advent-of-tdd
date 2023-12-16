package org.advent.day16;

import io.vavr.Tuple;
import org.advent.utils.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhotonTest {

    @Test
    void testPhotonMove() {
        var underTest = new Photon(Tuple.of(0, 0), Direction.EAST).move();
        assertEquals(Tuple.of(1, 0), underTest.location());
        assertEquals(Direction.EAST, underTest.direction());

        underTest = new Photon(Tuple.of(0, 0), Direction.NORTH).move();
        assertEquals(Tuple.of(0, -1), underTest.location());
        assertEquals(Direction.NORTH, underTest.direction());

        underTest = new Photon(Tuple.of(0, 0), Direction.WEST).move();
        assertEquals(Tuple.of(-1, 0), underTest.location());
        assertEquals(Direction.WEST, underTest.direction());

        underTest = new Photon(Tuple.of(0, 0), Direction.SOUTH).move();
        assertEquals(Tuple.of(0, 1), underTest.location());
        assertEquals(Direction.SOUTH, underTest.direction());
    }

    @Test
    void testPhotonTurnLeft() {
        var underTest = new Photon(Tuple.of(0, 0), Direction.EAST).turnLeft();
        assertEquals(Tuple.of(0, 0), underTest.location());
        assertEquals(Direction.NORTH, underTest.direction());

        underTest = new Photon(Tuple.of(0, 0), Direction.NORTH).turnLeft();
        assertEquals(Tuple.of(0, 0), underTest.location());
        assertEquals(Direction.WEST, underTest.direction());

        underTest = new Photon(Tuple.of(0, 0), Direction.WEST).turnLeft();
        assertEquals(Tuple.of(0, 0), underTest.location());
        assertEquals(Direction.SOUTH, underTest.direction());

        underTest = new Photon(Tuple.of(0, 0), Direction.SOUTH).turnLeft();
        assertEquals(Tuple.of(0, 0), underTest.location());
        assertEquals(Direction.EAST, underTest.direction());
    }

    @Test
    void testPhotonTurnRight() {
        var underTest = new Photon(Tuple.of(0, 0), Direction.EAST).turnRight();
        assertEquals(Tuple.of(0, 0), underTest.location());
        assertEquals(Direction.SOUTH, underTest.direction());

        underTest = new Photon(Tuple.of(0, 0), Direction.NORTH).turnRight();
        assertEquals(Tuple.of(0, 0), underTest.location());
        assertEquals(Direction.EAST, underTest.direction());

        underTest = new Photon(Tuple.of(0, 0), Direction.WEST).turnRight();
        assertEquals(Tuple.of(0, 0), underTest.location());
        assertEquals(Direction.NORTH, underTest.direction());

        underTest = new Photon(Tuple.of(0, 0), Direction.SOUTH).turnRight();
        assertEquals(Tuple.of(0, 0), underTest.location());
        assertEquals(Direction.WEST, underTest.direction());
    }

}
