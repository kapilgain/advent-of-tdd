package org.advent.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlipFlopTest {

    @Test
    void throwsExceptionForInvalidInput() {
        var underTest = new FlipFlop();
        assertThrows(IllegalArgumentException.class, () -> underTest.process(null, "broadcaster"));
        assertThrows(IllegalArgumentException.class, () -> underTest.process("invalid", "broadcaster"));
    }

    @Test
    void returnsNullForHighInput() {
        var underTest = new FlipFlop();
        assertNull(underTest.process("high", "broadcaster"));
    }

    @Test
    void returnsHighForLowInputAndOffState() {
        var underTest = new FlipFlop();
        assertFalse(underTest.isOn());
        assertEquals("high", underTest.process("low", "broadcaster"));
    }

    @Test
    void returnsLowForLowInputAndOnState() {
        var underTest = new FlipFlop();
        underTest.setOn(true);
        assertTrue(underTest.isOn());
        assertEquals("low", underTest.process("low", "broadcaster"));
    }

}
