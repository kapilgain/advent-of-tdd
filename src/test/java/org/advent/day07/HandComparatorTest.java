package org.advent.day07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandComparatorTest {

    private HandComparator underTest;

    @BeforeEach
    public void setup() {
        this.underTest = new HandComparator();
    }

    @Test
    public void handsWithSameCardsInSameOrderAreEqual() {
        assertEquals(0, underTest.compare(Hand.parse("A2345 0"), Hand.parse("A2345 1")));
    }

    @Test
    public void handsWithSameCardsInDifferentOrderConsiderRankOfEarliestCard() {
        assertTrue(underTest.compare(Hand.parse("A2345 0"), Hand.parse("2345A 1")) > 0);
    }

    @Test
    public void threeOfKindStrongerThanPair() {
        var pair = Hand.parse("32T3K 765");
        var threeOfKind = Hand.parse("QQQJA 483");
        assertTrue(underTest.compare(threeOfKind, pair) > 0);
    }

    @Test
    public void strongerCardWinsWhenHandTypeIsSame() {
        assertTrue(underTest.compare(Hand.parse("T55J5 684"), Hand.parse("QQQJA 483")) < 0);
        assertTrue(underTest.compare(Hand.parse("77888 0"), Hand.parse("77788 0")) > 0);
    }

}
