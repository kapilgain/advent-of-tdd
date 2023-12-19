package org.advent.day19;

public record WorkflowRule(String condition, String destination) {

    public static WorkflowRule parse(String rule) {
        var ruleParts = rule.split(":");
        return new WorkflowRule(ruleParts.length > 1 ? ruleParts[0] : null, ruleParts[ruleParts.length - 1]);
    }

    public Character conditionLHS() {
        return condition == null ? null : condition.charAt(0);
    }

    public Character conditionOperator() {
        return condition == null ? null : condition.charAt(1);
    }

    public Integer conditionRHS() {
        return condition == null ? null : Integer.parseInt(condition.substring(2));
    }

}