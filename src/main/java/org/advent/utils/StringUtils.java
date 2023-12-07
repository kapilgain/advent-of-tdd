package org.advent.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<Integer> splitToIntList(String line) {
        return Arrays.stream(line.trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    public static List<Long> splitToLongList(String line) {
        return Arrays.stream(line.trim().split("\\s+"))
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();
    }

    public static List<Character> splitToCharList(String line) {
        return line.chars()
                .mapToObj(c -> (char) c)
                .toList();
    }

}
