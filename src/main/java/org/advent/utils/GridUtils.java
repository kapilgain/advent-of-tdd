package org.advent.utils;

import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.Arrays;
import java.util.List;

// 1-indexed grid
public class GridUtils {

    public static char[][] createGrid(List<String> lines) {
        // Because '.' is ignored, we can use it to pad the grid's borders and simplify our adjacency checks
        var rows = lines.size() + 2;
        var cols = lines.getFirst().length() + 2;
        var grid = createEmptyGrid(rows, cols);
        populateGrid(grid, lines);
        return grid;
    }

    public static char[][] createEmptyGrid(int rows, int cols) {
        char[][] grid = new char[rows][cols];
        for (var r = 0; r < rows; r++) {
            Arrays.fill(grid[r], '.');
        }

        return grid;
    }

    public static void populateGrid(char[][] grid, List<String> lines) {
        for (int i = 0, r = 1; i < lines.size(); i++, r++) {
            var row = lines.get(i);
            for (int j = 0, c = 1; j < row.length(); j++, c++) {
                var cell = row.charAt(j);
                grid[r][c] = cell;
            }
        }
    }

    public static Tuple2<Integer, Integer> indexOf(char[][] grid, char ch) {
        for (var r = 0; r < grid.length; r++) {
            for (var c = 0; c < grid[r].length; c++) {
                if (ch == grid[r][c]) {
                    return Tuple.of(r, c);
                }
            }
        }

        return null;
    }

    public static Tuple2<Integer, Integer> toXY(Tuple2<Integer, Integer> index) {
        return index.map((row, col) -> Tuple.of(col, row));
    }

    public static Tuple2<Integer, Integer> toIndex(Tuple2<Integer, Integer> xy) {
        return xy.map((x, y) -> Tuple.of(y, x));
    }

}
