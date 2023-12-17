package org.advent.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<Integer> splitToIntList(String line) {
        return splitToIntList(line, "\\s+");
    }

    public static List<Integer> splitToIntList(String line, String regex) {
        return Arrays.stream(line.trim().split(regex))
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

    public static List<String> parseSections(List<String> lines) {
        var raw = String.join("\n", lines).trim();
        return Arrays.stream(raw.split("\n\n")).toList();
    }

    public static List<String> readLines(String lines) {
        return Arrays.stream(lines.trim().split("\n")).toList();
    }

}
