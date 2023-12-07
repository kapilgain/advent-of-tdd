package org.advent.day07;

import java.util.List;

public class Day07Part2 {

    private static final Day07Part1 PART_1 = new Day07Part1();

    public Number solve(List<String> lines) {
        return PART_1.solve(lines, new WildcardAwareHandComparator());
    }

}
