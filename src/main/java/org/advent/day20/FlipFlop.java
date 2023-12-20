package org.advent.day20;

import lombok.Data;

@Data
public class FlipFlop implements PulseProcessor {

    private boolean on = false;

    @Override
    public String process(String pulse, String from) {
        if (pulse == null){
            throw new IllegalArgumentException("Pulse cannot be null");
        }

        return switch (pulse) {
            case "high" -> null;
            case "low" -> {
                on = !on;
                yield on ? "high" : "low";
            }
            default -> throw new IllegalArgumentException("Unknown pulse: " + pulse);
        };
    }

}
