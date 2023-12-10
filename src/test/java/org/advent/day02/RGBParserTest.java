package org.advent.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RGBParserTest {

    @Test
    void throwsRuntimeExceptionIfRGBDataNotInCorrectFormat() {
        var line = "Hello World";
        var underTest = new RGBParser();
        assertThrows(RuntimeException.class, () -> underTest.parse(line));
        assertThrows(RuntimeException.class, () -> underTest.parse("1 red, 2 green, blue"));
        assertThrows(RuntimeException.class, () -> underTest.parse("1 red, 2 green, 3 blue, 4 yellow"));
        assertThrows(RuntimeException.class, () -> underTest.parse("1 red; 2 green; 3 blue"));
    }

    @Test
    void parsesRedCorrectly() {
        var line = "10 red";
        var parser = new RGBParser();
        var underTest = parser.parse(line);
        assertEquals(new RGB(10, 0, 0), underTest);
    }

    @Test
    void parsesGreenCorrectly() {
        var line = "10 green";
        var parser = new RGBParser();
        var underTest = parser.parse(line);
        assertEquals(new RGB(0, 10, 0), underTest);
    }

    @Test
    void parsesBlueCorrectly() {
        var line = "10 blue";
        var parser = new RGBParser();
        var underTest = parser.parse(line);
        assertEquals(new RGB(0, 0, 10), underTest);
    }

    @Test
    void parsesAllColoursCorrectly() {
        var line = "8 green, 6 blue, 20 red";
        var parser = new RGBParser();
        var underTest = parser.parse(line);
        assertEquals(new RGB(20, 8, 6), underTest);
    }

    @Test
    void parsesWhenOneColourMissing() {
        var line = "3 blue, 4 red";
        var parser = new RGBParser();
        var underTest = parser.parse(line);
        assertEquals(new RGB(4, 0, 3), underTest);
    }

    @Test
    void throwsRuntimeExceptionWhenNoSpaceBetweenColourAndCount() {
        var line = "3blue, 4 red";
        var underTest = new RGBParser();
        assertThrows(RuntimeException.class, () -> underTest.parse(line));
    }

    @Test
    void throwsRuntimeExceptionWhenColourNameIsMissing() {
        var line = "8 green, 6, 20 red";
        var underTest = new RGBParser();
        assertThrows(RuntimeException.class, () -> underTest.parse(line));
    }

    @Test
    void throwsRuntimeExceptionWhenCountIsMissing() {
        var line = "8 green, blue, 20 red";
        var underTest = new RGBParser();
        assertThrows(RuntimeException.class, () -> underTest.parse(line));
    }

    @Test
    void throwsRuntimeExceptionIfColourMisspelled() {
        var line = "8 green, 6 blues, 20 red";
        var underTest = new RGBParser();
        assertThrows(RuntimeException.class, () -> underTest.parse(line));
    }

}
