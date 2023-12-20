package org.advent.day20;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = PRIVATE)
public class Module {

    private final String name;
    private final char type;
    private final List<String> targets;

    @EqualsAndHashCode.Exclude
    private PulseProcessor processor;

    public static Module parse(String line) {
        var parts = line.split(" -> ");
        var name = parts[0].trim();
        char type = name.charAt(0);
        name = switch (type) {
            case '%', '&' -> name.substring(1);
            default -> name;
        };

        var targets = List.of(parts[1].trim().split(", "));
        return new Module(name, type, targets);
    }

    public PulseProcessor initialiseProcessor(Map<String, Module> network) {
        return processor = switch (type) {
            case 'b' -> new Broadcaster();
            case '%' -> new FlipFlop();
            case '&' -> new Conjunction(this, network);
            default -> throw new IllegalArgumentException("Unknown module type: " + type);
        };
    }

}
