package org.advent.day13;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.advent.utils.GridUtils;

import java.util.List;

import static io.vavr.collection.List.ofAll;
import static org.advent.utils.StringUtils.parseSections;

public class Day13Part2 {

    private static final Day13Part1 PART_1 = new Day13Part1();

    public Number solve(List<String> lines) {
        return ofAll(parseSections(lines).stream()
                .map(pattern -> pattern.lines().toList())
                .map(patternLines -> GridUtils.createGrid(patternLines, ' '))
                .map(this::fixSmudge)
                .map(tuple -> tuple.apply((h, v) -> h > 0 ? 100 * h : v))
        ).sum();
    }

    public Tuple2<Integer, Integer> fixSmudge(char[][] grid) {
        // GridUtils.print(grid);
        var hOriginal = ofAll(PART_1.findHorizontalReflectionLines(grid)).getOrElse(0);
        var vOriginal = ofAll(PART_1.findVerticalReflectionLines(grid)).getOrElse(0);
        for (var r = 1; r < grid.length - 1; r++) {
            for (var c = 1; c < grid[0].length - 1; c++) {
                grid[r][c] ^= '.' ^ '#';

                var h = ofAll(PART_1.findHorizontalReflectionLines(grid))
                        .find(line -> !hOriginal.equals(line))
                        .getOrElse(0);

                var v = ofAll(PART_1.findVerticalReflectionLines(grid))
                        .find(line -> !vOriginal.equals(line))
                        .getOrElse(0);

                grid[r][c] ^= '.' ^ '#';
                if (h != 0) {
                    return Tuple.of(h, 0);
                }

                if (v != 0) {
                    return Tuple.of(0, v);
                }
            }
        }

        return Tuple.of(0, 0);
    }

}
