package org.advent.utils;

import io.vavr.Tuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    @Test
    void testLocationInstantiation() {
        var location = new Location(1, 2);
        assertEquals(1, location.x());
        assertEquals(2, location.y());
    }

    @Test
    void testLocationInstantiationFromTuple() {
        var location = Location.from(Tuple.of(1, 2));
        assertEquals(new Location(1, 2), location);
    }

    @Test
    void testConvertToTuple() {
        var location = new Location(1, 2);
        var tuple = location.toTuple();
        assertEquals(Tuple.of(1, 2), tuple);
    }

    @Test
    void testConvertToRowCol() {
        var location = new Location(1, 2);
        var rowCol = location.toRowCol();
        assertEquals(Tuple.of(2, 1), rowCol);
    }

    @Test
    void testMove() {
        var startLocation = new Location(0, 0);
        var underTest = startLocation.move(Direction.EAST);
        assertEquals(new Location(1, 0), underTest);

        underTest = startLocation.move(Direction.NORTH);
        assertEquals(new Location(0, -1), underTest);

        underTest = startLocation.move(Direction.WEST);
        assertEquals(new Location(-1, 0), underTest);

        underTest = startLocation.move(Direction.SOUTH);
        assertEquals(new Location(0, 1), underTest);
    }

}
