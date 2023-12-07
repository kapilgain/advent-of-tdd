package org.advent.day07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WildcardAwareHandComparatorTest {

    private HandComparator underTest;

    @BeforeEach
    public void setup() {
        this.underTest = new WildcardAwareHandComparator();
    }

    @Test
    public void handlesWildcardsWhenComparingHandStrengths() {
        var highCard = Hand.parse("32T3K 765");
        var twoPairs = Hand.parse("KK677 28");
        var fourOfKind1 = Hand.parse("T55J5 684");
        var fourOfKind2 = Hand.parse("KTJJT 220");
        var fourOfKind3 = Hand.parse("QQQJA 483");

        assertTrue(underTest.compare(highCard, twoPairs) < 0);
        assertTrue(underTest.compare(twoPairs, fourOfKind1) < 0);
        assertTrue(underTest.compare(fourOfKind1, fourOfKind2) < 0);
        assertTrue(underTest.compare(fourOfKind2, fourOfKind3) > 0);
        assertTrue(underTest.compare(fourOfKind1, fourOfKind3) < 0);
    }

    @Test
    public void jokerIsWeakestWhenComparingCardStrength() {
        assertTrue(underTest.compare(Hand.parse("J2345 0"), Hand.parse("2345J 0")) < 0);
    }

}
