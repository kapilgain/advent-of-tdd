package org.advent.day03;

import java.util.*;

import static org.advent.utils.GridUtils.createGrid;

public class Day03Part2 {

    private static final List<int[]> DIRECTION_VECTORS = List.of(
            new int[]{-1, 0},
            new int[]{-1, -1},
            new int[]{0, -1},
            new int[]{1, -1},
            new int[]{1, 0},
            new int[]{1, 1},
            new int[]{0, 1},
            new int[]{-1, 1}
    );

    public int solve(List<String> lines) {
        if (lines.isEmpty()) {
            return 0;
        }

        var grid = createGrid(lines);
        var neighbourhood = estimateGearNeighbourhoodMap(grid);
        return neighbourhood.values()
                .stream()
                .filter(partNumbers -> partNumbers.size() == 2)
                .map(this::calculateGearRatio)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int calculateGearRatio(List<Integer> partNumbers) {
        return partNumbers.stream().reduce(1, (product, partNumber) -> product * partNumber);
    }

    public Map<String, List<Integer>> estimateGearNeighbourhoodMap(char[][] grid) {
        var returnVal = new HashMap<String, List<Integer>>();
        for (var r = 1; r < grid.length - 1; r++) {
            var row = grid[r];
            for (var c = 1; c < row.length - 1; c++) {
                var cell = row[c];
                if (!Character.isDigit(cell)) {
                    continue;
                }

                var numBuilder = new StringBuilder();
                var adjacentGears = new HashSet<String>();
                while (Character.isDigit(cell)) {
                    adjacentGears.addAll(findAdjacentGears(grid, r, c));
                    numBuilder.append(cell);
                    c++;
                    cell = row[c];
                }

                if (adjacentGears.isEmpty()) {
                    continue;
                }

                for (var gear : adjacentGears) {
                    returnVal.computeIfAbsent(gear, g -> new ArrayList<>())
                            .add(Integer.parseInt(numBuilder.toString()));
                }
            }
        }

        return returnVal;
    }

    public List<String> findAdjacentGears(char[][] grid, int r, int c) {
        var returnVal = new ArrayList<String>();
        for (var direction : DIRECTION_VECTORS) {
            var x = direction[0];
            var y = direction[1];
            var cell = grid[r + y][c + x];
            if (cell == '*') {
                var location = String.format("(%d,%d)", r + y, c + x);
                returnVal.add(location);
            }
        }

        return returnVal;
    }

}
