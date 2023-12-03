package org.advent.day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day03Part1 {

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
        var cols = lines.get(0).length() + 2;
        var grid = createEmptyGrid(rows, cols);
        populateGrid(grid, lines);
        var partNumbers = findPartNumbers(grid);
        return partNumbers.stream().mapToInt(Integer::intValue).sum();
    }

    public char[][] createEmptyGrid(int rows, int cols) {
        char[][] grid = new char[rows][cols];
        for (var r = 0; r < rows; r++) {
            Arrays.fill(grid[r], '.');
        }

        return grid;
    }

    public List<Integer> findPartNumbers(char[][] grid) {
        var returnVal = new ArrayList<Integer>();
        for (var r = 1; r < grid.length - 1; r++) {
            var row = grid[r];
            for (var c = 1; c < row.length - 1; c++) {
                var cell = row[c];
                if (!Character.isDigit(cell)) {
                    continue;
                }

                var numBuilder = new StringBuilder();
                var isPartNumber = false;
                while (Character.isDigit(cell)) {
                    isPartNumber = isPartNumber || hasAdjacentSymbol(grid, r, c);
                    numBuilder.append(cell);
                    c++;
                    cell = row[c];
                }

                if (isPartNumber) {
                    returnVal.add(Integer.parseInt(numBuilder.toString()));
                }
            }
        }

        return returnVal;
    }

    public boolean hasAdjacentSymbol(char[][] grid, int r, int c) {
        for (var direction : DIRECTION_VECTORS) {
            var x = direction[0];
            var y = direction[1];
            var cell = grid[r + y][c + x];
            if (!Character.isDigit(cell) && cell != '.') {
                return true;
            }
        }

        return false;
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
