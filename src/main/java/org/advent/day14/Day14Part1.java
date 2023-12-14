package org.advent.day14;

import org.advent.utils.GridUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static io.vavr.collection.List.ofAll;
import static java.util.Collections.reverseOrder;
import static org.advent.utils.CollectionUtils.partitionSort;
import static org.advent.utils.GridUtils.*;

public class Day14Part1 {

    public Number solve(List<String> lines) {
        // We can use '#' as walls
        var grid = createGrid(lines, '#');
        var tiltedGrid = tiltGridNorth(grid);
        // print(tiltedGrid);
        return calculateLoadOnGrid(tiltedGrid);
    }

    public char[][] tiltGridNorth(char[][] grid) {
        var tiltedCols = new ArrayList<String>(grid[0].length);
        for (var c = 1; c < grid[0].length - 1; c++) {
            var col = GridUtils.col(grid, c);
            var tiltedCol = StringUtils.join(tiltColNorth(col), "");
            tiltedCols.add(tiltedCol.substring(1, tiltedCol.length() - 1)); // first and last chars are padding
        }

        return transpose(createGrid(tiltedCols, '#'));
    }

    public List<Character> tiltColNorth(List<Character> col) {
        // Partition at each '#', and then sort the 'O's and '.'s in each partition
        return partitionSort(col, '#', reverseOrder());
    }

    public Number calculateLoadOnCol(List<Character> col) {
        return ofAll(col).reverse().zipWithIndex().filter(zipped -> zipped._1 == 'O').map(zipped -> zipped._2).sum();
    }

    public Number calculateLoadOnGrid(char[][] grid) {
        return ofAll(IntStream.range(1, grid.length - 1).mapToObj(c -> calculateLoadOnCol(col(grid, c)))).sum();
    }

}
