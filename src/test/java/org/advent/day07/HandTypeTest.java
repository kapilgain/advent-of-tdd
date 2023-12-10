package org.advent.day07;

import org.junit.jupiter.api.Test;

import static org.advent.day07.HandType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTypeTest {

    @Test
    void identifiesFiveOfAKind() {
        assertEquals(FIVE_OF_A_KIND, HandType.of(Hand.parse("AAAAA 0")));
    }

    @Test
    void identifiesFourOfAKind() {
        assertEquals(FOUR_OF_A_KIND, HandType.of(Hand.parse("AA8AA 0")));
    }

    @Test
    void identifiesFullHouse() {
        assertEquals(FULL_HOUSE, HandType.of(Hand.parse("23332 0")));
    }

    @Test
    void identifiesThreeOfAKind() {
        assertEquals(THREE_OF_A_KIND, HandType.of(Hand.parse("TTT98 0")));
    }

    @Test
    void identifiesTwoPairs() {
        assertEquals(TWO_PAIRS, HandType.of(Hand.parse("23432 0")));
    }

    @Test
    void identifiesOnePair() {
        assertEquals(ONE_PAIR, HandType.of(Hand.parse("A23A4 0")));
    }

    @Test
    void highCardWhenAllLabelsAreDistinct() {
        assertEquals(HIGH_CARD, HandType.of(Hand.parse("23456 0")));
    }

    @Test
    void identifiesHandTypeForWildcardEnabled() {
        assertEquals(HIGH_CARD, HandType.of(Hand.parse("12345 1"), true));

        assertEquals(ONE_PAIR, HandType.of(Hand.parse("12344 2"), true));
        assertEquals(ONE_PAIR, HandType.of(Hand.parse("1234J 2"), true));

        assertEquals(TWO_PAIRS, HandType.of(Hand.parse("12233 3"), true));

        assertEquals(THREE_OF_A_KIND, HandType.of(Hand.parse("12333 4"), true));
        assertEquals(THREE_OF_A_KIND, HandType.of(Hand.parse("1233J 4"), true));
        assertEquals(THREE_OF_A_KIND, HandType.of(Hand.parse("123JJ 4"), true));

        assertEquals(FULL_HOUSE, HandType.of(Hand.parse("11222 5"), true));
        assertEquals(FULL_HOUSE, HandType.of(Hand.parse("1122J 5"), true));

        assertEquals(FOUR_OF_A_KIND, HandType.of(Hand.parse("12222 6"), true));
        assertEquals(FOUR_OF_A_KIND, HandType.of(Hand.parse("1222J 6"), true));
        assertEquals(FOUR_OF_A_KIND, HandType.of(Hand.parse("122JJ 6"), true));
        assertEquals(FOUR_OF_A_KIND, HandType.of(Hand.parse("12JJJ 6"), true));

        assertEquals(FIVE_OF_A_KIND, HandType.of(Hand.parse("11111 7"), true));
        assertEquals(FIVE_OF_A_KIND, HandType.of(Hand.parse("1111J 7"), true));
        assertEquals(FIVE_OF_A_KIND, HandType.of(Hand.parse("111JJ 7"), true));
        assertEquals(FIVE_OF_A_KIND, HandType.of(Hand.parse("11JJJ 7"), true));
        assertEquals(FIVE_OF_A_KIND, HandType.of(Hand.parse("1JJJJ 7"), true));
        assertEquals(FIVE_OF_A_KIND, HandType.of(Hand.parse("JJJJJ 7"), true));
    }


}
