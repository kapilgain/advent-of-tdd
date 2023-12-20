package org.advent.day20;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.advent.day20.Day20Part1Test.TEST_DATA_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModuleTest {

    @Test
    void parsesModule() {
        var underTest = Module.parse("broadcaster -> a, b, c");
        assertEquals("broadcaster", underTest.getName());
        assertEquals('b', underTest.getType());
        assertEquals(List.of("a", "b", "c"), underTest.getTargets());
    }

    @Test
    void throwsExceptionWhenInitialisingProcessorForInvalidModuleType() {
        var underTest = Module.parse("@invalid -> a");
        assertThrows(IllegalArgumentException.class, () -> underTest.initialiseProcessor(null));
    }

    @Test
    void initialisesBroadcaster() {
        var underTest = Module.parse("broadcaster -> a, b, c").initialiseProcessor(null);
        assertEquals(Broadcaster.class, underTest.getClass());
    }

    @Test
    void initialisesFlipFlop() {
        var underTest = Module.parse("%a -> b").initialiseProcessor(null);
        assertEquals(FlipFlop.class, underTest.getClass());
    }

    @Test
    void initialisesConjunction() {
        var network = new Day20Part1().parseModules(TEST_DATA_1);
        var underTest = Module.parse("&inv -> a").initialiseProcessor(network);
        assertEquals(Conjunction.class, underTest.getClass());
    }

}
