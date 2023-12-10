package org.advent.day10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Part2Test {

    private static final List<String> TEST_DATA_1 = Arrays.stream("""
            ...........
            .S-------7.
            .|F-----7|.
            .||.....||.
            .||.....||.
            .|L-7.F-J|.
            .|..|.|..|.
            .L--J.L--J.
            ...........
            """
            .trim().split("\n")).toList();

    private static final List<String> TEST_DATA_2 = Arrays.stream("""
            ..........
            .S------7.
            .|F----7|.
            .||....||.
            .||....||.
            .|L-7F-J|.
            .|..||..|.
            .L--JL--J.
            ..........
             """
            .trim().split("\n")).toList();

    private static final List<String> TEST_DATA_3 = Arrays.stream("""
            .F----7F7F7F7F-7....
            .|F--7||||||||FJ....
            .||.FJ||||||||L7....
            FJL7L7LJLJ||LJ.L-7..
            L--J.L7...LJS7F-7L7.
            ....F-J..F7FJ|L7L7L7
            ....L7.F7||L7|.L7L7|
            .....|FJLJ|FJ|F7|.LJ
            ....FJL-7.||.||||...
            ....L---J.LJ.LJLJ...
                       
             """
            .trim().split("\n")).toList();

    private static final List<String> TEST_DATA_4 = Arrays.stream("""
            FF7FSF7F7F7F7F7F---7
            L|LJ||||||||||||F--J
            FL-7LJLJ||||||LJL-77
            F--JF--7||LJLJ7F7FJ-
            L---JF-JLJ.||-FJLJJ7
            |F|F-JF---7F7-L7L|7|
            |FFJF7L7F-JF7|JL---7
            7-L-JL7||F7|L7F-7F7|
            L.L7LFJ|||||FJL7||LJ
            L7JLJL-JLJLJL--JLJ.L
            """
            .trim().split("\n")).toList();

    private Day10Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day10Part2();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(4, underTest.solve(TEST_DATA_1));
        assertEquals(4, underTest.solve(TEST_DATA_2));
        assertEquals(8, underTest.solve(TEST_DATA_3));
        assertEquals(10, underTest.solve(TEST_DATA_4));
    }

}
