package org.advent.utils;

public record Pointer(Location location, Direction direction) {

    public Pointer move() {
        return new Pointer(location().move(direction), direction);
    }

    public Pointer turnLeft() {
        return new Pointer(location(), direction.turnLeft());
    }

    public Pointer turnRight() {
        return new Pointer(location(), direction.turnRight());
    }

}