package org.advent.day11;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.advent.utils.GridUtils;
import org.advent.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

import static org.advent.utils.GridUtils.createGrid;

public class Day11Part1 {

    public Number solve(List<String> lines) {
        var grid = createGrid(lines);
        var blankRows = GridUtils.indexOfBlankRows(grid);
        var blankCols = GridUtils.indexOfBlankCols(grid);
        var galaxyIndices = findAllGalaxies(grid);
        return sumGalaxyDistances(galaxyIndices, blankRows, blankCols, 2);
    }

    public long sumGalaxyDistances(
            List<Tuple2<Integer, Integer>> galaxyIndices,
            List<Integer> blankRows,
            List<Integer> blankCols,
            int expansionFactor
    ) {
        var sum = 0L;

        for (var i = 0; i < galaxyIndices.size() - 1; i++) {
            for (var j = i + 1; j < galaxyIndices.size(); j++) {
                var first = galaxyIndices.get(i);
                var second = galaxyIndices.get(j);
                var distance = MathUtils.manhattanDistance(first, second);

                distance += (expansionFactor - 1) * blankRows.stream()
                        .filter(r -> r > Math.min(first._1, second._1))
                        .filter(r -> r < Math.max(first._1, second._1))
                        .count();

                distance += (expansionFactor - 1) * blankCols.stream()
                        .filter(r -> r > Math.min(first._2, second._2))
                        .filter(r -> r < Math.max(first._2, second._2))
                        .count();

                sum += distance;
            }
        }

        return sum;
    }

    public List<Tuple2<Integer, Integer>> findAllGalaxies(char[][] grid) {
        var galaxyIndices = new ArrayList<Tuple2<Integer, Integer>>();
        for (var r = 1; r < grid.length - 1; r++) {
            for (var c = 1; c < grid[r].length - 1; c++) {
                if (grid[r][c] == '#') {
                    galaxyIndices.add(Tuple.of(r, c));
                }
            }
        }

        return galaxyIndices;
    }

}
