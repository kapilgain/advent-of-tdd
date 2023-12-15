package org.advent.day15;

import io.vavr.Tuple;
import io.vavr.Tuple3;

import java.util.*;
import java.util.regex.Pattern;

public class Day15Part2 extends Day15Part1 {

    private static final Pattern STEP_PATTERN = Pattern.compile("^([a-z]+)[=-](\\d)?$");

    private final Map<Integer, List<Tuple3<Integer, String, Integer>>> boxes = new HashMap<>(256);

    public Number solve(List<String> lines) {
        initBoxes();

        Arrays.stream(lines.getFirst().split(","))
                .map(this::parseStep)
                .forEach(this::processStep);

        return boxes.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(this::focusingPower)
                .sum();
    }

    public Map<Integer, List<Tuple3<Integer, String, Integer>>> initBoxes() {
        for (int i = 0; i < 256; i++) {
            boxes.put(i, new ArrayList<>());
        }

        return boxes;
    }

    public Tuple3<Integer, String, Integer> parseStep(String step) {
        var matcher = STEP_PATTERN.matcher(step);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid step: " + step);
        }
        return Tuple.of(
                hashOf(matcher.group(1)),
                matcher.group(1),
                matcher.group(2) == null ? null : Integer.parseInt(matcher.group(2))
        );
    }

    public Map<Integer, List<Tuple3<Integer, String, Integer>>> processStep(Tuple3<Integer, String, Integer> step) {
        var boxNum = step._1;
        var label = step._2;
        var lensNum = step._3;
        var box = boxes.get(boxNum);

        if (lensNum == null) {
            // Delete operation
            box.removeIf(t -> t._2.equals(label));

        } else {
            // Insert operation
            box.stream()
                    .filter(t -> t._2.equals(label))
                    .findFirst()
                    .ifPresentOrElse(
                            t -> box.set(box.indexOf(t), step),
                            () -> box.add(step)
                    );
        }

        return boxes;
    }

    public int focusingPower(Tuple3<Integer, String, Integer> lens) {
        if (lens._3 == null) {
            return 0;
        }

        return (lens._1 + 1) * lens._3 * (boxes.get(lens._1).indexOf(lens) + 1);
    }

}
