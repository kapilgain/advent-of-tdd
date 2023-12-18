package org.advent.utils;

import io.vavr.Tuple;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GridUtilsTest {

    private static final List<String> TEST_DATA_1 = Arrays.asList(
            "467",
            "*..",
            "35."
    );

    private static final List<String> TEST_DATA_2 = Arrays.stream("""
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
            """
            .trim().split("\n")).toList();

    @Test
    void testCreateEmptyGrid() {
        var underTest = GridUtils.createEmptyGrid(5, 5);
        assertEquals(5, underTest.length);
        assertEquals(5, underTest[0].length);
        assertEquals('.', underTest[2][2]);
    }

    @Test
    void testPopulateGrid() {
        var underTest = GridUtils.createEmptyGrid(5, 5);
        GridUtils.populateGrid(underTest, TEST_DATA_1);
        assertEquals('4', underTest[1][1]);
        assertEquals('*', underTest[2][1]);
        assertEquals('3', underTest[3][1]);
    }

    @Test
    void testCreateGrid() {
        var underTest = GridUtils.createGrid(TEST_DATA_1);
        assertEquals('4', underTest[1][1]);
        assertEquals('*', underTest[2][1]);
        assertEquals('3', underTest[3][1]);
    }

    @Test
    void testIndexOf() {
        var underTest = GridUtils.createGrid(TEST_DATA_1);
        assertEquals(Tuple.of(1, 3), GridUtils.indexOf(underTest, '7'));
        assertEquals(Tuple.of(2, 1), GridUtils.indexOf(underTest, '*'));
        assertEquals(Tuple.of(3, 2), GridUtils.indexOf(underTest, '5'));
        assertNull(GridUtils.indexOf(underTest, '$'));
    }

    @Test
    void covertsRowColIndexToXY() {
        assertEquals(Tuple.of(1, 3), GridUtils.toXY(Tuple.of(3, 1)));
    }

    @Test
    void covertsXYtoRowColIndex() {
        assertEquals(Tuple.of(3, 1), GridUtils.toXY(Tuple.of(1, 3)));
    }

    @Test
    void testIndexOfBlankRows() {
        var underTest = GridUtils.indexOfBlankRows(GridUtils.createGrid(TEST_DATA_2));
        assertEquals(2, underTest.size());
    }

    @Test
    void testIndexOfBlankCols() {
        var underTest = GridUtils.indexOfBlankCols(GridUtils.createGrid(TEST_DATA_2));
        assertEquals(3, underTest.size());
    }

    @Test
    void testTransposeForSquareGrid() {
        var input = new char[][]{
                "123".toCharArray(),
                "456".toCharArray(),
                "789".toCharArray(),
        };

        var expected = new char[][]{
                "147".toCharArray(),
                "258".toCharArray(),
                "369".toCharArray(),
        };

        assertTrue(Arrays.deepEquals(expected, GridUtils.transpose(input)));
    }

    @Test
    void testTransposeForRectangleGrid() {
        var input = new char[][]{
                "123".toCharArray(),
                "456".toCharArray(),
        };

        var expected = new char[][]{
                "14".toCharArray(),
                "25".toCharArray(),
                "36".toCharArray(),
        };

        assertTrue(Arrays.deepEquals(expected, GridUtils.transpose(input)));
    }

    @Test
    void testCol() {
        assertEquals(
                List.of('#', '4', '*', '3', '#'),
                GridUtils.col(GridUtils.createGrid(TEST_DATA_1, '#'), 1)
        );
    }

    @Test
    void testRow() {
        assertEquals(
                List.of('#', '4', '6', '7', '#'),
                GridUtils.row(GridUtils.createGrid(TEST_DATA_1, '#'), 1)
        );
    }

    @Test
    void testAsStringRepresentationOfGrid() {
        assertEquals("""
                        467
                        *..
                        35.
                        """.trim(),
                GridUtils.asString(GridUtils.createGrid(TEST_DATA_1))
        );
    }

    @Test
    void testGridEdges() {
        var grid = GridUtils.createGrid(TEST_DATA_1);
        assertTrue(GridUtils.isOutside(grid, Tuple.of(0, 0)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(0, 1)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(0, 2)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(0, 3)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(0, 4)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(1, 0)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(2, 0)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(3, 0)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(4, 0)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(4, 1)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(4, 2)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(4, 3)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(4, 4)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(1, 4)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(2, 4)));
        assertTrue(GridUtils.isOutside(grid, Tuple.of(3, 4)));

        assertFalse(GridUtils.isOutside(grid, Tuple.of(1, 1)));
    }

}
