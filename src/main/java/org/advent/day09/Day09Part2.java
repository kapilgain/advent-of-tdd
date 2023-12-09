package org.advent.day09;


import org.advent.utils.StringUtils;

import java.util.List;

public class Day09Part2 {

    private static final Day09Part1 PART_1 = new Day09Part1();

    public Number solve(List<String> lines) {
        return io.vavr.collection.List.ofAll(
                lines.stream()
                        .map(StringUtils::splitToLongList)
                        .map(this::prev)
                        .toList()
        ).sum();
    }

    public Long prev(List<Long> sequence) {
        var deltas = PART_1.deltas(sequence);
        var isDeltaZero = deltas.stream().allMatch(n -> n == 0);
        if (isDeltaZero) {
            return sequence.getFirst();
        }

        return sequence.getFirst() - prev(deltas);
    }

}
