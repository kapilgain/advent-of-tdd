package org.advent.day06;

import java.util.List;

public class Day06Part2 {

    private static final Day06Part1 PART_1 = new Day06Part1();

    public long solve(List<String> lines) {
        var time = Long.parseLong(
                String.join("", lines.getFirst().split(":")[1].split("\\s+"))
        );

        var distance = Long.parseLong(
                String.join("", lines.getLast().split(":")[1].split("\\s+"))
        );

        return PART_1.countSolutions(time, distance);
    }

}
