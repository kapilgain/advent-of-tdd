package org.advent.day16;

import io.vavr.Tuple;

import java.util.ArrayList;
import java.util.List;

import static org.advent.utils.Direction.*;
import static org.advent.utils.GridUtils.createGrid;

public class Day16Part2 extends Day16Part1 {

    @Override
    public Number solve(List<String> lines) {
        var grid = createGrid(lines);
        var energies = new ArrayList<Integer>();

        for (var r = 1; r < grid.length - 1; r++) {
            energies.add(
                    calculateEnergy(computeEnergisedGrid(grid, new Photon(Tuple.of(0, r), EAST)))
            );

            energies.add(
                    calculateEnergy(computeEnergisedGrid(grid, new Photon(Tuple.of(grid.length - 1, r), WEST)))
            );
        }

        for (var c = 1; c < grid[0].length - 1; c++) {
            energies.add(
                    calculateEnergy(computeEnergisedGrid(grid, new Photon(Tuple.of(c, 0), SOUTH)))
            );

            energies.add(
                    calculateEnergy(computeEnergisedGrid(grid, new Photon(Tuple.of(c, grid[0].length - 1), NORTH)))
            );
        }

        return energies.stream().mapToInt(i -> i).max().orElse(0);
    }

}
