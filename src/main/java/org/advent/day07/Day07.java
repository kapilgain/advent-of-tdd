package org.advent.day07;

import org.advent.utils.ClasspathFileReader;

public class Day07 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day07.input");
        System.out.println(new Day07Part1().solve(lines));
        System.out.println(new Day07Part2().solve(lines));
    }

}
