package org.advent.day13;

import io.vavr.Tuple;
import org.advent.utils.GridUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.vavr.collection.List.ofAll;
import static org.advent.utils.GridUtils.transpose;
import static org.advent.utils.StringUtils.parseSections;

public class Day13Part1 {

    public Number solve(List<String> lines) {
        return ofAll(parseSections(lines).stream()
                .map(pattern -> pattern.lines().toList())
                .map(patternLines -> GridUtils.createGrid(patternLines, ' '))
                .map(grid -> Tuple.of(grid, findHorizontalReflectionLines(grid), findVerticalReflectionLines(grid)))
                .map(tuple -> tuple.apply((grid, h, v) -> !h.isEmpty() ? 100 * h.getFirst() : v.getFirst()))
        ).sum();
    }

    public List<Integer> findHorizontalReflectionLines(char[][] grid) {
        var returnVal = new ArrayList<Integer>();
        for (var top = 1; top < grid.length - 1; top++) {
            // At the point of reflection, current and next will match
            if (!Arrays.equals(grid[top], grid[top + 1])) {
                continue;
            }

            var reflectionLine = top;
            // From here, we middle-out the comparison towards the edges
            for (int up = top - 1, down = top + 2; up > 0 && down < grid.length - 1; up--, down++) {
                if (!Arrays.equals(grid[up], grid[down])) {
                    reflectionLine = 0;
                    break;
                }
            }

            if (reflectionLine > 0) {
                returnVal.add(reflectionLine);
            }
        }

        return returnVal;
    }

    public List<Integer> findVerticalReflectionLines(char[][] grid) {
        return findHorizontalReflectionLines(transpose(grid));
    }

}
