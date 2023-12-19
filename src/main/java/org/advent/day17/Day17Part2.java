package org.advent.day17;

import io.vavr.collection.PriorityQueue;

import java.util.List;

import static org.advent.utils.GridUtils.isOutside;

public class Day17Part2 extends Day17Part1 {

    public Number solve(List<String> lines) {
        return super.solve(lines);
    }

    @Override
    public boolean endCondition(char[][] grid, Crucible crucible) {
        return super.endCondition(grid, crucible) && crucible.stepsSinceLastTurn() >= 4;
    }

    @Override
    public PriorityQueue<Crucible> enqueueNextCrucibleStates(char[][] grid, Crucible crucible, PriorityQueue<Crucible> pQ) {
        // Move straight only if we have not turned in 10 steps
        var stepsSinceLastTurn = crucible.stepsSinceLastTurn();
        var pointer = crucible.pointer();
        if (stepsSinceLastTurn < 10) {
            var moved = pointer.move();
            if (!isOutside(grid, moved.location().toTuple())) {
                var heatLoss = crucible.heatLoss() + getHeatLossAt(grid, moved.location());
                pQ = pQ.enqueue(new Crucible(heatLoss, moved, stepsSinceLastTurn + 1));
            }
        }

        // Turn and move is allowed only after moving straight for 4 steps
        if (stepsSinceLastTurn >= 4) {
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
        }

        return pQ;
    }

}
