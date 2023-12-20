package org.advent.day20;

import org.advent.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.advent.day20.Day20Part1Test.TEST_DATA_2;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Part2Test {

    public static final List<String> TEST_DATA = StringUtils.readLines(
            TEST_DATA_2.replace("output", "rx")
    );

    private Day20Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day20Part2();
    }

    @Test
    void findsSourceToRx() {
        var network = underTest.initialiseNetwork(TEST_DATA);
        var source = underTest.findSourceModuleToRx(network);
        assertEquals("con", source.getName());
        assertEquals('&', source.getType());
    }

    @Test
    void findsSourcesToModule() {
        var network = underTest.initialiseNetwork(TEST_DATA);
        var sources = underTest.findSourceModulesTo("con", network);
        assertEquals(2, sources.size());
        assertEquals("a", sources.getFirst().getName());
        assertEquals("b", sources.getLast().getName());
    }

    @Test
    void countsCyclesLengths() {
        var network = underTest.initialiseNetwork(TEST_DATA);
        var sourceToRx = underTest.findSourceModuleToRx(network);
        var sourcesToSourceToRx = underTest.findSourceModulesTo(sourceToRx.getName(), network);
        var cycleLengths = underTest.countCyclesLengths(network, sourceToRx, sourcesToSourceToRx);
        assertEquals(1L, cycleLengths.get("a"));
        assertEquals(1L, cycleLengths.get("b"));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(1L, underTest.solve(TEST_DATA));
    }

}
