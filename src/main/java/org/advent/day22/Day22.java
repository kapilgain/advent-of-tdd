package org.advent.day22;

import org.advent.utils.ClasspathFileReader;

public class Day22 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day22.input");
        System.out.println(new Day22Part1().solve(lines));
        System.out.println(new Day22Part2().solve(lines));
    }

}
