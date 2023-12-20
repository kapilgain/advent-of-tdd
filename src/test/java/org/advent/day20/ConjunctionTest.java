package org.advent.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConjunctionTest {

    @Test
    void initialisesConjunctionMemoryToLowForSourcesElseNull() {
        var network = new Day20Part1().parseModules(Day20Part1Test.TEST_DATA_1);
        var underTest = new Conjunction(Module.parse("&inv -> a"), network);
        assertEquals("low", underTest.getMemory().get("c"));
        assertNull(underTest.getMemory().get("a"));
        assertNull(underTest.getMemory().get("b"));
    }

    @Test
    void returnsLowIfAllInputsAreHigh() {
        var network = new Day20Part1().parseModules(Day20Part1Test.TEST_DATA_1);
        var underTest = new Conjunction(Module.parse("&inv -> a"), network);
        assertEquals("low", underTest.process("high", "c"));
    }

    @Test
    void returnsHighIfAnyInputIsLow() {
        var network = new Day20Part1().parseModules(Day20Part1Test.TEST_DATA_1);
        var underTest = new Conjunction(Module.parse("&inv -> a"), network);
        assertEquals("high", underTest.process("low", "c"));
    }

}
