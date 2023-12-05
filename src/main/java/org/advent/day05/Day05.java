package org.advent.day05;

import org.advent.utils.ClasspathFileReader;

public class Day05 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day05.input");
        System.out.println(new Day05Part1().solve(lines));
        System.out.println(new Day05Part2().solve(lines));
    }

}
