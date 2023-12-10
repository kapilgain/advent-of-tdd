package org.advent.day10;

import io.vavr.Tuple2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.advent.utils.Direction;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.advent.utils.Direction.*;

@Getter
@RequiredArgsConstructor
public enum Pipe {

    PIPE_NS('|', Set.of(NORTH, SOUTH), '│'),
    PIPE_EW('-', Set.of(EAST, WEST), '─'),
    PIPE_NE('L', Set.of(NORTH, EAST), '└'),
    PIPE_NW('J', Set.of(NORTH, WEST), '┘'),
    PIPE_SW('7', Set.of(SOUTH, WEST), '┐'),
    PIPE_SE('F', Set.of(SOUTH, EAST), '┌');


    private final char label;
    private final Set<Direction> connections;
    private final char display;

    public static Pipe fromLabel(char label) {
        for (Pipe pipe : Pipe.values()) {
            if (pipe.label == label) {
                return pipe;
            }
        }

        return null;
    }

    public Set<Tuple2<Integer, Integer>> connectingLocations(Tuple2<Integer, Integer> pipePositionVector) {
        return connections.stream()
                .map(direction -> direction.addTo(pipePositionVector))
                .collect(toSet());
    }

}
