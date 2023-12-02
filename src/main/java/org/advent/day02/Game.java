package org.advent.day02;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Game {

    private final int id;

    private final List<RGB> reveals = new ArrayList<>();

    public void addReveal(RGB reveal) {
        reveals.add(reveal);
    }

}
