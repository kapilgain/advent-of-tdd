package org.advent.day06;

import org.advent.utils.MathUtils;
import org.advent.utils.StringUtils;

import java.util.List;

public class Day06Part1 {

    public long solve(List<String> lines) {
        var times = StringUtils.splitToIntList(lines.getFirst().split(":")[1]);
        var distances = StringUtils.splitToIntList(lines.getLast().split(":")[1]);
        var product = 1;
        for (var i = 0; i < times.size(); i++) {
            product *= countSolutions(times.get(i), distances.get(i));
        }
        return product;
    }

    public int countSolutions(long raceTime, long recordDistance) {
        // If button is pressed for time t, then speed=t
        // Distance travelled (d) = speed * remaining time = t * (T-t)
        // We want to solve for: d > D (record distance)
        // t * (T-t) > D <=> -t^2 + T*t - D > 0
        // This is upside-down parabola, and solutions are all numbers between the roots
        var roots = MathUtils.solveQuadratic(-1, raceTime, -recordDistance);

        // Roots themselves don't count towards the solutions
        // Adding/subtracting delta so that ceil/floor will exclude natural number roots
        int lo = (int) Math.ceil(roots._1 + 1e-9);
        int hi = (int) Math.floor(roots._2 - 1e-9);
        return hi - lo + 1;
    }

}
