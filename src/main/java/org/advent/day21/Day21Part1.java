package org.advent.day21;

import io.vavr.Tuple;
import io.vavr.collection.Queue;
import org.advent.utils.Direction;
import org.advent.utils.Location;

import java.util.HashSet;
import java.util.List;

import static org.advent.utils.GridUtils.*;

public class Day21Part1 {

    public Number solve(List<String> lines) {
        return countFor(64, lines);
    }

    public long countFor(long steps, List<String> lines) {
        var grid = createGrid(lines, '#');
        var indexOfS = indexOf(grid, 'S');
        if (indexOfS == null) {
            throw new RuntimeException("No starting position found");
        }

        var startLocation = Location.from(toXY(indexOfS));
        var queue = Queue.of(Tuple.of(startLocation, 0));
        var visited = new HashSet<Location>();
        var reachedSpots = new HashSet<Location>();

        while (!queue.isEmpty()) {
            var dequeued = queue.dequeue();
            var currentLocation = dequeued._1._1;
            var stepCounter = dequeued._1._2;
            queue = dequeued._2;

            if (visited.contains(currentLocation) || stepCounter > steps) {
                continue;
            }

            visited.add(currentLocation);
            if ((steps - stepCounter) % 2 == 0) {
                reachedSpots.add(currentLocation);
            }

            for (var direction : Direction.values()) {
                var adjacentXY = currentLocation.move(direction);
                var adjacentRowCol = adjacentXY.toRowCol();
                var adjacentPlot = grid[adjacentRowCol._1][adjacentRowCol._2];
                if (adjacentPlot != '#') {
                    queue = queue.enqueue(Tuple.of(adjacentXY, stepCounter + 1));
                }
            }
        }

        return reachedSpots.size();
    }

}
