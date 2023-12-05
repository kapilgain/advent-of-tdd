package org.advent.day03;

import org.advent.utils.ClasspathFileReader;

public class Day03 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day03.input");
        System.out.println(new Day03Part1().solve(lines));
        System.out.println(new Day03Part2().solve(lines));
    }

}
