package org.advent.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.Comparator.naturalOrder;
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

    @Test
    void partitionSortReturnsEmptyListForEmptyInput() {
        assertEquals(List.of(), CollectionUtils.partitionSort(List.of(), '#', naturalOrder()));
    }

    @Test
    void canSortWithoutPartitionPresent() {
        assertEquals(
                Arrays.asList('a', 'b', 'c'),
                CollectionUtils.partitionSort(Arrays.asList('c', 'a', 'b'), '#', naturalOrder())
        );
    }

    @Test
    void canSortWhenAllItemsArePartitions() {
        assertEquals(
                Arrays.asList('#', '#', '#'),
                CollectionUtils.partitionSort(Arrays.asList('#', '#', '#'), '#', naturalOrder())
        );
    }


    @Test
    void canSortWithOnePartition() {
        assertEquals(
                Arrays.asList('a', 'b', '#', 'c', 'd'),
                CollectionUtils.partitionSort(Arrays.asList('b', 'a', '#', 'd', 'c'), '#', naturalOrder())
        );
    }

    @Test
    void canSortWithMultiplePartitions() {
        assertEquals(
                Arrays.asList('a', 'b', '#', 'c', 'd', '#', 'g', 'h'),
                CollectionUtils.partitionSort(Arrays.asList('b', 'a', '#', 'd', 'c', '#', 'h', 'g'), '#', naturalOrder())
        );
    }

    @Test
    void canSortWithPartitionAtEdges() {
        assertEquals(
                Arrays.asList('#', 'a', 'b', 'c', '#'),
                CollectionUtils.partitionSort(Arrays.asList('#', 'c', 'b', 'a', '#'), '#', naturalOrder())
        );
    }

    @Test
    void canSortWithNoItemsBetweenPartitions() {
        assertEquals(
                Arrays.asList('a', 'b', '#', '#', 'd', 'e'),
                CollectionUtils.partitionSort(Arrays.asList('b', 'a', '#', '#', 'e', 'd'), '#', naturalOrder())
        );
    }

}
