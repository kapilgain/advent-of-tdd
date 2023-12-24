package org.advent.day24;

import org.advent.utils.ClasspathFileReader;

public class Day24 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day24.input");
        System.out.println(new Day24Part1().solve(lines));
        System.out.println(new Day24Part2().solve(lines));
    }

}
