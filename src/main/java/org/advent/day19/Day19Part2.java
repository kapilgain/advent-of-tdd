package org.advent.day19;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.advent.utils.MathUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.vavr.collection.HashMap.of;
import static org.advent.utils.StringUtils.parseSections;

public class Day19Part2 extends Day19Part1 {

    @Override
    public Number solve(List<String> lines) {
        var sections = parseSections(lines);
        var workflowMap = createWorkflowMap(sections.getFirst());
        return countAcceptedCombinations(
                of(
                        'x', Tuple.of(1, 4000),
                        'm', Tuple.of(1, 4000),
                        'a', Tuple.of(1, 4000),
                        's', Tuple.of(1, 4000)
                ),
                workflowMap,
                "in"
        );
    }

    public long countAcceptedCombinations(
            io.vavr.collection.Map<Character, Tuple2<Integer, Integer>> christmasRanges,
            Map<String, Workflow> workflowMap,
            String startingWorkflowName) {
        if ("R".equals(startingWorkflowName)) {
            return 0L;
        }

        if ("A".equals(startingWorkflowName)) {
            return christmasRanges.values()
                    .map(range -> range._2 - range._1 + 1)
                    .product()
                    .longValue();
        }

        var workflow = workflowMap.get(startingWorkflowName);
        var count = 0L;
        for (var rule : workflow.rules()) {
            var range = christmasRanges.get(rule.conditionLHS()).getOrNull();

            // Last rule, no condition, just go to destination
            if (rule.conditionOperator() == null) {
                count += countAcceptedCombinations(christmasRanges, workflowMap, rule.destination());
                continue;
            }

            char operator = rule.conditionOperator();
            var rhs = Optional.ofNullable(rule.conditionRHS());
            if (operator == '<') {
                var matchedRange = MathUtils.intersectRange(range, Tuple.of(range._1, rhs.orElse(0) - 1));
                var unmatchedRange = Tuple.of(matchedRange._2 + 1, range._2);
                count += countAcceptedCombinations(
                        christmasRanges.put(rule.conditionLHS(), matchedRange),
                        workflowMap,
                        rule.destination());
                christmasRanges = christmasRanges.put(rule.conditionLHS(), unmatchedRange);
                continue;
            }

            // operator == '>'
            var matchedRange = MathUtils.intersectRange(range, Tuple.of(rhs.orElse(0) + 1, range._2));
            var unmatchedRange = Tuple.of(range._1, matchedRange._1 - 1);
            count += countAcceptedCombinations(
                    christmasRanges.put(rule.conditionLHS(), matchedRange),
                    workflowMap,
                    rule.destination());
            christmasRanges = christmasRanges.put(rule.conditionLHS(), unmatchedRange);
        }

        return count;
    }

}
