package org.advent.day07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CardComparator implements Comparator<Card> {

    private static final List<Character> DESC_ORDER = new ArrayList<>(16);

    static {
        DESC_ORDER.add('A');
        DESC_ORDER.add('K');
        DESC_ORDER.add('Q');
        DESC_ORDER.add('J');
        DESC_ORDER.add('T');
        DESC_ORDER.add('9');
        DESC_ORDER.add('8');
        DESC_ORDER.add('7');
        DESC_ORDER.add('6');
        DESC_ORDER.add('5');
        DESC_ORDER.add('4');
        DESC_ORDER.add('3');
        DESC_ORDER.add('2');
    }

    @Override
    public int compare(Card c1, Card c2) {
        return DESC_ORDER.indexOf(c2.label()) - DESC_ORDER.indexOf(c1.label());
    }

}
