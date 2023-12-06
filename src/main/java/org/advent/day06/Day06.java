package org.advent.day06;

import org.advent.utils.ClasspathFileReader;

public class Day06 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day06.input");
        System.out.println(new Day06Part1().solve(lines));
        System.out.println(new Day06Part2().solve(lines));
    }

}
