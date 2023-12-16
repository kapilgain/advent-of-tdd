package org.advent.day16;

import io.vavr.Tuple;
import org.advent.utils.Direction;
import org.advent.utils.GridUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            .|...\\....
            |.-.\\.....
            .....|-...
            ........|.
            ..........
            .........\\
            ..../.\\\\..
            .-.-/..|..
            .|....-|.\\
            ..//.|....
            """
            .trim().split("\n")).toList();
    public static final char[][] TEST_DATA_GRID = GridUtils.createGrid(TEST_DATA);

    public static final String ENERGISED_GRID_FOR_TEST_DATA = """
            ######....
            .#...#....
            .#...#####
            .#...##...
            .#...##...
            .#...##...
            .#..####..
            ########..
            .#######..
            .#...#.#..
            """;

    private Day16Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day16Part1();
    }

    @Test
    void photonDoesNotChangeDirectionOnEmpty() {
        var results = underTest.nextPhotonDirections(new Photon(Tuple.of(1, 1), Direction.EAST), TEST_DATA_GRID);
        assertEquals(1, results.size());
        assertEquals(Set.of(new Photon(Tuple.of(1, 1), Direction.EAST)), results);
    }

    @Test
    void photonDoesNotChangeDirectionWhenGoingEastOrWestOnHorizontalSplit() {
        var results = underTest.nextPhotonDirections(new Photon(Tuple.of(3, 2), Direction.EAST), TEST_DATA_GRID);
        assertEquals(1, results.size());
        assertEquals(Set.of(new Photon(Tuple.of(3, 2), Direction.EAST)), results);

        results = underTest.nextPhotonDirections(new Photon(Tuple.of(3, 2), Direction.WEST), TEST_DATA_GRID);
        assertEquals(1, results.size());
        assertEquals(Set.of(new Photon(Tuple.of(3, 2), Direction.WEST)), results);
    }

    @Test
    void photonDoesNotChangeDirectionWhenGoingNorthOrSouthOnVerticalSplit() {
        var results = underTest.nextPhotonDirections(new Photon(Tuple.of(1, 2), Direction.NORTH), TEST_DATA_GRID);
        assertEquals(1, results.size());
        assertEquals(Set.of(new Photon(Tuple.of(1, 2), Direction.NORTH)), results);

        results = underTest.nextPhotonDirections(new Photon(Tuple.of(1, 2), Direction.SOUTH), TEST_DATA_GRID);
        assertEquals(1, results.size());
        assertEquals(Set.of(new Photon(Tuple.of(1, 2), Direction.SOUTH)), results);
    }

    @Test
    void photonSplitsIntoTwoAndTurnsWhenGoingNorthOnHorizontalSplit() {
        var results = underTest.nextPhotonDirections(new Photon(Tuple.of(3, 2), Direction.NORTH), TEST_DATA_GRID);
        assertEquals(2, results.size());
        assertEquals(Set.of(
                new Photon(Tuple.of(3, 2), Direction.WEST),
                new Photon(Tuple.of(3, 2), Direction.EAST)
        ), results);
    }

    @Test
    void computesEnergisedGrid() {
        var energisedGrid = underTest.computeEnergisedGrid(TEST_DATA_GRID, new Photon(Tuple.of(0, 1), Direction.EAST));
        assertEquals(ENERGISED_GRID_FOR_TEST_DATA.trim(), GridUtils.asString(energisedGrid).trim());
    }

    @Test
    void calculatesEnergy() {
        var energisedGrid = underTest.computeEnergisedGrid(TEST_DATA_GRID, new Photon(Tuple.of(0, 1), Direction.EAST));
        assertEquals(46, underTest.calculateEnergy(energisedGrid));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(46, underTest.solve(TEST_DATA));
    }

}
