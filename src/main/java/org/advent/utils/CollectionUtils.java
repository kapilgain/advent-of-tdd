package org.advent.utils;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static <T> Set<T> intersect(Collection<T> first, Collection<T> second) {
        return first.stream().filter(second::contains).collect(Collectors.toSet());
    }

}
