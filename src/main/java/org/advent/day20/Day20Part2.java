package org.advent.day20;

import io.vavr.Tuple;
import io.vavr.collection.Queue;
import org.advent.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Day20Part2 extends Day20Part1 {

    public Number solve(List<String> lines) {
        // There is only one module that has "rx" as a target, and it is a conjunction module
        // This is similiar to the "output" module in the second test input of part 1 which
        // also had only one conjunction module connecting to it (&con -> output)
        var network = initialiseNetwork(lines);
        var sourceToRx = findSourceModuleToRx(network);
        var sourcesToSourceToRx = findSourceModulesTo(sourceToRx.getName(), network);
        var cyclesLengths = countCyclesLengths(network, sourceToRx, sourcesToSourceToRx);
        // System.out.println(cyclesLengths);
        return MathUtils.lcm(new ArrayList<>(cyclesLengths.values()));
    }

    public Module findSourceModuleToRx(Map<String, Module> network) {
        return findSourceModulesTo("rx", network).getFirst();
    }

    public List<Module> findSourceModulesTo(String moduleName, Map<String, Module> network) {
        return network.values().stream()
                .filter(m -> m.getTargets().contains(moduleName))
                .collect(toList());
    }

    public Map<String, Long> countCyclesLengths(Map<String, Module> network, Module sourceToRx, List<Module> sourcesToSourceToRx) {
        var cycleLengths = sourcesToSourceToRx.stream()
                .map(Module::getName)
                .collect(toMap(identity(), k -> 0L));

        for (var turn = 1L; ; turn++) {
            var queue = Queue.of(Tuple.of("broadcaster", "low", "button"));
            while (!queue.isEmpty()) {
                var dequeued = queue.dequeue();
                queue = dequeued._2();
                var item = dequeued._1();

                var currentModuleName = item._1();
                var inputPulse = item._2();
                var fromModuleName = item._3();

                if (sourceToRx.getName().equals(currentModuleName) &&
                        "high".equals(inputPulse) &&
                        cycleLengths.getOrDefault(fromModuleName, 0L) == 0L
                ) {
                    cycleLengths.put(fromModuleName, turn);
                }

                if (cycleLengths.values().stream().allMatch(c -> c != 0L)) {
                    return cycleLengths;
                }

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
        }
    }

}
