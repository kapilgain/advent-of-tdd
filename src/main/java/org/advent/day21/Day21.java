package org.advent.day21;

import org.advent.utils.ClasspathFileReader;

public class Day21 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day21.input");
        System.out.println(new Day21Part1().solve(lines));
        System.out.println(new Day21Part2().solve(lines));
    }

}
