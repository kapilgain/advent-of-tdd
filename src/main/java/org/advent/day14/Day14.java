package org.advent.day14;

import org.advent.utils.ClasspathFileReader;

public class Day14 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day14.input");
        System.out.println(new Day14Part1().solve(lines));
        System.out.println(new Day14Part2().solve(lines));
    }

}
