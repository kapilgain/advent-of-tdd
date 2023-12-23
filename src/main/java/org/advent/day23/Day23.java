package org.advent.day23;

import org.advent.utils.ClasspathFileReader;

public class Day23 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day23.input");
        System.out.println(new Day23Part1().solve(lines));
        System.out.println(new Day23Part2().solve(lines));
    }

}
