package org.advent.day22;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day22Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            1,0,1~1,2,1
            0,0,2~2,0,2
            0,2,3~2,2,3
            0,0,4~0,2,4
            2,0,5~2,2,5
            0,1,6~2,1,6
            1,1,8~1,1,9
            """
            .trim().split("\n")).toList();

    public static final Brick BRICK_A = Brick.parse("1,0,1~1,2,1");
    public static final Brick BRICK_B = Brick.parse("0,0,2~2,0,2");
    public static final Brick BRICK_C = Brick.parse("0,2,3~2,2,3");
    public static final Brick BRICK_D = Brick.parse("0,0,4~0,2,4");
    public static final Brick BRICK_E = Brick.parse("2,0,5~2,2,5");
    public static final Brick BRICK_F = Brick.parse("0,1,6~2,1,6");
    public static final Brick BRICK_G = Brick.parse("1,1,8~1,1,9");

    public static final Brick FALLEN_BRICK_A = Brick.parse("1,0,1~1,2,1");
    public static final Brick FALLEN_BRICK_B = Brick.parse("0,0,2~2,0,2");
    public static final Brick FALLEN_BRICK_C = Brick.parse("0,2,2~2,2,2");
    public static final Brick FALLEN_BRICK_D = Brick.parse("0,0,3~0,2,3");
    public static final Brick FALLEN_BRICK_E = Brick.parse("2,0,3~2,2,3");
    public static final Brick FALLEN_BRICK_F = Brick.parse("0,1,4~2,1,4");
    public static final Brick FALLEN_BRICK_G = Brick.parse("1,1,5~1,1,6");

    private Day22Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day22Part1();
    }

    @Test
    void parsesBricks() {
        var bricks = underTest.parseBricks(TEST_DATA);
        assertEquals(List.of(BRICK_A, BRICK_B, BRICK_C, BRICK_D, BRICK_E, BRICK_F, BRICK_G), bricks);
    }

    @Test
    void sortsBricks() {
        var bricks = underTest.parseBricks(TEST_DATA);
        // we do a reverse, because the test input provided is already sorted
        var sorted = underTest.sortBricks(bricks.reversed());
        assertEquals(List.of(BRICK_A, BRICK_B, BRICK_C, BRICK_D, BRICK_E, BRICK_F, BRICK_G), sorted);
    }

    @Test
    void testBricksAfterFall() {
        var bricks = underTest.parseBricks(TEST_DATA);
        var fallen = underTest.fallBricks(bricks);
        assertEquals(FALLEN_BRICK_A, fallen.getFirst());
        assertEquals(FALLEN_BRICK_B, fallen.get(1));
        assertEquals(FALLEN_BRICK_C, fallen.get(2));
        assertEquals(FALLEN_BRICK_D, fallen.get(3));
        assertEquals(FALLEN_BRICK_E, fallen.get(4));
        assertEquals(FALLEN_BRICK_F, fallen.get(5));
        assertEquals(FALLEN_BRICK_G, fallen.getLast());
    }

    @Test
    void testSupportStructureAnalysis() {
        var bricks = underTest.parseBricks(TEST_DATA);
        var fallen = underTest.fallBricks(bricks);
        var supportStructure = underTest.analyseSupportStructure(fallen);
        var kSupports = supportStructure._1;
        var kSupportedBy = supportStructure._2;

        assertEquals(List.of(FALLEN_BRICK_B, FALLEN_BRICK_C), kSupports.get(FALLEN_BRICK_A));
        assertEquals(List.of(FALLEN_BRICK_D, FALLEN_BRICK_E), kSupports.get(FALLEN_BRICK_B));
        assertEquals(List.of(FALLEN_BRICK_D, FALLEN_BRICK_E), kSupports.get(FALLEN_BRICK_C));
        assertEquals(List.of(FALLEN_BRICK_F), kSupports.get(FALLEN_BRICK_D));
        assertEquals(List.of(FALLEN_BRICK_F), kSupports.get(FALLEN_BRICK_E));
        assertEquals(List.of(FALLEN_BRICK_G), kSupports.get(FALLEN_BRICK_F));
        assertNull(kSupports.get(FALLEN_BRICK_G));

        assertNull(kSupportedBy.get(FALLEN_BRICK_A));
        assertEquals(List.of(FALLEN_BRICK_A), kSupportedBy.get(FALLEN_BRICK_B));
        assertEquals(List.of(FALLEN_BRICK_A), kSupportedBy.get(FALLEN_BRICK_C));
        assertEquals(List.of(FALLEN_BRICK_B, FALLEN_BRICK_C), kSupportedBy.get(FALLEN_BRICK_D));
        assertEquals(List.of(FALLEN_BRICK_B, FALLEN_BRICK_C), kSupportedBy.get(FALLEN_BRICK_E));
        assertEquals(List.of(FALLEN_BRICK_D, FALLEN_BRICK_E), kSupportedBy.get(FALLEN_BRICK_F));
        assertEquals(List.of(FALLEN_BRICK_F), kSupportedBy.get(FALLEN_BRICK_G));
    }

    @Test
    void testCanDisintegrateSafely() {
        var bricks = underTest.parseBricks(TEST_DATA);
        var fallen = underTest.fallBricks(bricks);
        var supportStructure = underTest.analyseSupportStructure(fallen);
        var kSupports = supportStructure._1;
        var kSupportedBy = supportStructure._2;

        assertTrue(underTest.canDisintegrateSafely(FALLEN_BRICK_B, kSupports, kSupportedBy));
        assertTrue(underTest.canDisintegrateSafely(FALLEN_BRICK_C, kSupports, kSupportedBy));
        assertTrue(underTest.canDisintegrateSafely(FALLEN_BRICK_D, kSupports, kSupportedBy));
        assertTrue(underTest.canDisintegrateSafely(FALLEN_BRICK_E, kSupports, kSupportedBy));
        assertTrue(underTest.canDisintegrateSafely(FALLEN_BRICK_G, kSupports, kSupportedBy));

        assertFalse(underTest.canDisintegrateSafely(FALLEN_BRICK_A, kSupports, kSupportedBy));
        assertFalse(underTest.canDisintegrateSafely(FALLEN_BRICK_F, kSupports, kSupportedBy));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(5L, underTest.solve(TEST_DATA));
    }

}
