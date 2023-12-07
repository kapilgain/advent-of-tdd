package org.advent.day07;

public class WildcardAwareCardComparator extends CardComparator {

    @Override
    public int compare(Card c1, Card c2) {
        if (c1.label() != 'J' && c2.label() != 'J') {
            return super.compare(c1, c2);
        }

        if (c1.label() == c2.label()) {
            return 0;
        }

        return c1.label() == 'J' ? -1 : 1;
    }

}
