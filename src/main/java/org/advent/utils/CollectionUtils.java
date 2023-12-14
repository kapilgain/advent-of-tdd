package org.advent.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.advent.utils.StringUtils.splitToCharList;

public class CollectionUtils {

    public static <T> Set<T> intersect(Collection<T> first, Collection<T> second) {
        return first.stream().filter(second::contains).collect(Collectors.toSet());
    }

    public static List<Character> partitionSort(List<Character> list, char partitionChar, Comparator<Character> comparator) {
        // Partition
        var partitions = StringUtils.join(list, "").split("" + partitionChar, -1);

        // Sort
        List<List<Character>> sortedPartitions = Arrays.stream(partitions)
                .map(partition -> partition.chars()
                        .mapToObj(rock -> (char) rock)
                        .sorted(comparator)
                        .collect(toList()))
                .toList();

        // Join partitions back
        var rv = sortedPartitions.stream()
                .map(partition -> StringUtils.join(partition, ""))
                .collect(joining("#"));

        return splitToCharList(rv);
    }
}
