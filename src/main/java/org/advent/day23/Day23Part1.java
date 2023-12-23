package org.advent.day23;

import io.vavr.Tuple;
import io.vavr.collection.HashSet;
import io.vavr.collection.Queue;
import org.advent.utils.Direction;
import org.advent.utils.Location;
import org.advent.utils.Pointer;

import java.util.List;

import static org.advent.utils.Direction.*;
import static org.advent.utils.GridUtils.*;

public class Day23Part1 {

    public Number solve(List<String> lines) {
        var grid = createGrid(lines, '#');
        var currentWalkVisited = HashSet.<Location>of();
        var queue = Queue.of(Tuple.of(new Pointer(new Location(2, 1), SOUTH), 0L, currentWalkVisited));
        var longest = 0L;
        while (!queue.isEmpty()) {
            var dequeued = queue.dequeue();
            var pointer = dequeued._1._1;
            var steps = dequeued._1._2;
            var visited = dequeued._1._3;
            queue = dequeued._2;

            var rowCol = pointer.location().toRowCol();
            if (isOutside(grid, rowCol)) {
                continue;
            }

            if (grid[rowCol._1][rowCol._2] == '#') {
                continue;
            }

            if (rowCol._1 == grid.length - 2 && grid[rowCol._1][rowCol._2] == EMPTY) {
                longest = Math.max(longest, steps);
                continue;
            }

            if (visited.contains(pointer.location())) {
                continue;
            }

            visited = visited.add(pointer.location());

            pointer = pointer.move();
            var nextDirections = getNextDirectionsAt(grid, pointer.location());
            for (var nextDirection : nextDirections) {
                var nextPointer = new Pointer(pointer.location(), nextDirection);
                queue = queue.enqueue(Tuple.of(nextPointer, steps + 1, visited));
            }
        }

        return longest;
    }

    public Direction[] getNextDirectionsAt(char[][] grid, Location location) {
        var rowCol = location.toRowCol();
        return switch (grid[rowCol._1][rowCol._2]) {
            case EMPTY -> Direction.values();
            case '^' -> new Direction[]{NORTH};
            case 'v' -> new Direction[]{SOUTH};
            case '<' -> new Direction[]{WEST};
            case '>' -> new Direction[]{EAST};
            case '#' -> new Direction[]{};
            default -> throw new IllegalStateException("Unexpected value: " + grid[rowCol._1][rowCol._2]);
        };
    }

}
