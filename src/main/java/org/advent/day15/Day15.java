package org.advent.day15;

import org.advent.utils.ClasspathFileReader;

public class Day15 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day15.input");
        System.out.println(new Day15Part1().solve(lines));
        System.out.println(new Day15Part2().solve(lines));
    }

}
