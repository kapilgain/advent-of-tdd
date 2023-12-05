package org.advent.utils;

import java.util.Collection;

public class MathUtils {

    public int sum(Collection<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

}
