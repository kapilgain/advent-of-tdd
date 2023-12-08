package org.advent.day08;


import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.advent.utils.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class Day08Part1 {

    private static final Pattern NETWORK_NODE_PATTERN = Pattern.compile("(\\w+) = \\((\\w+), (\\w+)\\)");

    public Number solve(List<String> lines) {
        var instructions = StringUtils.splitToCharList(lines.getFirst());
        var networkMap = createNetworkMap(lines);

        var node = "AAA";
        var steps = 0;
        while (!"ZZZ".equals(node)) {
            var step = instructions.get(steps % instructions.size());
            node = networkMap.get(node).apply((l, r) -> step == 'L' ? l : r);
            steps++;
        }

        return steps;
    }

    public Tuple3<String, String, String> parseNetworkNode(String line) {
        var matches = NETWORK_NODE_PATTERN.matcher(line);
        if (matches.find()) {
            return Tuple.of(matches.group(1), matches.group(2), matches.group(3));
        }

        return null;
    }

    public Map<String, Tuple2<String, String>> createNetworkMap(List<String> lines) {
        return lines.stream()
                .skip(2)
                .map(this::parseNetworkNode)
                .collect(toMap(
                        tuple -> tuple._1,
                        tuple -> tuple.apply((n, l, r) -> Tuple.of(l, r))
                ));
    }

}
