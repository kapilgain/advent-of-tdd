package org.advent.day22;

import org.advent.utils.Location3;

public record Brick(Location3 end1, Location3 end2) implements Comparable<Brick> {

    public static Brick parse(String line) {
        var parts = line.split("~");
        return new Brick(Location3.parse(parts[0]), Location3.parse(parts[1]));
    }

    public boolean intersects(Brick other) {
        return Math.max(end1.x(), other.end1().x()) <= Math.min(end2.x(), other.end2().x())
                && Math.max(end1.y(), other.end1().y()) <= Math.min(end2.y(), other.end2().y());
    }

    public Brick fallZBy(int distance) {
        return new Brick(
                new Location3(end1.x(), end1.y(), end1.z() - distance),
                new Location3(end2.x(), end2.y(), end2.z() - distance)
        );
    }

    @Override
    public int compareTo(Brick o) {
        return Integer.compare(end1.z(), o.end1.z());
    }

}
