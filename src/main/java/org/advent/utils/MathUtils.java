package org.advent.utils;

import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.Collection;

public class MathUtils {

    public int sum(Collection<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public Tuple2<Double, Double> solveQuadratic(long a, long b, long c) {
        double D = Math.sqrt(1.0 * b * b - 4 * a * c);
        double root1 = (-b + D) / (2 * a);
        double root2 = (-b - D) / (2 * a);
        return Tuple.of(Math.min(root1, root2), Math.max(root1, root2));
    }

}
