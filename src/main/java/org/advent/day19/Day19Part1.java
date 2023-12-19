package org.advent.day19;

import io.vavr.Tuple;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.vavr.collection.List.ofAll;
import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.advent.utils.StringUtils.parseSections;
import static org.advent.utils.StringUtils.readLines;

public class Day19Part1 {

    public Number solve(List<String> lines) {
        var sections = parseSections(lines);
        var workflowMap = createWorkflowMap(sections.getFirst());
        var parts = readLines(sections.getLast()).stream()
                .map(Part::parse)
                .toList();

        return ofAll(parts.stream()
                .map(part -> Tuple.of(part, runPartThroughWorkflows(part, workflowMap)))
                .filter(tuple -> tuple._2.contains("A"))
                .map(tuple -> tuple._1.calculateChristmasRating()))
                .sum();
    }

    public Map<String, Workflow> createWorkflowMap(String lines) {
        return readLines(lines).stream()
                .map(Workflow::parse)
                .collect(toMap(Workflow::name, identity()));
    }

    public List<String> runPartThroughWorkflows(Part part, Map<String, Workflow> workflowMap) {
        var workflowName = "in";
        var path = new ArrayList<String>();
        path.add(workflowName);

        while (true) {
            var workflow = requireNonNull(workflowMap.get(workflowName));
            for (var rule : workflow.rules()) {
                if (evaluateCondition(rule.condition(), part)) {
                    if ("A".equals(rule.destination()) || "R".equals(rule.destination())) {
                        path.add(rule.destination());
                        return path;

                    } else {
                        workflowName = rule.destination();
                        path.add(workflowName);
                        break;
                    }
                }
            }
        }
    }

    public boolean evaluateCondition(String condition, Part part) {
        if (StringUtils.isBlank(condition)) {
            return true;
        }

        var isLt = condition.contains("<");
        String[] parts = condition.split(isLt ? "<" : ">");
        String lhs = parts[0];
        int rhs = Integer.parseInt(parts[1]);

        return switch (lhs) {
            case "x" -> isLt ? part.x() < rhs : part.x() > rhs;
            case "m" -> isLt ? part.m() < rhs : part.m() > rhs;
            case "a" -> isLt ? part.a() < rhs : part.a() > rhs;
            case "s" -> isLt ? part.s() < rhs : part.s() > rhs;
            default -> throw new IllegalArgumentException("Invalid condition format");
        };
    }

}