package org.advent.day10;

import io.vavr.Tuple2;
import org.advent.utils.Direction;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.advent.utils.GridUtils.*;

public class Day10Part1 {

    public Number solve(List<String> lines) {
        var grid = createGrid(lines);
        var mainLoop = computeMainLoop(grid);
        return mainLoop.size() / 2;
    }

    public char[][] createGrid(List<String> lines) {
        var rows = lines.size() + 2;
        var cols = lines.getFirst().length() + 2;
        var grid = createEmptyGrid(rows, cols);
        populateGrid(grid, lines);
        return grid;
    }

    public Set<Tuple2<Integer, Integer>> computeMainLoop(char[][] grid) {
        var sIndex = indexOf(grid, 'S');
        assert sIndex != null;
        var sLocation = toXY(sIndex);

        var visitedLocations = new LinkedHashSet<Tuple2<Integer, Integer>>();
        visitedLocations.add(sLocation);

        var location = findLocationOfAnyConnectingPipeTo(sLocation, grid);
        while (location != sLocation) {
            visitedLocations.add(location);
            var index = toIndex(location);
            var pipe = Pipe.fromLabel(grid[index._1][index._2]);
            // System.out.println("Visited " + index + " Pipe: " + pipe);
            assert pipe != null;
            var nextLocation = pipe.connectingLocations(location)
                    .stream()
                    .filter(loc -> !visitedLocations.contains(loc))
                    .findAny();

            location = nextLocation.orElse(sLocation);
        }

        return visitedLocations;
    }

    public Tuple2<Integer, Integer> findLocationOfAnyConnectingPipeTo(Tuple2<Integer, Integer> toXY, char[][] grid) {
        for (var direction : Direction.values()) {
            var adjacentXY = direction.addTo(toXY);
            var index = toIndex(adjacentXY);
            var adjacentPipe = Pipe.fromLabel(grid[index._1][index._2]);
            if (adjacentPipe == null) {
                continue;
            }

            // Check if adjacent pipe can connect to the "xy" location
            if (adjacentPipe.connectingLocations(adjacentXY).contains(toXY)) {
                return adjacentXY;
            }
        }

        return null;
    }

}
