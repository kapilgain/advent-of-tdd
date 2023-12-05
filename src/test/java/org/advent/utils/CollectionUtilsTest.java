package org.advent.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionUtilsTest {

    private CollectionUtils<Integer> underTest;

    @BeforeEach
    public void setup() {
        underTest = new CollectionUtils<>();
    }

    @Test
    public void returnsEmptyForIntersectionBetweenEmpties() {
        assertEquals(Collections.emptySet(), underTest.intersect(Collections.emptyList(), Collections.emptyList()));
    }

    @Test
    public void returnsEmptyForNoMatchingIntersection() {
        assertEquals(Collections.emptySet(), underTest.intersect(List.of(1, 2, 3), List.of(4, 5)));
    }

    @Test
    public void returnsMatchingIntersections() {
        assertEquals(Set.of(2, 3, 5), underTest.intersect(List.of(1, 2, 3, 4, 5), List.of(5, 2, 3)));
    }

}
