package org.advent.day20;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Part1Test {

    public static final List<String> TEST_DATA_1 = Arrays.stream("""
            broadcaster -> a, b, c
            %a -> b
            %b -> c
            %c -> inv
            &inv -> a
            """
            .trim().split("\n")).toList();

    public static final Map<String, Module> TEST_NETWORK_1 = new Day20Part1().initialiseNetwork(TEST_DATA_1);

    public static final String TEST_DATA_2 = """
            broadcaster -> a
            %a -> inv, con
            &inv -> b
            %b -> con
            &con -> output
            """
            .trim();

    public static final Map<String, Module> TEST_NETWORK_2 = new Day20Part1().initialiseNetwork(
            Arrays.stream(TEST_DATA_2.trim().split("\n")).toList()
    );

    private Day20Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day20Part1();
    }

    @Test
    void parsesModules() {
        var modules = underTest.parseModules(TEST_DATA_1);
        assertEquals(5, modules.size());
        assertEquals(Module.parse("broadcaster -> a, b, c"), modules.get("broadcaster"));
        assertEquals(Module.parse("%a -> b"), modules.get("a"));
        assertEquals(Module.parse("%b -> c"), modules.get("b"));
        assertEquals(Module.parse("%c -> inv"), modules.get("c"));
        assertEquals(Module.parse("&inv -> a"), modules.get("inv"));
    }

    @Test
    void initialisesNetwork() {
        var network = underTest.initialiseNetwork(TEST_DATA_1);
        assertEquals(5, network.size());

        var broadcaster = network.get("broadcaster");
        assertEquals(Broadcaster.class, broadcaster.getProcessor().getClass());
        assertEquals(List.of("a", "b", "c"), broadcaster.getTargets());

        var a = network.get("a");
        assertEquals(FlipFlop.class, a.getProcessor().getClass());
        assertEquals(List.of("b"), a.getTargets());

        var b = network.get("b");
        assertEquals(FlipFlop.class, b.getProcessor().getClass());
        assertEquals(List.of("c"), b.getTargets());

        var c = network.get("c");
        assertEquals(FlipFlop.class, c.getProcessor().getClass());
        assertEquals(List.of("inv"), c.getTargets());

        var inverter = network.get("inv");
        assertEquals(Conjunction.class, inverter.getProcessor().getClass());
        assertEquals(List.of("a"), inverter.getTargets());
        ((Conjunction) inverter.getProcessor()).getMemory()
                .forEach((key, value) -> assertEquals("low", value));
    }

    @Test
    void pathIsCorrectAfterButtonPressForNetwork1() {
        assertEquals("""
                        button -low-> broadcaster
                        broadcaster -low-> a
                        broadcaster -low-> b
                        broadcaster -low-> c
                        a -high-> b
                        b -high-> c
                        c -high-> inv
                        inv -low-> a
                        a -low-> b
                        b -low-> c
                        c -low-> inv
                        inv -high-> a
                        """.trim(),
                underTest.pressButton(TEST_NETWORK_1).trim()
        );
    }

    @Test
    void pathIsCorrectAfterButtonPressForNetwork2() {
        assertEquals("""
                        button -low-> broadcaster
                        broadcaster -low-> a
                        a -high-> inv
                        a -high-> con
                        inv -low-> b
                        con -high-> output
                        b -high-> con
                        con -low-> output
                        """.trim(),
                underTest.pressButton(TEST_NETWORK_2, 1).trim()
        );
    }

    @Test
    void solvesForTestInput() {
        assertEquals(32_000_000L, underTest.solve(TEST_DATA_1));
    }

}
