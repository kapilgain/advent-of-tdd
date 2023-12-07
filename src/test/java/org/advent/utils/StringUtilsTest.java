package org.advent.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringUtilsTest {

    @Test
    public void splitToIntListThrowsNumberFormatExceptionForInvalidInput() {
        assertThrows(NumberFormatException.class, () -> StringUtils.splitToIntList(""));
        assertThrows(NumberFormatException.class, () -> StringUtils.splitToIntList("hello world"));
    }

    @Test
    public void splitsToIntListForIntegers() {
        assertEquals(List.of(1, 2, 3), StringUtils.splitToIntList("1 2 3"));
    }

    @Test
    public void canSplitToIntListDespitePaddedWhiteSpaces() {
        assertEquals(List.of(1, 2, 3), StringUtils.splitToIntList("  1 2 3"));
        assertEquals(List.of(1, 2, 3), StringUtils.splitToIntList("1 2 3  "));
        assertEquals(List.of(1, 2, 3), StringUtils.splitToIntList("1   2  3"));
    }

    @Test
    public void splitsToLongListForLongs() {
        assertEquals(List.of(1L, 2L, 3866217991L), StringUtils.splitToLongList("1 2 3866217991"));
    }

}
