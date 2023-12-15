package org.advent.day15;

import java.util.Arrays;
import java.util.List;

public class Day15Part1 {

    public Number solve(List<String> lines) {
        return sumHashOf(lines.getFirst());
    }

    public int hashOf(String input) {
        return input.chars().reduce(0, (hash, c) -> (hash + c) * 17 % 256);
    }

    public int sumHashOf(String sequence) {
        return Arrays.stream(sequence.split(",")).mapToInt(this::hashOf).sum();
    }

}
