package org.advent.day21;

import io.vavr.Tuple;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.advent.utils.MathUtils.solveQuadraticCoefficients;
import static org.advent.utils.StringUtils.readLines;

public class Day21Part2 extends Day21Part1 {

    @Override
    public Number solve(List<String> lines) {
        // Couldn't figure out a way to solve this on my own
        // This subreddit post helped me understand the "pattern":
        // https://www.reddit.com/r/adventofcode/comments/18nevo3/comment/keaio7r/
        // This approach is based on some key observations from the input data (main puzzle input, not the test input):
        // 1. The input grid is always a square, so solving for the row or column is the same
        // 2. The input grid size is 131x131
        // 3. S starts in the center of the grid (65, 65)
        // 4. The row and column of S has no obstacles (#), so we can travel to edge of grid (straight) in 65 steps
        // From edge of grid, every multiple of 131 steps in the same direction will land on same edge spot in another grid
        // Coincidentally, 26_501_365L = 65 + 131 * 202_300L :)
        // The idea is to find a quadratic function that can solve for first few iterations of this "pattern"
        // Then we can use this polynomial to calculate the answer for much larger iterations
        // f(x) = ax^2 + bx + c where a, b and c are the unknown coefficients we need to solve for
        // We need 3 data points (x, f(x)) to do this, where f(x) = countFor(s) and s = 65 + x * 131
        // Plugging in x = 0, 1, 2, we get the following data points:
        // eg: (0, countFor(65)), (1, countFor(196)), (2, countFor(327))
        // Then, we can use the polynomial to get our solution: f(202_300L) = countFor(26_501_365L)

        var gridSize = lines.size();
        lines = repeatMapNineTimes(repeatMapNineTimes(lines));

        var steps = 26_501_365L;
        var mod = steps % gridSize;
        var first = countFor(mod, lines);
        var second = countFor(mod + gridSize, lines);
        var third = countFor(mod + gridSize * 2L, lines);
        var coefficients = solveQuadraticCoefficients(
                Tuple.of(0L, first),
                Tuple.of(1L, second),
                Tuple.of(2L, third)
        );

        var a = Math.round(coefficients._1);
        var b = Math.round(coefficients._2);
        var c = Math.round(coefficients._3);
        var quotient = steps / gridSize;
        return a * quotient * quotient + b * quotient + c;
    }

    public List<String> repeatMapNineTimes(List<String> lines) {
        var s = lines.stream()
                .map(line -> line.repeat(3))
                .map(line -> line.replaceFirst("S", "."))
                .map(StringUtils::reverse)
                .map(line -> line.replaceFirst("S", "."))
                .map(StringUtils::reverse)
                .collect(joining("\n"));

        var sb = new StringBuilder();
        for (var i = 0; i < 3; i++) {
            var t = s;
            if (i != 1) {
                t = t.replace("S", ".");
            }
            sb.append(t).append("\n");
        }

        // System.out.println(sb);
        return readLines(sb.toString());
    }

}
