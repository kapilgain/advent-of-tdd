package org.advent.day04;

import java.util.Arrays;
import java.util.List;

public class Day04Part2 {

    private static final Day04Part1 PART_1 = new Day04Part1();

    public int solve(List<String> lines) {
        var matchesCountList = lines.stream()
                .map(PART_1::countMatches)
                .toList();

        var cardCounts = new int[lines.size()];
        // Original cards
        Arrays.fill(cardCounts, 1);

        for (var i = 0; i < lines.size(); i++) {
            for (var c = 0; c < cardCounts[i]; c++) {
                var match = matchesCountList.get(i);
                for (var m = 1; m <= match; m++) {
                    // Copy cards
                    cardCounts[i + m]++;
                }
            }
        }

        return Arrays.stream(cardCounts).sum();
    }

}
