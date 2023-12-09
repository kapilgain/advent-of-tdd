package org.advent.day09;

import org.advent.utils.ClasspathFileReader;

public class Day09 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day09.input");
        System.out.println(new Day09Part1().solve(lines));
        System.out.println(new Day09Part2().solve(lines));
    }

}
