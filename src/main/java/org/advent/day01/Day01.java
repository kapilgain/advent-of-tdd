package org.advent.day01;

import org.advent.utils.ClasspathFileReader;

public class Day01 implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day01.input");
        var answer = new CalibrationValueRecoverer().sum(lines);
        System.out.println(answer);
    }

}
