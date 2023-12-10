package org.advent.day10;

import org.advent.utils.ClasspathFileReader;

public class Day10 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day10.input");
        System.out.println(new Day10Part1().solve(lines));
        System.out.println(new Day10Part2().solve(lines));
    }

}
