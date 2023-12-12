package org.advent.day12;

import java.util.Collections;
import java.util.List;

public class Day12Part2 {

    private static final Day12Part1 PART_1 = new Day12Part1();

    public Number solve(List<String> lines) {
        return io.vavr.collection.List.ofAll(
                lines.stream()
                        .map(this::unfold)
                        .map(PART_1::parseLine)
                        .map(line -> PART_1.memoizedCountSolutions.apply(line, false))
        ).sum();
    }

    public String unfold(String line) {
        var parts = line.split("\\s+");
        return String.join("?", Collections.nCopies(5, parts[0])) + " " +
                String.join(",", Collections.nCopies(5, parts[1]));
    }

}
