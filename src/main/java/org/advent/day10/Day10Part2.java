package org.advent.day10;


import org.advent.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

import static org.advent.utils.GridUtils.createGrid;

public class Day10Part2 {

    private static final Day10Part1 PART_1 = new Day10Part1();

    public Number solve(List<String> lines) {
        var grid = createGrid(lines);
        print(grid);

        // Using Pick's formula, we can get number of enclosed integer nodes = A - (b/2) + 1
        // where A is the polygon area, and b is the number of integer boundary points
        var boundaryPoints = new ArrayList<>(PART_1.computeMainLoop(grid));

        // Add the start location again at the end, to close the polygon geometry
        boundaryPoints.add(boundaryPoints.getFirst());
        // System.out.println(boundaryPoints);

        var area = MathUtils.polygonArea(new ArrayList<>(boundaryPoints));
        return (int) area - boundaryPoints.size() / 2 + 1;
    }

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
