package org.advent.day16;

import org.advent.utils.ClasspathFileReader;

public class Day16 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day16.input");
        System.out.println(new Day16Part1().solve(lines));
        System.out.println(new Day16Part2().solve(lines));
    }

}
