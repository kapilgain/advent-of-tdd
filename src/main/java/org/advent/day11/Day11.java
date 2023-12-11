package org.advent.day11;

import org.advent.utils.ClasspathFileReader;

public class Day11 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day11.input");
        System.out.println(new Day11Part1().solve(lines));
        System.out.println(new Day11Part2().solve(lines));
    }

}
