package org.advent.day18;

import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.advent.utils.Direction;
import org.advent.utils.Location;
import org.advent.utils.MathUtils;
import org.advent.utils.SparseGrid;

import java.util.ArrayList;
import java.util.List;

public class Day18Part1 {

    public Number solve(List<String> lines) {
        var boundaryPoints = Function1.of(this::getDigInstructions)
                .andThen(this::dig)
                .andThen(Tuple2::_1)
                .apply(lines);

        var numInternalPoints = MathUtils.countInternalPoints(boundaryPoints);
        return boundaryPoints.size() + numInternalPoints;
    }

    public Tuple2<Character, Long> parseLine(String line) {
        var parts = line.split(" ");
        return Tuple.of(parts[0].charAt(0), Long.parseLong(parts[1]));
    }

    public List<Tuple2<Character, Long>> getDigInstructions(List<String> lines) {
        return lines.stream().map(this::parseLine).toList();
    }

    public Direction parseDirection(Character direction) {
        return switch (direction) {
            case 'R' -> Direction.EAST;
            case 'L' -> Direction.WEST;
            case 'U' -> Direction.NORTH;
            case 'D' -> Direction.SOUTH;
            default -> throw new IllegalStateException("Direction: " + direction);
        };
    }

    public Tuple2<List<Tuple2<? extends Number, ? extends Number>>, SparseGrid<Character>> dig(
            List<Tuple2<Character, Long>> instructions
    ) {
        var boundaryPoints = new ArrayList<Tuple2<? extends Number, ? extends Number>>();
        var grid = new SparseGrid<Character>();
        var shovelLocation = new Location(0, 0);
        for (var instruction : instructions) {
            var direction = parseDirection(instruction._1);
            var distance = instruction._2;
            for (var i = 0; i < distance; i++) {
                shovelLocation = shovelLocation.move(direction);
                boundaryPoints.add(shovelLocation.toRowCol());
                grid.set(shovelLocation.toRowCol(), '#');
            }
        }

        return Tuple.of(boundaryPoints, grid);
    }

}
