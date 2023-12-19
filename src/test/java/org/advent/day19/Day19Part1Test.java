package org.advent.day19;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day19Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            px{a<2006:qkq,m>2090:A,rfg}
            pv{a>1716:R,A}
            lnx{m>1548:A,A}
            rfg{s<537:gd,x>2440:R,A}
            qs{s>3448:A,lnx}
            qkq{x<1416:A,crn}
            crn{x>2662:A,R}
            in{s<1351:px,qqz}
            qqz{s>2770:qs,m<1801:hdj,R}
            gd{a>3333:R,R}
            hdj{m>838:A,pv}

            {x=787,m=2655,a=1222,s=2876}
            {x=1679,m=44,a=2067,s=496}
            {x=2036,m=264,a=79,s=2244}
            {x=2461,m=1339,a=466,s=291}
            {x=2127,m=1623,a=2188,s=1013}
            """
            .trim().split("\n")).toList();

    public static final String TEST_WORKFLOW_DATA = """
            px{a<2006:qkq,m>2090:A,rfg}
            pv{a>1716:R,A}
            lnx{m>1548:A,A}
            rfg{s<537:gd,x>2440:R,A}
            qs{s>3448:A,lnx}
            qkq{x<1416:A,crn}
            crn{x>2662:A,R}
            in{s<1351:px,qqz}
            qqz{s>2770:qs,m<1801:hdj,R}
            gd{a>3333:R,R}
            hdj{m>838:A,pv}
            """
            .trim();

    private Day19Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day19Part1();
    }

    @Test
    void throwsExceptionWhenEvaluatingInvalidCondition() {
        assertThrows(
                Exception.class,
                () -> underTest.evaluateCondition("hello", new Part(0, 0, 0, 0))
        );

        assertThrows(
                Exception.class,
                () -> underTest.evaluateCondition("q>10", new Part(0, 0, 0, 0))
        );
    }

    @Test
    void blankConditionEvaluatesToTrue() {
        assertTrue(underTest.evaluateCondition(null, new Part(11, 0, 0, 0)));
    }

    @Test
    void testEvaluateCondition() {
        assertTrue(underTest.evaluateCondition("x>10", new Part(11, 0, 0, 0)));
        assertFalse(underTest.evaluateCondition("x>10", new Part(10, 0, 0, 0)));
        assertFalse(underTest.evaluateCondition("x>10", new Part(9, 0, 0, 0)));
        assertTrue(underTest.evaluateCondition("x<10", new Part(9, 0, 0, 0)));

        assertTrue(underTest.evaluateCondition("m>10", new Part(0, 11, 0, 0)));
        assertFalse(underTest.evaluateCondition("m>10", new Part(0, 10, 0, 0)));
        assertFalse(underTest.evaluateCondition("m>10", new Part(0, 9, 0, 0)));
        assertFalse(underTest.evaluateCondition("m<9", new Part(0, 9, 0, 0)));

        assertTrue(underTest.evaluateCondition("a>10", new Part(0, 0, 11, 0)));
        assertFalse(underTest.evaluateCondition("a>10", new Part(0, 0, 10, 0)));
        assertFalse(underTest.evaluateCondition("a>10", new Part(0, 0, 9, 0)));
        assertTrue(underTest.evaluateCondition("a<10", new Part(0, 0, 9, 0)));

        assertTrue(underTest.evaluateCondition("s>10", new Part(0, 0, 0, 11)));
        assertFalse(underTest.evaluateCondition("s>10", new Part(0, 0, 0, 10)));
        assertFalse(underTest.evaluateCondition("s>10", new Part(0, 0, 0, 9)));
        assertTrue(underTest.evaluateCondition("s<10", new Part(0, 0, 0, 9)));
    }

    @Test
    void testRunPartThroughWorkflowsForSmallerInput() {
        var workflowMap = underTest.createWorkflowMap("in{A}");
        assertEquals(
                List.of("in", "A"),
                underTest.runPartThroughWorkflows(Part.parse("{x=787,m=2655,a=1222,s=2876}"), workflowMap)
        );

        workflowMap = underTest.createWorkflowMap("in{R}");
        assertEquals(
                List.of("in", "R"),
                underTest.runPartThroughWorkflows(Part.parse("{x=787,m=2655,a=1222,s=2876}"), workflowMap)
        );
    }

    @Test
    void testRunPartThroughWorkflows() {
        var workflowMap = underTest.createWorkflowMap(TEST_WORKFLOW_DATA);
        assertEquals(
                Arrays.asList("in", "qqz", "qs", "lnx", "A"),
                underTest.runPartThroughWorkflows(Part.parse("{x=787,m=2655,a=1222,s=2876}"), workflowMap)
        );

        assertEquals(
                Arrays.asList("in", "px", "rfg", "gd", "R"),
                underTest.runPartThroughWorkflows(Part.parse("{x=1679,m=44,a=2067,s=496}"), workflowMap)
        );

        assertEquals(
                Arrays.asList("in", "qqz", "hdj", "pv", "A"),
                underTest.runPartThroughWorkflows(Part.parse("{x=2036,m=264,a=79,s=2244}"), workflowMap)
        );

        assertEquals(
                Arrays.asList("in", "px", "qkq", "crn", "R"),
                underTest.runPartThroughWorkflows(Part.parse("{x=2461,m=1339,a=466,s=291}"), workflowMap)
        );

        assertEquals(
                Arrays.asList("in", "px", "rfg", "A"),
                underTest.runPartThroughWorkflows(Part.parse("{x=2127,m=1623,a=2188,s=1013}"), workflowMap)
        );
    }

    @Test
    void solvesForTestInput() {
        assertEquals(19_114L, underTest.solve(TEST_DATA));
    }

}
