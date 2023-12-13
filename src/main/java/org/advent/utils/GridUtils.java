package org.advent.utils;

import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1-indexed grid
public class GridUtils {

    public static final char EMPTY = '.';

    public static char[][] createGrid(List<String> lines) {
        // Because '.' is usually ignored, we can use it to pad the grid's borders and simplify our adjacency checks
        return createGrid(lines, EMPTY);
    }

    public static char[][] createGrid(List<String> lines, char empty) {
        var rows = lines.size() + 2;
        var cols = lines.getFirst().length() + 2;
        var grid = createEmptyGrid(rows, cols, empty);
        populateGrid(grid, lines);
        return grid;
    }

    public static char[][] createEmptyGrid(int rows, int cols) {
        return createEmptyGrid(rows, cols, EMPTY);
    }

    public static char[][] createEmptyGrid(int rows, int cols, char empty) {
        char[][] grid = new char[rows][cols];
        for (var r = 0; r < rows; r++) {
            Arrays.fill(grid[r], empty);
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

    public static List<Integer> indexOfBlankRows(char[][] grid) {
        return indexOfBlankRows(grid, EMPTY);
    }

    public static List<Integer> indexOfBlankRows(char[][] grid, char empty) {
        var returnVal = new ArrayList<Integer>();
        for (var r = 1; r < grid.length - 1; r++) {
            var isBlank = true;
            for (var c = 1; c < grid[r].length - 1; c++) {
                if (grid[r][c] != empty) {
                    isBlank = false;
                    break;
                }
            }

            if (isBlank) {
                returnVal.add(r);
            }
        }

        return returnVal;
    }

    public static List<Integer> indexOfBlankCols(char[][] grid) {
        return indexOfBlankCols(grid, EMPTY);
    }

    public static List<Integer> indexOfBlankCols(char[][] grid, char empty) {
        var returnVal = new ArrayList<Integer>();
        for (var c = 1; c < grid[0].length - 1; c++) {
            var isBlank = true;
            for (var r = 1; r < grid.length - 1; r++) {
                if (grid[r][c] != empty) {
                    isBlank = false;
                    break;
                }
            }

            if (isBlank) {
                returnVal.add(c);
            }
        }

        return returnVal;
    }

    public static char[][] transpose(char[][] grid) {
        var returnVal = new char[grid[0].length][grid.length];
        for (var r = 0; r < grid.length; r++) {
            for (var c = 0; c < grid[0].length; c++) {
                returnVal[c][r] = grid[r][c];
            }
        }

        return returnVal;
    }

    public static void print(char[][] grid) {
        for (var r = 1; r < grid.length - 1; r++) {
            for (var c = 1; c < grid[0].length - 1; c++) {
                System.out.print(grid[r][c]);
            }

            System.out.println();
        }
    }

}
