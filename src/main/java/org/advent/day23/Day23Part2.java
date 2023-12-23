package org.advent.day23;

import io.vavr.Tuple;
import io.vavr.collection.Queue;
import org.advent.utils.Direction;
import org.advent.utils.Location;

import java.util.*;

import static org.advent.utils.GridUtils.*;

public class Day23Part2 extends Day23Part1 {

    @Override
    public Number solve(List<String> lines) {
        var graph = createContractedGraph(lines);
        long startTime = System.nanoTime();
        var returnVal = countLongestPath(
                graph,
                new Location(2, 1), // scenic walk start
                new Location(lines.getFirst().length() - 1, lines.size()), // scenic walk end
                new HashSet<>(),
                0
        );
        System.out.println("Execution time in milliseconds: " + (System.nanoTime() - startTime) / 1_000_000);
        return returnVal;
    }

    public long countLongestPath(
            Map<Location, Map<Location, Integer>> graph,
            Location current,
            Location end,
            Set<Location> visited,
            long pathLength
    ) {
        if (current.equals(end)) {
            return pathLength;
        }

        visited.add(current);
        var maxLength = 0L;
        for (var neighbor : graph.get(current).keySet()) {
            if (visited.contains(neighbor)) {
                continue;
            }

            var result = countLongestPath(
                    graph, neighbor, end, new HashSet<>(visited), pathLength + graph.get(current).get(neighbor)
            );

            maxLength = Math.max(maxLength, result);
        }

        return maxLength;
    }

    public Set<Location> findWaypoints(List<String> lines) {
        var grid = createGrid(lines, '#');
        var wayPoints = new LinkedHashSet<Location>();
        wayPoints.add(new Location(2, 1)); // scenic walk start

        for (var r = 2; r < grid.length - 2; r++) {
            for (var c = 2; c < grid[0].length - 2; c++) {
                var ch = grid[r][c];
                if (ch == '#') {
                    continue;
                }

                var location = new Location(c, r);
                if (countPassableNeighbours(grid, location) > 2) {
                    wayPoints.add(location);
                }
            }
        }

        wayPoints.add(new Location(grid.length - 3, grid[0].length - 2)); // scenic walk end
        return wayPoints;
    }

    public int countPassableNeighbours(char[][] grid, Location location) {
        var count = 0;
        for (var direction : Direction.values()) {
            var rowCol = location.move(direction).toRowCol();
            if (grid[rowCol._1][rowCol._2] != '#') {
                count++;
            }
        }
        return count;
    }

    public Map<Location, Map<Location, Integer>> createContractedGraph(List<String> lines) {
        var grid = createGrid(lines, '#');
        var wayPoints = findWaypoints(lines);
        var graph = new HashMap<Location, Map<Location, Integer>>();
        for (var wayPoint : wayPoints) {
            var queue = Queue.of(Tuple.of(wayPoint, 0));
            var visited = new HashSet<Location>();
            while (!queue.isEmpty()) {
                var dequeued = queue.dequeue();
                var location = dequeued._1._1;
                var steps = dequeued._1._2;
                queue = dequeued._2;

                if (steps > 0 && wayPoints.contains(location) && !location.equals(wayPoint)) {
                    graph.computeIfAbsent(wayPoint, k -> new HashMap<>()).put(location, steps);
                    continue;
                }

                var rowCol = location.toRowCol();
                if (isOutside(grid, rowCol)) {
                    continue;
                }

                if (grid[rowCol._1][rowCol._2] == '#') {
                    continue;
                }

                if (visited.contains(location)) {
                    continue;
                }

                visited.add(location);
                for (var direction : getNextDirectionsAt(grid, location)) {
                    var nextLocation = location.move(direction);
                    queue = queue.enqueue(Tuple.of(nextLocation, steps + 1));
                }
            }
        }

        return graph;
    }

    @Override
    public Direction[] getNextDirectionsAt(char[][] grid, Location location) {
        var rowCol = location.toRowCol();
        return switch (grid[rowCol._1][rowCol._2]) {
            case EMPTY, '^', 'v', '<', '>' -> Direction.values();
            case '#' -> new Direction[]{};
            default -> throw new IllegalStateException("Unexpected value: " + grid[rowCol._1][rowCol._2]);
        };
    }

}
