package org.advent.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkflowTest {

    @Test
    void testParse() {
        var underTest = Workflow.parse("ex{x>10:one,m<20:two,a>30:R,A}");
        assertEquals("ex", underTest.name());
        assertEquals(4, underTest.rules().size());
        assertEquals(new WorkflowRule("x>10", "one"), underTest.rules().getFirst());
        assertEquals(new WorkflowRule("m<20", "two"), underTest.rules().get(1));
        assertEquals(new WorkflowRule("a>30", "R"), underTest.rules().get(2));
        assertEquals(new WorkflowRule(null, "A"), underTest.rules().getLast());
    }

}
