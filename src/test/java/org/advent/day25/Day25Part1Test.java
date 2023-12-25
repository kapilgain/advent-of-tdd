package org.advent.day25;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            jqt: rhn xhk nvd
            rsh: frs pzl lsr
            xhk: hfx
            cmg: qnr nvd lhk bvb
            rhn: xhk bvb hfx
            bvb: xhk hfx
            pzl: lsr hfx nvd
            qnr: nvd
            ntq: jqt hfx bvb xhk
            nvd: lhk
            lsr: lhk
            rzs: qnr cmg lsr rsh
            frs: qnr lhk lsr
            """
            .trim().split("\n")).toList();

    private Day25Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day25Part1();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(54, underTest.solve(TEST_DATA));
    }

}
