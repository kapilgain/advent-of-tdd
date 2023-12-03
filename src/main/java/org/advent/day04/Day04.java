package org.advent.day04;

import org.advent.ClasspathFileReader;

public class Day04 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day04.input");
        System.out.println(new Day04Part1().solve(lines));
//        System.out.println(new Part2().solve(lines));
    }

}
