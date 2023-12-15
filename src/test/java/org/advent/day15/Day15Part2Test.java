package org.advent.day15;

import io.vavr.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.advent.day15.Day15Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day15Part2Test {

    private Day15Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day15Part2();
    }

    @Test
    void testParseStep() {
        assertEquals(Tuple.of(0, "rn", 1), underTest.parseStep("rn=1"));
        assertEquals(Tuple.of(0, "cm", null), underTest.parseStep("cm-"));
        assertEquals(Tuple.of(1, "qp", 3), underTest.parseStep("qp=3"));
        assertEquals(Tuple.of(0, "cm", 2), underTest.parseStep("cm=2"));
        assertEquals(Tuple.of(1, "qp", null), underTest.parseStep("qp-"));
        assertEquals(Tuple.of(3, "pc", 4), underTest.parseStep("pc=4"));
        assertEquals(Tuple.of(3, "ot", 9), underTest.parseStep("ot=9"));
        assertEquals(Tuple.of(3, "ab", 5), underTest.parseStep("ab=5"));
        assertEquals(Tuple.of(3, "pc", null), underTest.parseStep("pc-"));
        assertEquals(Tuple.of(3, "pc", 6), underTest.parseStep("pc=6"));
        assertEquals(Tuple.of(3, "ot", 7), underTest.parseStep("ot=7"));
    }

    @Test
    void testInitBoxes() {
        var boxes = underTest.initBoxes();
        assertEquals(256, boxes.size());
        for (var i = 0; i < 256; i++) {
            assertEquals(0, boxes.get(i).size());
        }
    }

    @Test
    void boxesHaveCorrectConfigAfterProcessingEachStep() {
        underTest.initBoxes();

        var result = underTest.processStep(underTest.parseStep("rn=1"));
        assertEquals(List.of(Tuple.of(0, "rn", 1)), result.get(0));

        result = underTest.processStep(underTest.parseStep("cm-"));
        assertEquals(List.of(Tuple.of(0, "rn", 1)), result.get(0));

        result = underTest.processStep(underTest.parseStep("qp=3"));
        assertEquals(List.of(Tuple.of(0, "rn", 1)), result.get(0));
        assertEquals(List.of(Tuple.of(1, "qp", 3)), result.get(1));

        result = underTest.processStep(underTest.parseStep("cm=2"));
        assertEquals(List.of(Tuple.of(0, "rn", 1), Tuple.of(0, "cm", 2)), result.get(0));
        assertEquals(List.of(Tuple.of(1, "qp", 3)), result.get(1));

        result = underTest.processStep(underTest.parseStep("qp-"));
        assertEquals(List.of(Tuple.of(0, "rn", 1), Tuple.of(0, "cm", 2)), result.get(0));
        assertTrue(result.get(1).isEmpty());

        result = underTest.processStep(underTest.parseStep("pc=4"));
        assertEquals(List.of(Tuple.of(0, "rn", 1), Tuple.of(0, "cm", 2)), result.get(0));
        assertEquals(List.of(Tuple.of(3, "pc", 4)), result.get(3));

        result = underTest.processStep(underTest.parseStep("ot=9"));
        assertEquals(List.of(Tuple.of(0, "rn", 1), Tuple.of(0, "cm", 2)), result.get(0));
        assertEquals(List.of(Tuple.of(3, "pc", 4), Tuple.of(3, "ot", 9)), result.get(3));

        result = underTest.processStep(underTest.parseStep("ab=5"));
        assertEquals(List.of(Tuple.of(0, "rn", 1), Tuple.of(0, "cm", 2)), result.get(0));
        assertEquals(List.of(Tuple.of(3, "pc", 4), Tuple.of(3, "ot", 9), Tuple.of(3, "ab", 5)), result.get(3));

        result = underTest.processStep(underTest.parseStep("pc-"));
        assertEquals(List.of(Tuple.of(0, "rn", 1), Tuple.of(0, "cm", 2)), result.get(0));
        assertEquals(List.of(Tuple.of(3, "ot", 9), Tuple.of(3, "ab", 5)), result.get(3));

        result = underTest.processStep(underTest.parseStep("pc=6"));
        assertEquals(List.of(Tuple.of(0, "rn", 1), Tuple.of(0, "cm", 2)), result.get(0));
        assertEquals(List.of(Tuple.of(3, "ot", 9), Tuple.of(3, "ab", 5), Tuple.of(3, "pc", 6)), result.get(3));

        result = underTest.processStep(underTest.parseStep("ot=7"));
        assertEquals(List.of(Tuple.of(0, "rn", 1), Tuple.of(0, "cm", 2)), result.get(0));
        assertEquals(List.of(Tuple.of(3, "ot", 7), Tuple.of(3, "ab", 5), Tuple.of(3, "pc", 6)), result.get(3));

        for (var i = 1; i < 256; i++) {
            if (i == 3) {
                continue;
            }

            assertTrue(result.get(i).isEmpty());
        }
    }

    @Test
    void focusingPowerOfDeletedLensIsZero() {
        underTest.initBoxes();
        underTest.processStep(Tuple.of(0, "cm", null));
        assertEquals(0, underTest.focusingPower(Tuple.of(0, "cm", null)));
    }

    @Test
    void testFocusingPower() {
        underTest.initBoxes();
        Arrays.stream(TEST_DATA.split(",")).map(underTest::parseStep).forEach(underTest::processStep);

        assertEquals(1, underTest.focusingPower(Tuple.of(0, "rn", 1)));
        assertEquals(4, underTest.focusingPower(Tuple.of(0, "cm", 2)));
        assertEquals(28, underTest.focusingPower(Tuple.of(3, "ot", 7)));
        assertEquals(40, underTest.focusingPower(Tuple.of(3, "ab", 5)));
        assertEquals(72, underTest.focusingPower(Tuple.of(3, "pc", 6)));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(145, underTest.solve(List.of(TEST_DATA)));
    }

}
