package org.advent.day07;


import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Day07Part1 {

    public Number solve(List<String> lines) {
        return solve(lines, new HandComparator());
    }

    public Number solve(List<String> lines, Comparator<Hand> handComparator) {
        return io.vavr.collection.List.ofAll(
                        lines.stream()
                                .map(Hand::parse)
                                .sorted(handComparator)
                                .toList()
                )
                .zipWithIndex()
                .map(tuple -> tuple.map(Function.identity(), rank -> rank + 1))
                .map(tuple -> tuple.apply((hand, rank) -> hand.bid() * rank))
                .sum();
    }

}
