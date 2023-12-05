package org.advent.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilsTest {

    private StringUtils underTest;

    @BeforeEach
    public void setup() {
        underTest = new StringUtils();
    }

    @Test
    public void splitToIntListThrowsNumberFormatExceptionForInvalidInput() {
        assertThrows(NumberFormatException.class, () -> underTest.splitToIntList(""));
        assertThrows(NumberFormatException.class, () -> underTest.splitToIntList("hello world"));
    }

    @Test
    public void splitsToIntListForIntegers() {
        assertEquals(List.of(1, 2, 3), underTest.splitToIntList("1 2 3"));
    }

    @Test
    public void canSplitToIntListDespitePaddedWhiteSpaces() {
        assertEquals(List.of(1, 2, 3), underTest.splitToIntList("  1 2 3"));
        assertEquals(List.of(1, 2, 3), underTest.splitToIntList("1 2 3  "));
        assertEquals(List.of(1, 2, 3), underTest.splitToIntList("1   2  3"));
    }

    @Test
    public void splitsToLongListForLongs() {
        assertEquals(List.of(1L, 2L, 3866217991L), underTest.splitToLongList("1 2 3866217991"));
    }

}
