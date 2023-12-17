package org.advent.day05;

import io.vavr.Tuple3;

import java.util.List;
import java.util.Map;

public class Day05Part2 {

    private static final Day05Part1 PART_1 = new Day05Part1();

    public long solve(List<String> lines) {
        var sections = PART_1.parseSections(lines);
        var seeds = PART_1.parseSeeds(sections);
        var maps = PART_1.parseMaps(sections);

        long startTime = System.nanoTime();
        var returnVal = lowestLocation(seeds, maps);
        System.out.println("Execution time in milliseconds: " + (System.nanoTime() - startTime) / 1_000_000);
        return returnVal;
    }

    public long lowestLocation(List<Long> seeds, Map<String, List<Tuple3<Long, Long, Long>>> maps) {
        var lowest = Long.MAX_VALUE;
        for (var s = 0; s < seeds.size(); s += 2) {
            var seedStart = seeds.get(s);
            var seedEnd = seedStart + seeds.get(s + 1);

            for (var t = seedStart; t < seedEnd; t++) {
                var value = t;
                for (var mapperName : maps.keySet()) {
                    value = PART_1.mapToNextDimension(maps, mapperName, value);
                }

                lowest = Math.min(lowest, value);
            }
        }

        return lowest;
    }

}
