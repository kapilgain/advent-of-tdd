package org.advent.day05;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import org.advent.utils.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day05Part1 {

    public long solve(List<String> lines) {
        var sections = parseSections(lines);
        var seeds = parseSeeds(sections);
        var maps = parseMaps(sections);
        return lowestLocation(seeds, maps);
    }

    public String[] parseSections(List<String> lines) {
        var raw = String.join("\n", lines);
        return raw.split("\n\n");
    }

    public List<Long> parseSeeds(String[] sections) {
        return new StringUtils().splitToLongList(sections[0].split(":")[1]);
    }

    public Map<String, List<Tuple3<Long, Long, Long>>> parseMaps(String[] sections) {
        var returnVal = new LinkedHashMap<String, List<Tuple3<Long, Long, Long>>>();
        for (var i = 1; i < sections.length; i++) {
            var section = sections[i];
            var sectionLines = section.split("\n");
            var mapName = sectionLines[0];
            var mapData = Arrays.stream(sectionLines)
                    .skip(1)
                    .map(line -> new StringUtils().splitToLongList(line))
                    .map(triplet -> Tuple.of(triplet.getFirst(), triplet.get(1), triplet.getLast()))
                    .toList();
            returnVal.put(mapName, mapData);
        }

        return returnVal;
    }

    public Long mapToNextDimension(Map<String, List<Tuple3<Long, Long, Long>>> allMaps, String mapperName, Long valueToMap) {
        var mappings = allMaps.get(mapperName);
        for (var mapping : mappings) {
            if (mapping._2 > valueToMap || mapping._2 + mapping._3 < valueToMap) {
                continue;
            }

            var delta = valueToMap - mapping._2;
            return mapping._1 + delta;
        }

        return valueToMap;
    }

    public long lowestLocation(List<Long> seeds, Map<String, List<Tuple3<Long, Long, Long>>> maps) {
        var lowest = Long.MAX_VALUE;
        for (var seed : seeds) {
            var value = seed;
            for (var mapperName : maps.keySet()) {
                value = mapToNextDimension(maps, mapperName, value);
            }

            lowest = Math.min(lowest, value);
        }

        return lowest;
    }

}
