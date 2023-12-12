package org.advent.day12;

import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.advent.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Day12Part1 {

    public final Function2<Tuple2<String, List<Integer>>, Boolean, Long> memoizedCountSolutions =
            Function2.of(this::countSolutions).memoized();

    public Number solve(List<String> lines) {
        return io.vavr.collection.List.ofAll(
                lines.stream()
                        .map(this::parseLine)
                        .map(line -> memoizedCountSolutions.apply(line, false))
        ).sum();
    }

    public Tuple2<String, List<Integer>> parseLine(String line) {
        var parts = line.split("\\s+");
        return Tuple.of(parts[0], StringUtils.splitToIntList(parts[1], ","));
    }

    public long countSolutions(Tuple2<String, List<Integer>> line, boolean isInBlock) {
        // 'line' is effectively a row in a B/W Nonogram grid with no column constraint
        // '.' is a white cell, '#' is a black cell, and '?' is a cell that is undecided
        var cells = line._1;
        var blocks = line._2;

        // Base case: When all cells have been processed
        if (cells.isEmpty()) {
            // Valid if and only if all blocks have also been processed or only a block of size 0 is left to process
            return blocks.isEmpty() || (blocks.size() == 1 && blocks.getFirst() == 0) ? 1 : 0;
        }

        // We can condense all contiguous dots into a single '.' without any effect on solution since we only need one
        // dot to demarcate the blocks
        cells = cells.replaceAll("\\.+", ".");
        var remainingCells = cells.substring(1);
        switch (cells.charAt(0)) {
            case '.' -> {
                if (isInBlock) {
                    // Invalid since the '.' is prematurely ending the block without fulfilling the current block size
                    if (blocks.getFirst() != 0) {
                        return 0;
                    }

                    return memoizedCountSolutions.apply(
                            Tuple.of(remainingCells, blocks.subList(1, blocks.size())),
                            false
                    );
                }

                return memoizedCountSolutions.apply(Tuple.of(remainingCells, blocks), false);

            }
            case '#' -> {
                // blocks cannot be empty while we are processing a '#'
                if (blocks.isEmpty() || (blocks.size() == 1 && blocks.getFirst() == 0)) {
                    return 0;
                }

                var updatedBlocks = new ArrayList<>(blocks);
                updatedBlocks.set(0, blocks.getFirst() - 1);
                return memoizedCountSolutions.apply(Tuple.of(remainingCells, updatedBlocks), true);

            }
            case '?' -> {
                return memoizedCountSolutions.apply(Tuple.of('.' + remainingCells, blocks), isInBlock) +
                        memoizedCountSolutions.apply(Tuple.of('#' + remainingCells, blocks), isInBlock);
            }
        }

        return 0;
    }

}
