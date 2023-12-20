package org.advent.day20;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Conjunction implements PulseProcessor {

    private final Map<String, String> memory = new HashMap<>();

    public Conjunction(Module parentModule, Map<String, Module> network) {
        network.values().stream()
                .filter(module -> module.getTargets().contains(parentModule.getName()))
                .forEach(module -> memory.put(module.getName(), "low"));
    }

    @Override
    public String process(String inputPulse, String from) {
        memory.put(from, inputPulse);
        return memory.values().stream()
                .allMatch(pulse -> pulse.equals("high")) ? "low" : "high";
    }

}
