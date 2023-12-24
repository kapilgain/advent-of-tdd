package org.advent.day24;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple6;
import org.advent.utils.MathUtils;
import org.advent.utils.StringUtils;

import java.util.List;

import static org.advent.utils.MathUtils.intersectLines;

public class Day24Part1 {

    public Number solve(List<String> lines) {
        return countIntersectionPoints(lines, Tuple.of(200000000000000L, 400000000000000L));
    }

    public List<Tuple6<Long, Long, Long, Long, Long, Long>> parseLines(List<String> lines) {
        return lines.stream()
                .map(line -> line.replace(",", " ").replace("@ ", " "))
                .map(StringUtils::splitToLongList)
                .map(l -> Tuple.of(l.getFirst(), l.get(1), l.get(2), l.get(3), l.get(4), l.getLast()))
                .toList();
    }

    public List<Tuple2<Double, Double>> processLines(List<Tuple6<Long, Long, Long, Long, Long, Long>> hailLines) {
        return hailLines.stream().map(hailLine -> MathUtils.solveLinearCoefficients(
                        Tuple.of(hailLine._1, hailLine._2),
                        Tuple.of(hailLine._1 + hailLine._4, hailLine._2 + hailLine._5)
                ))
                .toList();
    }

    public long countIntersectionPoints(List<String> lines, Tuple2<Long, Long> testArea) {
        var hailLines = parseLines(lines);
        var processedLines = processLines(hailLines);
        var count = 0;
        for (var i = 0; i < processedLines.size(); i++) {
            for (var j = 0; j < i; j++) {
                var first = processedLines.get(i);
                var second = processedLines.get(j);
                // convert y = ax + b to ax - y = -b
                var intersectionPoint = findIntersectionPoint(first, second);
                if (intersectionPoint == null) {
                    continue;
                }

                // Outside test area

                if (intersectionPoint._1 < testArea._1 || intersectionPoint._1 > testArea._2 ||
                        intersectionPoint._2 < testArea._1 || intersectionPoint._2 > testArea._2) {
                    continue;
                }

                // Check if intersection happens in positive time for both lines
                // x = x1 + vx1 * t <=> t = (x - x1) / vx1 > 0
                double x = intersectionPoint._1;
                var x1 = hailLines.get(i)._1;
                var vx1 = hailLines.get(i)._4;
                var x2 = hailLines.get(j)._1;
                var vx2 = hailLines.get(j)._4;
                if (calculateTime(x1, x, vx1) > 0 && calculateTime(x2, x, vx2) > 0) {
                    // System.out.println(i + " " + j + " " + hailLines.get(i) + " " + hailLines.get(j) + " " + intersectionPoint);
                    count++;
                }
            }
        }

        return count;
    }

    public Tuple2<Double, Double> findIntersectionPoint(Tuple2<Double, Double> first, Tuple2<Double, Double> second) {
        return intersectLines(
                first.apply((a, b) -> Tuple.of(a, -1.0, -b)),
                second.apply((a, b) -> Tuple.of(a, -1.0, -b))
        );
    }

    public double calculateTime(double firstPoint, double secondPoint, long velocity) {
        return (secondPoint - firstPoint) / velocity;
    }

}
