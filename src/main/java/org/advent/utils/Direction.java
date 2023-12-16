package org.advent.utils;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.linear.ArrayRealVector;

@Getter
@RequiredArgsConstructor
public enum Direction {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0);

    private final int x;
    private final int y;

    public static Direction from(int x, int y) {
        for (Direction direction : Direction.values()) {
            if (direction.getX() == x && direction.getY() == y) {
                return direction;
            }
        }

        return null;
    }

    public Tuple2<Integer, Integer> addTo(Tuple2<Integer, Integer> positionVector) {
        var a = new ArrayRealVector(new double[]{x, y});
        var b = new ArrayRealVector(new double[]{positionVector._1, positionVector._2});
        var sum = a.add(b);
        return Tuple.of((int) sum.getEntry(0), (int) sum.getEntry(1));
    }

    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
        };
    }

}
