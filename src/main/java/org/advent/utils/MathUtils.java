package org.advent.utils;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.Collection;
import java.util.List;

public class MathUtils {

    public static long sum(Collection<Long> numbers) {
        return numbers.stream().mapToLong(Long::longValue).sum();
    }

    public static Tuple2<Double, Double> solveQuadratic(long a, long b, long c) {
        var D = Math.sqrt(1.0 * b * b - 4 * a * c);
        var root1 = (-b + D) / (2 * a);
        var root2 = (-b - D) / (2 * a);
        return Tuple.of(Math.min(root1, root2), Math.max(root1, root2));
    }

    public static double polygonArea(List<Tuple2<Integer, Integer>> vertices) {
        var coordinates = vertices.stream()
                .map(v -> v.apply(Coordinate::new))
                .toList()
                .toArray(new Coordinate[0]);

        return new GeometryFactory().createPolygon(coordinates).getArea();
    }

}
