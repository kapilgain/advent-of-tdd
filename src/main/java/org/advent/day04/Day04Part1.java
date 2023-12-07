package org.advent.day04;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import org.advent.utils.CollectionUtils;
import org.advent.utils.MathUtils;
import org.advent.utils.StringUtils;

import java.util.List;

public class Day04Part1 {

    public long solve(List<String> lines) {
        return MathUtils.sum(
                lines.stream()
                        .map(this::countMatches)
                        .map(this::toPoints)
                        .map(point -> (long) point)
                        .toList()
        );
    }

    public Tuple3<Integer, List<Integer>, List<Integer>> parseLine(String line) {
        return Tuple.of(parseCardId(line), parseWinners(line), parseScratched(line));
    }

    public int parseCardId(String line) {
        return Integer.parseInt(line.split(":")[0].split("\\s+")[1]);
    }

    public List<Integer> parseWinners(String line) {
        return StringUtils.splitToIntList(line.split(":")[1].split("\\|")[0]);
    }

    public List<Integer> parseScratched(String line) {
        return StringUtils.splitToIntList(line.split(":")[1].split("\\|")[1]);
    }

    public int countMatches(String line) {
        return parseLine(line)
                .apply((cardId, winners, scratched) -> CollectionUtils.intersect(winners, scratched))
                .size();

    }

    public int toPoints(int numMatches) {
        if (numMatches < 1) {
            return 0;
        }

        return (int) Math.pow(2, numMatches - 1);
    }

}
