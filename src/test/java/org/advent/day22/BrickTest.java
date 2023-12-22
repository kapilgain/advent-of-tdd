package org.advent.day22;

import org.advent.utils.Location3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BrickTest {

    @Test
    void testParse() {
        var underTest = Brick.parse("1,2,3~4,5,6");
        assertEquals(new Location3(1, 2, 3), underTest.end1());
        assertEquals(new Location3(4, 5, 6), underTest.end2());
    }

    @Test
    void testCompare() {
        assertEquals(-1, Brick.parse("1,0,1~1,2,1").compareTo(Brick.parse("0,0,2~2,0,2")));
        assertEquals(1, Brick.parse("0,0,2~2,0,2").compareTo(Brick.parse("1,0,1~1,2,1")));
        assertEquals(0, Brick.parse("0,0,2~2,0,2").compareTo(Brick.parse("0,0,2~2,0,2")));
    }

    @Test
    void testIntersectWithSelf() {
        var brickA = Brick.parse("1,2,3~4,5,6");
        assertTrue(brickA.intersects(brickA));
    }

    @Test
    void testIntersectWithOther() {
        var brickA = Brick.parse("1,0,1~1,2,1");
        var brickB = Brick.parse("0,0,2~2,0,2");
        assertTrue(brickA.intersects(brickB));
        assertTrue(brickB.intersects(brickA));

        var brickC = Brick.parse("0,2,3~2,2,3");
        assertFalse(brickB.intersects(brickC));
        assertFalse(brickC.intersects(brickB));

        var brickD = Brick.parse("0,0,4~0,2,4");
        var brickE = Brick.parse("2,0,5~2,2,5");
        assertFalse(brickD.intersects(brickE));
        assertFalse(brickE.intersects(brickD));
    }

    @Test
    void zeroFallReturnsSame() {
        var underTest = Brick.parse("0,1,6~2,1,6").fallZBy(0);
        assertEquals(Brick.parse("0,1,6~2,1,6"), underTest);
    }

    @Test
    void testFallZBy() {
        var underTest = Brick.parse("0,1,6~2,1,6").fallZBy(2);
        assertEquals(new Location3(0, 1, 4), underTest.end1());
        assertEquals(new Location3(2, 1, 4), underTest.end2());

        // Fall further
        underTest = underTest.fallZBy(3);
        assertEquals(new Location3(0, 1, 1), underTest.end1());
        assertEquals(new Location3(2, 1, 1), underTest.end2());
    }

    @Test
    void testFallTallBrick() {
        var underTest = Brick.parse("1,1,8~1,1,9").fallZBy(7);
        assertEquals(new Location3(1, 1, 1), underTest.end1());
        assertEquals(new Location3(1, 1, 2), underTest.end2());
    }

}
