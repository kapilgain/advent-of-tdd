package org.advent.day25;

import org.advent.utils.ClasspathFileReader;

public class Day25 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day25.input");
        System.out.println(new Day25Part1().solve(lines));
    }

}
