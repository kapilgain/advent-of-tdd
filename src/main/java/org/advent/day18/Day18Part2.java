package org.advent.day18;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.advent.utils.Direction;

import java.util.List;

public class Day18Part2 extends Day18Part1 {

    @Override
    public Number solve(List<String> lines) {
        long startTime = System.nanoTime();
        var returnVal = super.solve(lines);
        System.out.println("Execution time in milliseconds: " + (System.nanoTime() - startTime) / 1_000_000);
        return returnVal;
    }

    @Override
    public Tuple2<Character, Long> parseLine(String line) {
        var parts = line.split(" ");
        var distance = Long.parseLong(parts[2].substring(2, parts[2].length() - 2), 16);
        var direction = parts[2].charAt(parts[2].length() - 2);
        return Tuple.of(direction, distance);
    }

    @Override
    public Direction parseDirection(Character direction) {
        return switch (direction) {
            case '0' -> Direction.EAST;
            case '1' -> Direction.SOUTH;
            case '2' -> Direction.WEST;
            case '3' -> Direction.NORTH;
            default -> throw new IllegalStateException("Direction: " + direction);
        };
    }

}
