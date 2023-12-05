package org.advent.day03;

import java.util.*;

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

        // Because '.' is ignored, we can use it to pad the grid's borders and simplify our adjacency checks
        var rows = lines.size() + 2;
        var cols = lines.getFirst().length() + 2;
        var grid = createEmptyGrid(rows, cols);
        populateGrid(grid, lines);
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

    public char[][] createEmptyGrid(int rows, int cols) {
        char[][] grid = new char[rows][cols];
        for (var r = 0; r < rows; r++) {
            Arrays.fill(grid[r], '.');
        }

        return grid;
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

    public void populateGrid(char[][] grid, List<String> lines) {
        for (int i = 0, r = 1; i < lines.size(); i++, r++) {
            var row = lines.get(i);
            for (int j = 0, c = 1; j < row.length(); j++, c++) {
                var cell = row.charAt(j);
                grid[r][c] = cell;
            }
        }
    }

}
