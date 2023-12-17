package org.advent.day16;

import io.vavr.Tuple;
import io.vavr.collection.Array;
import io.vavr.collection.Queue;
import io.vavr.collection.Stream;
import org.advent.utils.Direction;
import org.advent.utils.GridUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.advent.utils.GridUtils.createEmptyGrid;
import static org.advent.utils.GridUtils.createGrid;

public class Day16Part1 {

    public Number solve(List<String> lines) {
        var grid = createGrid(lines);
        var energised = computeEnergisedGrid(grid, new Photon(Tuple.of(0, 1), Direction.EAST));
        return calculateEnergy(energised);
    }

    public int calculateEnergy(char[][] energised) {
        return Array.of(energised)
                .flatMap(Stream::ofAll)
                .count(c -> c == '#');
    }

    public char[][] computeEnergisedGrid(char[][] grid, Photon entryPoint) {
        var energised = createEmptyGrid(grid.length, grid[0].length);
        var photons = Queue.of(entryPoint);
        var visited = new HashSet<Photon>();

        while (!photons.isEmpty()) {
            var dequeued = photons.dequeue();
            photons = dequeued._2;

            var photon = dequeued._1.move();
            photons = photons.enqueueAll(
                    nextPhotonDirections(photon, grid).stream()
                            .filter(p -> !visited.contains(p) && !GridUtils.isOutside(grid, p.location()))
                            .peek(p -> {
                                visited.add(p);
                                energised[p.location()._2][p.location()._1] = '#';
                            })
                            .toList()
            );
        }

        return energised;
    }


    public Set<Photon> nextPhotonDirections(Photon photon, char[][] grid) {
        var photonLocation = photon.location();
        var photonDirection = photon.direction();
        var gridObj = grid[photonLocation._2][photonLocation._1];

        // No change
        if (gridObj == '.' ||
                (gridObj == '-' && photonDirection == Direction.WEST) ||
                (gridObj == '-' && photonDirection == Direction.EAST) ||
                (gridObj == '|' && photonDirection == Direction.NORTH) ||
                (gridObj == '|' && photonDirection == Direction.SOUTH)) {
            return Set.of(photon);
        }

        // Reflect
        if (gridObj == '/') {
            return switch (photonDirection) {
                case NORTH, SOUTH -> Set.of(photon.turnRight());
                case EAST, WEST -> Set.of(photon.turnLeft());
            };
        }

        if (gridObj == '\\') {
            return switch (photonDirection) {
                case NORTH, SOUTH -> Set.of(photon.turnLeft());
                case EAST, WEST -> Set.of(photon.turnRight());
            };
        }

        // Split
        if (gridObj == '-' && photonDirection == Direction.NORTH ||
                gridObj == '-' && photonDirection == Direction.SOUTH ||
                gridObj == '|' && photonDirection == Direction.WEST ||
                gridObj == '|' && photonDirection == Direction.EAST) {
            return Set.of(photon.turnLeft(), photon.turnRight());
        }

        return Set.of();
    }

}
