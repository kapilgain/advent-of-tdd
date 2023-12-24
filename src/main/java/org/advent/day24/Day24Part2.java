package org.advent.day24;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.Tuple6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.advent.utils.MathUtils.findFactors;

public class Day24Part2 extends Day24Part1 {

    public Number solve(List<String> lines) {
        return findPossibleRockStartingCoordinates(lines).stream()
                .findFirst()
                .map(t -> (long) (t._1 + t._2 + t._3))
                .orElseThrow();
    }

    public Set<Tuple3<Double, Double, Double>> findPossibleRockStartingCoordinates(List<String> lines) {
        var returnVal = new HashSet<Tuple3<Double, Double, Double>>();

        // At any time "t", the position is given by (for one spatial dimension):
        // x = x1 + vx1 * t <=> t = (x - x1) / vx1
        // For collision, we need to find time "t" such that:
        // x1 + vx1 * t = x + vx * t <=> t = (x - x1) / (vx1 - vx)
        // On inspecting the input data, we can see multiple hails have the same velocity in a given dimension
        // So for the rock to collide with such hails in integer time t, (x - x1) must be divisible by (vx1 - vx)
        // We extract all factors and brute-force through each feasible velocity to find the rock's starting position
        var hailLines = parseLines(lines);
        var feasibleVelocitiesX = calculateFeasibleVelocities(hailLines, 0);
        var feasibleVelocitiesY = calculateFeasibleVelocities(hailLines, 1);
        var feasibleVelocitiesZ = calculateFeasibleVelocities(hailLines, 2);

        for (var vx : feasibleVelocitiesX) {
            for (var vy : feasibleVelocitiesY) {
                for (var vz : feasibleVelocitiesZ) {
                    // We subtract rock's velocity from hail so that we are looking at things from rock's frame of reference
                    // This simplifies, because in this frame, all hails must pass through rock's position in order to collide
                    var first = hailLines.getFirst();
                    first = first.apply((x, y, z, vx1, vy1, vz1) -> Tuple.of(x, y, z, vx1 - vx, vy1 - vy, vz1 - vz));

                    var second = hailLines.get(1);
                    second = second.apply((x, y, z, vx1, vy1, vz1) -> Tuple.of(x, y, z, vx1 - vx, vy1 - vy, vz1 - vz));

                    var processed = processLines(List.of(first, second));
                    var intersectionPoint = findIntersectionPoint(processed.get(0), processed.get(1));
                    if (intersectionPoint != null) {
                        var timeFirst = calculateTime(first._1(), intersectionPoint._1, first._4());
                        var timeSecond = calculateTime(second._1(), intersectionPoint._1, second._4());
                        // Check if timeFirst and timeSecond are natural numbers
                        if (timeFirst > 0 && timeSecond > 0 &&
                                timeFirst == Math.floor(timeFirst) && timeSecond == Math.floor(timeSecond)) {
                            // Verify for z
                            if (first._3 + first._6 * timeFirst == second._3 + second._6 * timeSecond) {
                                returnVal.add(Tuple.of(intersectionPoint._1, intersectionPoint._2, first._3 + first._6 * timeFirst));
                            }
                        }
                    }
                }
            }
        }

        return returnVal;
    }


    public Set<Long> calculateFeasibleVelocities(List<Tuple6<Long, Long, Long, Long, Long, Long>> hailstones, int axis) {
        var intersection = new TreeSet<Long>();

        for (var i = 0; i < hailstones.size(); i++) {
            for (var j = i + 1; j < hailstones.size(); j++) {
                var h1 = hailstones.get(i);
                var h2 = hailstones.get(j);

                var p1 = axis == 0 ? h1._1() : axis == 1 ? h1._2() : h1._3();
                var p2 = axis == 0 ? h2._1() : axis == 1 ? h2._2() : h2._3();
                var v1 = axis == 0 ? h1._4() : axis == 1 ? h1._5() : h1._6();
                var v2 = axis == 0 ? h2._4() : axis == 1 ? h2._5() : h2._6();

                if (v1.equals(v2)) {
                    var distanceDiff = Math.abs(p1 - p2);
                    var factors = findFactors(distanceDiff);
                    var feasibleVelocities = new HashSet<Long>();

                    // factor = |feasible_v - v1| <=> feasible_v = v1 + factor or v1 - factor
                    for (var factor : factors) {
                        feasibleVelocities.add(v1 + factor);
                        feasibleVelocities.add(v1 - factor);
                    }

                    feasibleVelocities.remove(0L); // Exclude zero velocity, since rock cannot be stationary
                    if (intersection.isEmpty()) {
                        intersection.addAll(feasibleVelocities);
                    } else {
                        intersection.retainAll(feasibleVelocities);
                    }
                }
            }
        }

        return intersection;
    }

}
