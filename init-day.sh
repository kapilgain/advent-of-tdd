#!/usr/bin/env bash

if [ -z "$1" ]; then
  cat <<EOF
Usage: $0 <day>

eg: for Day 09
$0 09

EOF
exit 1
fi

DAY=$1
echo Initialising starter code for day ${DAY}

SRC_PATH=src/main/java/org/advent/day${DAY}
TEST_PATH=src/test/java/org/advent/day${DAY}

mkdir -p ${SRC_PATH}
mkdir -p ${TEST_PATH}


cat > ${SRC_PATH}/Day${DAY}.java <<EOF
package org.advent.day${DAY};

import org.advent.utils.ClasspathFileReader;

public class Day${DAY} implements Runnable {

    @Override
    public void run() {
        var lines = new ClasspathFileReader().readAllLines("day${DAY}.input");
        System.out.println(new Day${DAY}Part1().solve(lines));
        System.out.println(new Day${DAY}Part2().solve(lines));
    }

}
EOF


cat > ${SRC_PATH}/Day${DAY}Part1.java <<EOF
package org.advent.day${DAY};

import java.util.List;

public class Day${DAY}Part1 {

    public Number solve(List<String> lines) {
        return -1L;
    }

}
EOF


cat > ${SRC_PATH}/Day${DAY}Part2.java <<EOF
package org.advent.day${DAY};

import java.util.List;

public class Day${DAY}Part2 {

    public Number solve(List<String> lines) {
        return -1L;
    }

}
EOF


cat > ${TEST_PATH}/Day${DAY}Part1Test.java <<EOF
package org.advent.day${DAY};

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day${DAY}Part1Test {

    public static final List<String> TEST_DATA = Arrays.stream("""

            """
            .trim().split("\n")).toList();

    private Day${DAY}Part1 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day${DAY}Part1();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(-1L, underTest.solve(TEST_DATA));
    }

}
EOF


cat > ${TEST_PATH}/Day${DAY}Part2Test.java <<EOF
package org.advent.day${DAY};

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.advent.day${DAY}.Day${DAY}Part1Test.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day${DAY}Part2Test {

    private Day${DAY}Part2 underTest;

    @BeforeEach
    void setup() {
        underTest = new Day${DAY}Part2();
    }

    @Test
    void solvesForTestInput() {
        assertEquals(-1L, underTest.solve(TEST_DATA));
    }

}
EOF


cat > src/main/java/org/advent/Main.java <<EOF
package org.advent;

import org.advent.day${DAY}.Day${DAY};

public class Main {

    public static void main(String[] args) {
        new Day${DAY}().run();
    }

}
EOF


touch "src/main/resources/day${DAY}.input"
