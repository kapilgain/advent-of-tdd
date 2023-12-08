package org.advent.day08;

import org.advent.utils.ClasspathFileReader;

public class Day08 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day08.input");
        System.out.println(new Day08Part1().solve(lines));
        System.out.println(new Day08Part2().solve(lines));
    }

}
