package org.advent.day17;

import io.vavr.collection.PriorityQueue;
import org.advent.utils.Crucible;
import org.advent.utils.Direction;
import org.advent.utils.Location;
import org.advent.utils.Pointer;

import java.util.HashSet;
import java.util.List;

import static org.advent.utils.GridUtils.createGrid;
import static org.advent.utils.GridUtils.isOutside;

public class Day17Part1 {

    public Number solve(List<String> lines) {
        var grid = createGrid(lines, '0');
        long startTime = System.nanoTime();
        var returnVal = minimiseHeatLoss(grid);
        System.out.println("Execution time in milliseconds: " + (System.nanoTime() - startTime) / 1_000_000);
        return returnVal;
    }

    public Number minimiseHeatLoss(char[][] grid) {
        var pQ = PriorityQueue.of(
                new Crucible(0, new Pointer(new Location(1, 1), Direction.EAST), 0),
                new Crucible(0, new Pointer(new Location(1, 1), Direction.SOUTH), 0)
        );

        var visited = new HashSet<Crucible>();
        var minimumHeatLoss = Integer.MAX_VALUE;
        while (!pQ.isEmpty()) {
            var dequeued = pQ.dequeue();
            pQ = dequeued._2;
            var crucible = dequeued._1;
            if (visited.contains(crucible)) {
                continue;
            }

            visited.add(crucible);
            // System.out.println(visited);
            if (endCondition(grid, crucible)) {
                minimumHeatLoss = crucible.heatLoss();
                break;
            }

            pQ = enqueueNextCrucibleStates(grid, crucible, pQ);
        }

        return minimumHeatLoss;
    }

    public boolean endCondition(char[][] grid, Crucible crucible) {
        return crucible.pointer().location().equals(new Location(grid[0].length - 2, grid.length - 2));
    }

    public PriorityQueue<Crucible> enqueueNextCrucibleStates(char[][] grid, Crucible crucible, PriorityQueue<Crucible> pQ) {
        // Move straight only if we have not turned in 3 steps
        var stepsSinceLastTurn = crucible.stepsSinceLastTurn();
        var pointer = crucible.pointer();
        if (stepsSinceLastTurn < 3) {
            var moved = pointer.move();
            if (!isOutside(grid, moved.location().toTuple())) {
                var heatLoss = crucible.heatLoss() + getHeatLossAt(grid, moved.location());
                pQ = pQ.enqueue(new Crucible(heatLoss, moved, stepsSinceLastTurn + 1));
            }
        }

        // Turn and move is always allowed
        var turnLeftAndMove = pointer.turnLeft().move();
        if (!isOutside(grid, turnLeftAndMove.location().toTuple())) {
            var heatLoss = crucible.heatLoss() + getHeatLossAt(grid, turnLeftAndMove.location());
            pQ = pQ.enqueue(new Crucible(heatLoss, turnLeftAndMove, 1));
        }

        var turnRightAndMove = pointer.turnRight().move();
        if (!isOutside(grid, turnRightAndMove.location().toTuple())) {
            var heatLoss = crucible.heatLoss() + getHeatLossAt(grid, turnRightAndMove.location());
            pQ = pQ.enqueue(new Crucible(heatLoss, turnRightAndMove, 1));
        }

        return pQ;
    }

    public int getHeatLossAt(char[][] grid, Location location) {
        var rowCol = location.toRowCol();
        return Integer.parseInt("" + grid[rowCol._1][rowCol._2]);
    }

}
