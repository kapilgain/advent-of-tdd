package org.advent.day20;

import org.advent.utils.ClasspathFileReader;

public class Day20 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day20.input");
        System.out.println(new Day20Part1().solve(lines));
        System.out.println(new Day20Part2().solve(lines));
    }

}
