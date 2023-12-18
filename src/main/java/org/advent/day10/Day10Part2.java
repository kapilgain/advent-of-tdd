package org.advent.day10;


import org.advent.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

import static org.advent.utils.GridUtils.createGrid;

public class Day10Part2 {

    private static final Day10Part1 PART_1 = new Day10Part1();

    public Number solve(List<String> lines) {
        var grid = createGrid(lines);
        // print(grid);
        var boundaryPoints = new ArrayList<>(PART_1.computeMainLoop(grid));
        // System.out.println(boundaryPoints);
        return MathUtils.countInternalPoints(boundaryPoints);
    }

    @SuppressWarnings("unused")
    public void print(char[][] grid) {
        for (var row : grid) {
            for (var ch : row) {
                if (ch == 'S') {
                    System.out.print('S');
                    continue;
                }

                var pipe = Pipe.fromLabel(ch);
                if (pipe == null) {
                    System.out.print('.');
                    continue;
                }

                System.out.print(pipe.getDisplay());
            }

            System.out.println();
        }
    }

}
