package org.advent.day05;

import io.vavr.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day05Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""
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

    private Day05Part1 underTest;

    @BeforeEach
    public void setup() {
        underTest = new Day05Part1();
    }

    @Test
    public void parsesSectionsFromInputData() {
        var sections = underTest.parseSections(TEST_DATA);
        assertEquals(8, sections.length);
        assertTrue(sections[0].startsWith("seeds:"));
        assertTrue(sections[1].startsWith("seed-to-soil map:"));
        assertTrue(sections[2].startsWith("soil-to-fertilizer map:"));
        assertTrue(sections[3].startsWith("fertilizer-to-water map:"));
        assertTrue(sections[4].startsWith("water-to-light map:"));
        assertTrue(sections[5].startsWith("light-to-temperature map:"));
        assertTrue(sections[6].startsWith("temperature-to-humidity map:"));
        assertTrue(sections[7].startsWith("humidity-to-location map:"));
    }

    @Test
    public void parsesSeedsFromSections() {
        var sections = underTest.parseSections(TEST_DATA);
        assertEquals(List.of(79L, 14L, 55L, 13L), underTest.parseSeeds(sections));
    }

    @Test
    public void parsesAllMapsInOrder() {
        var sections = underTest.parseSections(TEST_DATA);
        var maps = underTest.parseMaps(sections);
        assertEquals(
                Set.of(
                        "seed-to-soil map:",
                        "soil-to-fertilizer map:",
                        "fertilizer-to-water map:",
                        "water-to-light map:",
                        "light-to-temperature map:",
                        "temperature-to-humidity map:",
                        "humidity-to-location map:"
                ),
                maps.keySet()
        );
    }

    @Test
    public void parsesSeedSoilMapFromSections() {
        var sections = underTest.parseSections(TEST_DATA);
        var maps = underTest.parseMaps(sections);
        var seedSoilMap = maps.get("seed-to-soil map:");
        assertEquals(
                List.of(Tuple.of(50L, 98L, 2L), Tuple.of(52L, 50L, 48L)),
                seedSoilMap
        );
    }

    @Test
    public void mapsSeedValueToCorrectValuesInOtherDimensions() {
        var sections = underTest.parseSections(TEST_DATA);
        var maps = underTest.parseMaps(sections);
        // Seed 79, soil 81, fertilizer 81, water 81, light 74, temperature 78, humidity 78, location 82.
        assertEquals(
                81L,
                underTest.mapToNextDimension(maps, "seed-to-soil map:", 79L)
        );
        assertEquals(
                81L,
                underTest.mapToNextDimension(maps, "soil-to-fertilizer map:", 81L)
        );
        assertEquals(
                81L,
                underTest.mapToNextDimension(maps, "fertilizer-to-water map:", 81L)
        );
        assertEquals(
                74L,
                underTest.mapToNextDimension(maps, "water-to-light map:", 81L)
        );
        assertEquals(
                78L,
                underTest.mapToNextDimension(maps, "light-to-temperature map:", 74L)
        );
        assertEquals(
                78L,
                underTest.mapToNextDimension(maps, "temperature-to-humidity map:", 78L)
        );
        assertEquals(
                82L,
                underTest.mapToNextDimension(maps, "humidity-to-location map:", 78L)
        );
    }

    @Test
    public void calculatesLowestLocationValueForGivenSeedsAndMaps() {
        var sections = underTest.parseSections(TEST_DATA);
        var seeds = underTest.parseSeeds(sections);
        var maps = underTest.parseMaps(sections);
        assertEquals(35, underTest.lowestLocation(seeds, maps));
    }

    @Test
    public void solvesPartOneSampleInput() {
        assertEquals(35, underTest.solve(TEST_DATA));
    }

}
