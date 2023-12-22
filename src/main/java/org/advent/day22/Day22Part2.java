package org.advent.day22;

import io.vavr.Function1;
import io.vavr.collection.Queue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class Day22Part2 extends Day22Part1 {

    public Number solve(List<String> lines) {
        var bricks = Function1.of(this::parseBricks)
                .andThen(this::sortBricks)
                .andThen(this::fallBricks)
                .apply(lines);

        var supportStructure = analyseSupportStructure(bricks);
        var kSupports = supportStructure._1();
        var kSupportedBy = supportStructure._2();

        return bricks.stream()
                .mapToLong(brick -> pullBrick(brick, kSupports, kSupportedBy))
                .sum();
    }

    public long pullBrick(Brick brick, Map<Brick, List<Brick>> kSupports, Map<Brick, List<Brick>> kSupportedBy) {
        var pulled = new HashSet<Brick>();
        var queue = Queue.of(brick);
        while (!queue.isEmpty()) {
            var dequeued = queue.dequeue();
            var pulledBrick = dequeued._1();
            queue = dequeued._2();
            pulled.add(pulledBrick);

            var supportedBricks = kSupports.get(pulledBrick);
            if (supportedBricks == null) {
                continue;
            }

            for (var supportedBrick : supportedBricks) {
                var otherSupports = kSupportedBy.get(supportedBrick);
                if (pulled.containsAll(otherSupports)) {
                    queue = queue.append(supportedBrick);
                }
            }
        }

        return pulled.stream().filter(b -> !b.equals(brick)).count();
    }

}
