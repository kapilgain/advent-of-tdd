package org.advent.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @Test
    public void cardLabelSetCorrectly() {
        assertEquals('A', new Card('A').label());
        assertEquals('2', new Card('2').label());
    }

}
