package org.advent.day02;

import org.advent.utils.ClasspathFileReader;

public class Day02 implements Runnable {

    @Override
    public void run() {
        part1();
        part2();
    }

    public void part1() {
        var lines = new ClasspathFileReader().readAllLines("day02.input");
        var games = lines.stream().map(line -> new GameParser().parse(line)).toList();
        var sum = new GameValidator().sumOfValidGameIds(games, new RGB(12, 13, 14));
        System.out.println(sum);
    }

    public void part2() {
        var lines = new ClasspathFileReader().readAllLines("day02.input");
        var games = lines.stream().map(line -> new GameParser().parse(line)).toList();
        var sum = new GamePowerCalculator().sumPowers(games);
        System.out.println(sum);
    }

}
