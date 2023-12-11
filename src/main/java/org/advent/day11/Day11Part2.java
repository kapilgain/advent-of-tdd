package org.advent.day11;

import org.advent.utils.GridUtils;

import java.util.List;

import static org.advent.utils.GridUtils.createGrid;

public class Day11Part2 {

    private static final Day11Part1 PART_1 = new Day11Part1();

    public Number solve(List<String> lines) {
        return solve(lines, 1_000_000);
    }

    public Number solve(List<String> lines, int expansionFactor) {
        var grid = createGrid(lines);
        var blankRows = GridUtils.indexOfBlankRows(grid);
        var blankCols = GridUtils.indexOfBlankCols(grid);
        var galaxyIndices = PART_1.findAllGalaxies(grid);
        return PART_1.sumGalaxyDistances(galaxyIndices, blankRows, blankCols, expansionFactor);
    }

}
