package org.advent.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {

    @Test
    public void initialisesHandOfCards() {
        var underTest = new Hand(
                765,
                List.of(
                        new Card('3'),
                        new Card('2'),
                        new Card('T'),
                        new Card('3'),
                        new Card('K')
                ));

        assertEquals(765, underTest.bid());
        assertEquals(
                List.of(
                        new Card('3'),
                        new Card('2'),
                        new Card('T'),
                        new Card('3'),
                        new Card('K')
                ),
                underTest.cards()
        );
    }

    @Test
    public void parsesHandFromString() {
        var underTest = Hand.parse("32T3K 765");
        assertEquals(765, underTest.bid());
        assertEquals(
                List.of(
                        new Card('3'),
                        new Card('2'),
                        new Card('T'),
                        new Card('3'),
                        new Card('K')
                ),
                underTest.cards()
        );
    }

}
