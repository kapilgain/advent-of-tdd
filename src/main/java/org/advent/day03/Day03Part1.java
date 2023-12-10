package org.advent.day03;

import java.util.ArrayList;
import java.util.List;

import static org.advent.utils.GridUtils.createGrid;

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

        var grid = createGrid(lines);
        var partNumbers = findPartNumbers(grid);
        return partNumbers.stream().mapToInt(Integer::intValue).sum();
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

}
