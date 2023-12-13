package org.advent.day13;

import org.advent.utils.ClasspathFileReader;

public class Day13 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day13.input");
        System.out.println(new Day13Part1().solve(lines));
        System.out.println(new Day13Part2().solve(lines));
    }

}
