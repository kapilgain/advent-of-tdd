package org.advent.day16;

import io.vavr.Tuple2;
import org.advent.utils.Direction;

public record Photon(Tuple2<Integer, Integer> location, Direction direction) {

    public Photon move() {
        return new Photon(direction.addTo(location), direction);
    }

    public Photon turnLeft() {
        return new Photon(location, direction.turnLeft());
    }

    public Photon turnRight() {
        return new Photon(location, direction.turnRight());
    }

}