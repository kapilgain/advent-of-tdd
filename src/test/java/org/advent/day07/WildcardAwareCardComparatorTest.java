
package org.advent.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WildcardAwareCardComparatorTest {

    @Test
    public void comparesRelativeStrengthOfCards() {
        assertTrue(new WildcardAwareCardComparator().compare(new Card('A'), new Card('2')) > 0);
        assertTrue(new WildcardAwareCardComparator().compare(new Card('9'), new Card('T')) < 0);
        assertEquals(0, new WildcardAwareCardComparator().compare(new Card('J'), new Card('J')));
        assertTrue(new WildcardAwareCardComparator().compare(new Card('2'), new Card('J')) > 0);
        assertTrue(new WildcardAwareCardComparator().compare(new Card('J'), new Card('2')) < 0);
    }

}
