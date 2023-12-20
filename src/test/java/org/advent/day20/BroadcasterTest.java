package org.advent.day20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BroadcasterTest {

    @Test
    void returnsSamePulse() {
        var underTest = new Broadcaster();
        assertEquals("low", underTest.process("low", "button"));
        assertEquals("high", underTest.process("high", "button"));
    }

}
