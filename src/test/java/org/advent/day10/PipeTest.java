package org.advent.day10;

import io.vavr.Tuple;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.advent.day10.Pipe.*;
import static org.advent.utils.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PipeTest {

    @Test
    void testPipeHasCorrectLabelAndConnections() {
        assertEquals('|', PIPE_NS.getLabel());
        assertEquals(Set.of(NORTH, SOUTH), PIPE_NS.getConnections());

        assertEquals('-', PIPE_EW.getLabel());
        assertEquals(Set.of(EAST, WEST), PIPE_EW.getConnections());
    }

    @Test
    void testFromLabelReturnsCorrectPipeEnum() {
        assertEquals(PIPE_NS, fromLabel('|'));
        assertEquals(PIPE_EW, fromLabel('-'));
        assertEquals(PIPE_NE, fromLabel('L'));
        assertEquals(PIPE_NW, fromLabel('J'));
        assertEquals(PIPE_SW, fromLabel('7'));
        assertEquals(PIPE_SE, fromLabel('F'));
    }


    @Test
    void testFromLabelReturnsNullForInvalidLabel() {
        assertNull(fromLabel('.'));
        assertNull(fromLabel('S'));
    }

    @Test
    void calculatesConnectingLocationsForPipeFromGivenLocation() {
        var origin = Tuple.of(0, 0);
        var north = Tuple.of(0, -1);
        var south = Tuple.of(0, 1);
        var east = Tuple.of(1, 0);
        var west = Tuple.of(-1, 0);

        assertEquals(Set.of(north, south), PIPE_NS.connectingLocations(origin));
        assertEquals(Set.of(east, west), PIPE_EW.connectingLocations(origin));
        assertEquals(Set.of(north, east), PIPE_NE.connectingLocations(origin));
        assertEquals(Set.of(north, west), PIPE_NW.connectingLocations(origin));
        assertEquals(Set.of(south, west), PIPE_SW.connectingLocations(origin));
        assertEquals(Set.of(south, east), PIPE_SE.connectingLocations(origin));
    }

}
