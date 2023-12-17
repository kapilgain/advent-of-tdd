package org.advent.day17;

import org.advent.utils.ClasspathFileReader;

public class Day17 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day17.input");
        System.out.println(new Day17Part1().solve(lines));
        System.out.println(new Day17Part2().solve(lines));
    }

}
