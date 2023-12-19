package org.advent.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartTest {

    @Test
    void testInitialisation() {
        var underTest = new Part(1, 2, 3, 4);
        assertEquals(1, underTest.x());
        assertEquals(2, underTest.m());
        assertEquals(3, underTest.a());
        assertEquals(4, underTest.s());
    }

    @Test
    void testCalculateChristmasRating() {
        var underTest = new Part(1, 2, 3, 4);
        assertEquals(10, underTest.calculateChristmasRating());
    }

    @Test
    void testParse() {
        assertEquals(new Part(1, 2, 3, 4), Part.parse("{x=1,m=2,a=3,s=4}"));
    }

}
