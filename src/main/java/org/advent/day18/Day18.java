package org.advent.day18;

import org.advent.utils.ClasspathFileReader;

public class Day18 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day18.input");
        System.out.println(new Day18Part1().solve(lines));
        System.out.println(new Day18Part2().solve(lines));
    }

}
