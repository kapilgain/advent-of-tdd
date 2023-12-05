package org.advent.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    private MathUtils underTest;

    @BeforeEach
    public void setup() {
        underTest = new MathUtils();
    }

    @Test
    public void sumsCollectionOfIntegers() {
        assertEquals(10, underTest.sum(List.of(1, 2, 3, 4)));
    }

}
