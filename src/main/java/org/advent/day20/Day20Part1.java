package org.advent.day20;

import io.vavr.Tuple;
import io.vavr.collection.Queue;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Day20Part1 {

    public Number solve(List<String> lines) {
        var network = initialiseNetwork(lines);
        var path = pressButton(network, 1_000);
        var lows = StringUtils.countMatches(path, "low");
        var highs = StringUtils.countMatches(path, "high");
        return (long) lows * highs;
    }

    public String pressButton(Map<String, Module> network, long times) {
        var path = new StringBuilder();
        for (var turn = 0; turn < times; turn++) {
            path.append(pressButton(network));
        }
        return path.toString();
    }

    public String pressButton(Map<String, Module> network) {
        var path = new StringBuilder();
        var queue = Queue.of(Tuple.of("broadcaster", "low", "button"));
        while (!queue.isEmpty()) {
            var dequeued = queue.dequeue();
            queue = dequeued._2();
            var item = dequeued._1();

            var currentModuleName = item._1();
            var inputPulse = item._2();
            var fromModuleName = item._3();

            path.append(fromModuleName)
                    .append(" -")
                    .append(inputPulse)
                    .append("-> ")
                    .append(currentModuleName)
                    .append("\n");

            var currentModule = network.get(currentModuleName);
            // This is required for special "sink" modules which do not have any targets
            // eg: "output" module in second test input of part 1, and "rx" module in main input
            if (currentModule == null) {
                continue;
            }

            var outputPulse = currentModule.getProcessor().process(inputPulse, fromModuleName);
            if (outputPulse == null) {
                continue;
            }

            for (var targetModuleName : currentModule.getTargets()) {
                queue = queue.enqueue(Tuple.of(targetModuleName, outputPulse, currentModuleName));
            }
        }

        return path.toString();
    }

    public Map<String, Module> initialiseNetwork(List<String> lines) {
        var network = parseModules(lines);
        network.values().forEach(module -> module.initialiseProcessor(network));
        return network;
    }

    public Map<String, Module> parseModules(List<String> lines) {
        return lines.stream()
                .map(Module::parse)
                .collect(toMap(Module::getName, identity()));
    }

}
