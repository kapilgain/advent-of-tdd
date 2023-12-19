package org.advent.day19;

import org.advent.utils.ClasspathFileReader;

public class Day19 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day19.input");
        System.out.println(new Day19Part1().solve(lines));
        System.out.println(new Day19Part2().solve(lines));
    }

}
