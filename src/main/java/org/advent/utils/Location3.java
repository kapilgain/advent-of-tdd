package org.advent.utils;

public record Location3(int x, int y, int z) {

    public static Location3 parse(String line) {
        var parts = line.split(",");
        return new Location3(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    public Location toLocation() {
        return new Location(x, y);
    }

}
