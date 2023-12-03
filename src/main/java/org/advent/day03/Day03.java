package org.advent.day03;

import org.advent.ClasspathFileReader;

public class Day03 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day03.input");
        System.out.println(new Part1().solve(lines));
        System.out.println(new Part2().solve(lines));
    }

}
