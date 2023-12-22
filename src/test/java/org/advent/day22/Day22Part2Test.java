package org.advent.day22;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day22.Day22Part1Test.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Part2Test {

    private Day22Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day22Part2();
    }

    @Test
    void testPullBrick() {
        var bricks = underTest.parseBricks(TEST_DATA);
        var fallenBricks = underTest.fallBricks(bricks);
        var supportStructure = underTest.analyseSupportStructure(fallenBricks);
        var kSupports = supportStructure._1();
        var kSupportedBy = supportStructure._2();

        assertEquals(6L, underTest.pullBrick(FALLEN_BRICK_A, kSupports, kSupportedBy));
        assertEquals(0L, underTest.pullBrick(FALLEN_BRICK_B, kSupports, kSupportedBy));
        assertEquals(0L, underTest.pullBrick(FALLEN_BRICK_C, kSupports, kSupportedBy));
        assertEquals(0L, underTest.pullBrick(FALLEN_BRICK_D, kSupports, kSupportedBy));
        assertEquals(0L, underTest.pullBrick(FALLEN_BRICK_E, kSupports, kSupportedBy));
        assertEquals(1L, underTest.pullBrick(FALLEN_BRICK_F, kSupports, kSupportedBy));
        assertEquals(0L, underTest.pullBrick(FALLEN_BRICK_G, kSupports, kSupportedBy));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(7L, underTest.solve(TEST_DATA));
    }

}
