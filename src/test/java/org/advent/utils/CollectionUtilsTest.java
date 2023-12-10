package org.advent.utils;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionUtilsTest {

    @Test
    void returnsEmptyForIntersectionBetweenEmpties() {
        assertEquals(Collections.emptySet(), CollectionUtils.intersect(Collections.emptyList(), Collections.emptyList()));
    }

    @Test
    void returnsEmptyForNoMatchingIntersection() {
        assertEquals(Collections.emptySet(), CollectionUtils.intersect(List.of(1, 2, 3), List.of(4, 5)));
    }

    @Test
    void returnsMatchingIntersections() {
        assertEquals(Set.of(2, 3, 5), CollectionUtils.intersect(List.of(1, 2, 3, 4, 5), List.of(5, 2, 3)));
    }

}
