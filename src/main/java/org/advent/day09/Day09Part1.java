package org.advent.day09;


import org.advent.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Day09Part1 {

    public Number solve(List<String> lines) {
        return io.vavr.collection.List.ofAll(
                lines.stream()
                        .map(StringUtils::splitToLongList)
                        .map(this::next)
                        .toList()
        ).sum();
    }

    public Long next(List<Long> sequence) {
        var deltas = deltas(sequence);
        var isDeltaZero = deltas.stream().allMatch(n -> n == 0);
        if (isDeltaZero) {
            return sequence.getFirst();
        }

        return sequence.getLast() + next(deltas);
    }

    public List<Long> deltas(List<Long> sequence) {
        var returnVal = new ArrayList<Long>(sequence.size() - 1);
        for (var i = 0; i < sequence.size() - 1; i++) {
            returnVal.add(sequence.get(i + 1) - sequence.get(i));
        }

        return returnVal;
    }

}
