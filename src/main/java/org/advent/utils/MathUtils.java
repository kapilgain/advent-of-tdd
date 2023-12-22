package org.advent.utils;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.ArrayList;
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

    public static Tuple3<Double, Double, Double> solveQuadraticCoefficients(
            Tuple2<Long, Long> first, Tuple2<Long, Long> second, Tuple2<Long, Long> third
    ) {
        var x1 = first._1;
        var x2 = second._1;
        var x3 = third._1;
        var fx1 = first._2;
        var fx2 = second._2;
        var fx3 = third._2;

        double[][] coefficients = {
                {x1 * x1, x1, 1},
                {x2 * x2, x2, 1},
                {x3 * x3, x3, 1}
        };
        double[] constants = {fx1, fx2, fx3};

        var matrix = new Array2DRowRealMatrix(coefficients);
        var solver = new LUDecomposition(matrix).getSolver();
        var solution = solver.solve(new Array2DRowRealMatrix(constants).getColumnVector(0));

        var a = solution.getEntry(0);
        var b = solution.getEntry(1);
        var c = solution.getEntry(2);
        return Tuple.of(a, b, c);
    }

    public static double polygonArea(List<Tuple2<? extends Number, ? extends Number>> vertices) {
        // Add the start location again at the end, to close the polygon geometry, if it is not already
        if (!vertices.getFirst().equals(vertices.getLast())) {
            vertices = new ArrayList<>(vertices);
            vertices.add(vertices.getFirst());
        }

        var coordinates = vertices.stream()
                .map(v -> v.apply((x, y) -> new Coordinate(x.doubleValue(), y.doubleValue())))
                .toList()
                .toArray(new Coordinate[0]);

        return new GeometryFactory().createPolygon(coordinates).getArea();
    }

    public static long countInternalPoints(List<Tuple2<? extends Number, ? extends Number>> vertices) {
        // Using Pick's formula, we can get number of enclosed integer nodes = A - (b/2) + 1
        // where A is the polygon area, and b is the number of integer boundary points
        return (long) polygonArea(vertices) - vertices.size() / 2 + 1;
    }

    public static long manhattanDistance(Tuple2<Integer, Integer> first, Tuple2<Integer, Integer> second) {
        return Math.abs(second._1 - first._1) + Math.abs(second._2 - first._2);
    }

    public static Tuple2<Integer, Integer> intersectRange(
            Tuple2<Integer, Integer> firstRange, Tuple2<Integer, Integer> secondRange
    ) {
        var returnVal = Tuple.of(Math.max(firstRange._1, secondRange._1), Math.min(firstRange._2, secondRange._2));
        if (returnVal._1 > returnVal._2) {
            return Tuple.of(0, 0);
        }

        return returnVal;
    }

    public static Long lcm(List<Long> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Cannot calculate LCM of empty list");
        }

        var lcm = numbers.getFirst();
        for (var i = 1; i < numbers.size(); i++) {
            lcm = ArithmeticUtils.lcm(lcm, numbers.get(i));
        }
        return lcm;
    }

}
