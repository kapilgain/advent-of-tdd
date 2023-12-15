package org.advent.day15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Part1Test {

    public static final String TEST_DATA = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";

    private Day15Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day15Part1();
    }

    @Test
    void hashOfHASHis52() {
        assertEquals(52, underTest.hashOf("HASH"));
    }

    @Test
    void sumHashOfTestDataIs1320() {
        assertEquals(1320, underTest.sumHashOf(TEST_DATA));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(1320, underTest.solve(List.of(TEST_DATA)));
    }

}
