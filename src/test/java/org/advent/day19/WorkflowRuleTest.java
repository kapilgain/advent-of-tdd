package org.advent.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WorkflowRuleTest {

    @Test
    void testInitialisation() {
        var underTest = new WorkflowRule("x>10", "one");
        assertEquals("x>10", underTest.condition());
        assertEquals("one", underTest.destination());
    }

    @Test
    void testParse() {
        assertEquals(new WorkflowRule("x>10", "one"), WorkflowRule.parse("x>10:one"));
        assertEquals(new WorkflowRule(null, "A"), WorkflowRule.parse("A"));
        assertEquals(new WorkflowRule(null, "R"), WorkflowRule.parse("R"));
    }

    @Test
    void testConditionLHS() {
        assertEquals('x', WorkflowRule.parse("x>10:one").conditionLHS());
        assertNull(WorkflowRule.parse("A").conditionLHS());
        assertNull(WorkflowRule.parse("R").conditionLHS());
    }

    @Test
    void testConditionOperator() {
        assertEquals('>', WorkflowRule.parse("x>10:one").conditionOperator());
        assertNull(WorkflowRule.parse("A").conditionOperator());
        assertNull(WorkflowRule.parse("R").conditionOperator());
    }

    @Test
    void testConditionRHS() {
        assertEquals(10, WorkflowRule.parse("x>10:one").conditionRHS());
        assertNull(WorkflowRule.parse("A").conditionRHS());
        assertNull(WorkflowRule.parse("R").conditionRHS());
    }

}
