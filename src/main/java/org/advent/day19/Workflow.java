package org.advent.day19;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public record Workflow(String name, List<WorkflowRule> rules) {

    public static Workflow parse(String line) {
        var parts = line.split("\\{");
        var name = parts[0];
        var rules = Arrays.stream(parts[1].substring(0, parts[1].length() - 1).split(","))
                .map(WorkflowRule::parse)
                .collect(toList());
        return new Workflow(name, rules);
    }

}
