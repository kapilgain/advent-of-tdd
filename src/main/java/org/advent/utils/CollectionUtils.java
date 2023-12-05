package org.advent.utils;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionUtils<T> {

    public Set<? extends T> intersect(Collection<? extends T> first, Collection<? extends T> second) {
        return first.stream().filter(second::contains).collect(Collectors.toSet());
    }

}
