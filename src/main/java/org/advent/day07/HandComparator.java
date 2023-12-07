package org.advent.day07;

import java.util.Comparator;

public class HandComparator implements Comparator<Hand> {

    @Override
    public int compare(Hand h1, Hand h2) {
        var strengthDiff = compareHandStrength(h1, h2);
        if (strengthDiff == 0) {
            return compareCards(h1, h2);
        }

        return strengthDiff;
    }

    public int compareHandStrength(Hand h1, Hand h2) {
        return HandType.of(h1).getStrength() - HandType.of(h2).getStrength();
    }

    public int compareCards(Hand h1, Hand h2) {
        for (var i = 0; i < h1.cards().size(); i++) {
            var c1 = h1.cards().get(i);
            var c2 = h2.cards().get(i);
            var cardDiff = getCardComparator().compare(c1, c2);
            if (cardDiff != 0) {
                return cardDiff;
            }
        }

        return 0;
    }

    public Comparator<Card> getCardComparator() {
        return new CardComparator();
    }

}
