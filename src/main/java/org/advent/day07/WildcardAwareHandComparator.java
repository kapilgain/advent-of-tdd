package org.advent.day07;

import java.util.Comparator;

public class WildcardAwareHandComparator extends HandComparator {

    @Override
    public int compareHandStrength(Hand h1, Hand h2) {
        return HandType.of(h1, true).getStrength() - HandType.of(h2, true).getStrength();
    }

    @Override
    public Comparator<Card> getCardComparator() {
        return new WildcardAwareCardComparator();
    }

}
