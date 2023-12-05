package org.advent.day05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Part2Test {

    private static final Day05Part1 PART_1 = new Day05Part1();

    private Day05Part2 underTest;

    @BeforeEach
    public void setup() {
        underTest = new Day05Part2();
    }

    @Test
    public void calculatesLowestLocationValueForGivenSeedsAndMaps() {
        var sections = PART_1.parseSections(Day05Part1Test.TEST_DATA);
        var seeds = PART_1.parseSeeds(sections);
        var maps = PART_1.parseMaps(sections);
        assertEquals(46, underTest.lowestLocation(seeds, maps));
    }

    @Test
    public void solvesPartTwoSampleInput() {
        var lines = Arrays.stream("""
                seeds: 79 14 55 13
                                                                
                seed-to-soil map:
                50 98 2
                52 50 48
                                
                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15
                                
                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 7
                57 7 4
                                
                water-to-light map:
                88 18 7
                18 25 70
                                
                light-to-temperature map:
                45 77 23
                81 45 19
                68 64 13
                                
                temperature-to-humidity map:
                0 69 1
                1 0 69
                                
                humidity-to-location map:
                60 56 37
                56 93 4"""
                .trim().split("\n")).toList();

        assertEquals(46, underTest.solve(lines));
    }

}

