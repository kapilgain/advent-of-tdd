package org.advent.day14;

import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.advent.utils.GridUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.naturalOrder;
import static org.advent.utils.CollectionUtils.partitionSort;
import static org.advent.utils.GridUtils.createGrid;
import static org.advent.utils.GridUtils.transpose;

public class Day14Part2 extends Day14Part1 {

    @Override
    public Number solve(List<String> lines) {
        var grid = createGrid(lines, '#');
        var cache = new LinkedHashMap<String, Tuple2<Long, Number>>();

        long startTime = System.nanoTime();
        for (var cycle = 1L; cycle <= 1_000_000_000L; cycle++) {
            var gridAfterCycle = spin(grid);
            if (!cache.containsKey(GridUtils.asString(gridAfterCycle))) {
                cache.put(GridUtils.asString(gridAfterCycle), Tuple.of(cycle, calculateLoadOnGrid(grid)));
                grid = gridAfterCycle;
                continue;
            }

            // Found a loop
            var loopStart = cache.get(GridUtils.asString(gridAfterCycle))._1;
            var loopLength = cache.pollLastEntry().getValue()._1 - loopStart + 1;
            var mod = (1_000_000_000L - cycle + 1) % loopLength;
            System.out.println("Execution time in milliseconds: " + (System.nanoTime() - startTime) / 1_000_000);
            return cache.values().stream().filter(tuple -> tuple._1 == loopStart + mod).findFirst().orElse(Tuple.of(0L, 0))._2;
        }

        System.out.println("Execution time in milliseconds: " + (System.nanoTime() - startTime) / 1_000_000);
        return cache.pollLastEntry().getValue()._2;
    }

    public char[][] spin(char[][] grid) {
        return Function1.of(this::tiltGridNorth)
                .andThen(this::tiltGridWest)
                .andThen(this::tiltGridSouth)
                .andThen(this::tiltGridEast)
                .apply(grid);
    }

    public char[][] tiltGridWest(char[][] grid) {
        var tiltedRows = new ArrayList<String>(grid.length);
        for (var r = 1; r < grid.length - 1; r++) {
            var row = GridUtils.row(grid, r);
            var tiltedRow = StringUtils.join(tiltRowWest(row), "");
            tiltedRows.add(tiltedRow.substring(1, tiltedRow.length() - 1));
        }

        return createGrid(tiltedRows, '#');
    }

    public char[][] tiltGridSouth(char[][] grid) {
        var tiltedCols = new ArrayList<String>(grid[0].length);
        for (var c = 1; c < grid[0].length - 1; c++) {
            var col = GridUtils.col(grid, c);
            var tiltedCol = StringUtils.join(tiltColSouth(col), "");
            tiltedCols.add(tiltedCol.substring(1, tiltedCol.length() - 1)); // first and last chars are padding
        }

        return transpose(createGrid(tiltedCols, '#'));
    }

    public char[][] tiltGridEast(char[][] grid) {
        var tiltedRows = new ArrayList<String>(grid.length);
        for (var r = 1; r < grid.length - 1; r++) {
            var row = GridUtils.row(grid, r);
            var tiltedRow = StringUtils.join(tiltRowEast(row), "");
            tiltedRows.add(tiltedRow.substring(1, tiltedRow.length() - 1));
        }

        return createGrid(tiltedRows, '#');
    }

    public List<Character> tiltRowWest(List<Character> row) {
        return partitionSort(row, '#', reverseOrder());
    }

    public List<Character> tiltColSouth(List<Character> col) {
        return partitionSort(col, '#', naturalOrder());
    }

    public List<Character> tiltRowEast(List<Character> row) {
        return partitionSort(row, '#', naturalOrder());
    }

}
