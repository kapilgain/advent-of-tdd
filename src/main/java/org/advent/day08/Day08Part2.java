package org.advent.day08;


import io.vavr.Tuple2;
import org.advent.utils.StringUtils;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.List;
import java.util.Map;

public class Day08Part2 {

    private static final Day08Part1 PART_1 = new Day08Part1();

    public Number solve(List<String> lines) {
        var instructions = StringUtils.splitToCharList(lines.getFirst());
        var networkMap = PART_1.createNetworkMap(lines);

        // All paths need to be cycled until they all reach an end node simultaneously.
        // So the total steps needed is topologically equivalent to the LCM of all individual steps
        var steps = networkMap.keySet()
                .stream()
                .filter(node -> node.endsWith("A"))
                .map(startNode -> countSteps(startNode, instructions, networkMap))
                .toList();

        var lcm = steps.getFirst();
        for (var i = 1; i < steps.size(); i++) {
            lcm = ArithmeticUtils.lcm(lcm, steps.get(i));
        }

        return lcm;
    }

    public long countSteps(String node, List<Character> instructions, Map<String, Tuple2<String, String>> networkMap) {
        var steps = 0;
        while (!node.endsWith("Z")) {
            var step = instructions.get(steps % instructions.size());
            node = networkMap.get(node).apply((l, r) -> step == 'L' ? l : r);
            steps++;
        }

        return steps;
    }

}
