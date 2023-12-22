package org.advent.day22;

import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day22Part1 {

    public Number solve(List<String> lines) {
        var bricks = Function1.of(this::parseBricks)
                .andThen(this::sortBricks)
                .andThen(this::fallBricks)
                .apply(lines);

        var supportStructure = analyseSupportStructure(bricks);

        return bricks.stream()
                .filter(brick -> canDisintegrateSafely(brick, supportStructure._1(), supportStructure._2()))
                .count();
    }

    public List<Brick> parseBricks(List<String> lines) {
        return lines.stream().map(Brick::parse).toList();
    }

    public List<Brick> sortBricks(List<Brick> bricks) {
        return bricks.stream().sorted().toList();
    }

    public List<Brick> fallBricks(List<Brick> bricks) {
        var fallen = new ArrayList<>(bricks);
        for (var i = 0; i < bricks.size(); i++) {
            var brick = bricks.get(i);
            var lowestZ = 1;
            for (var j = 0; j < i; j++) {
                var other = fallen.get(j);
                if (brick.intersects(other)) {
                    lowestZ = Math.max(lowestZ, other.end2().z() + 1);
                }
            }

            fallen.set(i, brick.fallZBy(brick.end1().z() - lowestZ));
        }

        return sortBricks(fallen);
    }

    public Tuple2<Map<Brick, List<Brick>>, Map<Brick, List<Brick>>> analyseSupportStructure(List<Brick> bricks) {
        var kSupports = new HashMap<Brick, List<Brick>>();
        var kSupportedBy = new HashMap<Brick, List<Brick>>();
        for (var i = 0; i < bricks.size(); i++) {
            var brick = bricks.get(i);
            for (var j = 0; j < i; j++) {
                var other = bricks.get(j);
                if (brick.intersects(other) && brick.end1().z() == other.end2().z() + 1) {
                    kSupports.computeIfAbsent(other, k -> new ArrayList<>()).add(brick);
                    kSupportedBy.computeIfAbsent(brick, k -> new ArrayList<>()).add(other);
                }
            }
        }

        return Tuple.of(kSupports, kSupportedBy);
    }

    public boolean canDisintegrateSafely(Brick brick, Map<Brick, List<Brick>> kSupports, Map<Brick, List<Brick>> kSupportedBy) {
        var supports = kSupports.get(brick);
        if (supports == null) {
            return true;
        }

        for (var supportedBrick : supports) {
            var otherSupports = kSupportedBy.get(supportedBrick);
            if (otherSupports == null) {
                return false;
            }

            if (otherSupports.size() == 1) {
                return false;
            }
        }

        return true;
    }

}
