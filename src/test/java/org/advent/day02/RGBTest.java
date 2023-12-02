package org.advent.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RGBTest {

    @Test
    public void allColoursInitiliasedCorrectly() {
        var underTest = new RGB(1, 2, 3);
        assertEquals(1, underTest.red());
        assertEquals(2, underTest.green());
        assertEquals(3, underTest.blue());
    }

}
