package org.advent.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Location3Test {

    @Test
    void testParse() {
        var underTest = Location3.parse("1,2,3");
        assertEquals(1, underTest.x());
        assertEquals(2, underTest.y());
        assertEquals(3, underTest.z());
    }

    @Test
    void testToLocation() {
        var underTest = Location3.parse("1,2,3").toLocation();
        assertEquals(1, underTest.x());
        assertEquals(2, underTest.y());
    }

}
