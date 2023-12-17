package org.advent.utils;

import io.vavr.Tuple;
import io.vavr.Tuple2;

public record Location(int x, int y) {

    public static Location from(Tuple2<Integer, Integer> location) {
        return new Location(location._1, location._2);
    }

    public Tuple2<Integer, Integer> toTuple() {
        return Tuple.of(x, y);
    }

    public Tuple2<Integer, Integer> toRowCol() {
        return Tuple.of(y, x);
    }

    public Location move(Direction direction) {
        return from(direction.addTo(toTuple()));
    }

}
