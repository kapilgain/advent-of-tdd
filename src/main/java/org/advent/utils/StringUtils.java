package org.advent.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public List<Integer> splitToIntList(String line) {
        return Arrays.stream(line.trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    public List<Long> splitToLongList(String line) {
        return Arrays.stream(line.trim().split("\\s+"))
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();
    }

}
