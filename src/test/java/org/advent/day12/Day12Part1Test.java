package org.advent.day12;

import io.vavr.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
            """
            .trim().split("\n")).toList();

    private Day12Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day12Part1();
    }

    @Test
    void parsesLineIntoTuple() {
        assertEquals(Tuple.of("???.###", List.of(1, 1, 3)), underTest.parseLine("???.### 1,1,3"));
        assertEquals(Tuple.of(".??..??...?##.", List.of(1, 1, 3)), underTest.parseLine(".??..??...?##. 1,1,3"));
        assertEquals(Tuple.of("?#?#?#?#?#?#?#?", List.of(1, 3, 1, 6)), underTest.parseLine("?#?#?#?#?#?#?#? 1,3,1,6"));
        assertEquals(Tuple.of("????.#...#...", List.of(4, 1, 1)), underTest.parseLine("????.#...#... 4,1,1"));
        assertEquals(Tuple.of("????.######..#####.", List.of(1, 6, 5)), underTest.parseLine("????.######..#####. 1,6,5"));
        assertEquals(Tuple.of("?###????????", List.of(3, 2, 1)), underTest.parseLine("?###???????? 3,2,1"));
    }

    @Test
    void bothNoCellAndNoBlockIsValid() {
        assertEquals(1, underTest.countSolutions(Tuple.of("", Collections.emptyList()), false));
    }

    @Test
    void singleBlackCellWithOneBlockIsValid() {
        assertEquals(1, underTest.countSolutions(Tuple.of("#", Collections.singletonList(1)), false));
    }

    @Test
    void blackCellPresentWithNoBlocksIsInvalid() {
        assertEquals(0, underTest.countSolutions(Tuple.of("#", Collections.emptyList()), false));
    }

    @Test
    void multipleCellsWithDifferentBlockSizes() {
        assertEquals(1, underTest.countSolutions(Tuple.of("###", Collections.singletonList(3)), false));
        assertEquals(0, underTest.countSolutions(Tuple.of("###", Collections.singletonList(2)), false));
    }

    @Test
    void emptyCellsWithDifferentBlocks() {
        assertEquals(1, underTest.countSolutions(Tuple.of("...", Collections.emptyList()), false));
        assertEquals(0, underTest.countSolutions(Tuple.of("...", Collections.singletonList(1)), false));
    }

    @Test
    void testCountForTestInput() {
        assertEquals(1, underTest.countSolutions(underTest.parseLine(TEST_DATA.getFirst()), false));
        assertEquals(10, underTest.countSolutions(underTest.parseLine(TEST_DATA.getLast()), false));
    }

    @Test
    void solvesForTestInput() {
        assertEquals(21L, underTest.solve(TEST_DATA));
    }

}
