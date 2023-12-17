package org.advent.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CrucibleTest {

    @Test
    void testCompareTo() {
        Crucible c1 = new Crucible(1, new Pointer(new Location(2, 3), Direction.NORTH), 0);
        Crucible c2 = new Crucible(1, new Pointer(new Location(4, 5), Direction.EAST), 1);
        Crucible c3 = new Crucible(10, new Pointer(new Location(11, 12), Direction.SOUTH), 2);

        assertEquals(0, c1.compareTo(c2));
        assertEquals(0, c2.compareTo(c1));
        assertTrue(c1.compareTo(c3) < 0);
        assertTrue(c3.compareTo(c1) > 0);
    }

    @Test
    void testHashCode() {
        Crucible c1 = new Crucible(1, new Pointer(new Location(2, 3), Direction.NORTH), 0);
        Crucible c2 = new Crucible(10, new Pointer(new Location(2, 3), Direction.NORTH), 0);
        Crucible c3 = new Crucible(10, new Pointer(new Location(2, 3), Direction.SOUTH), 1);

        assertEquals(c1.hashCode(), c2.hashCode());
        assertTrue(c1.hashCode() != c3.hashCode());
    }

    @Test
    void testEquals() {
        Crucible c1 = new Crucible(1, new Pointer(new Location(2, 3), Direction.NORTH), 0);
        Crucible c2 = new Crucible(10, new Pointer(new Location(2, 3), Direction.NORTH), 0);
        Crucible c3 = new Crucible(10, new Pointer(new Location(2, 3), Direction.SOUTH), 1);

        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
    }

}
