package org.advent.day12;

import org.advent.utils.ClasspathFileReader;

public class Day12 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day12.input");
        System.out.println(new Day12Part1().solve(lines));
        System.out.println(new Day12Part2().solve(lines));
    }

}
