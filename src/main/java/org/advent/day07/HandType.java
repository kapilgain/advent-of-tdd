package org.advent.day07;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Getter
@RequiredArgsConstructor
public enum HandType {

    FIVE_OF_A_KIND(1 << 7),
    FOUR_OF_A_KIND(1 << 6),
    FULL_HOUSE(1 << 5),
    THREE_OF_A_KIND(1 << 4),
    TWO_PAIRS(1 << 3),
    ONE_PAIR(1 << 2),
    HIGH_CARD(1 << 1);

    private final int strength;

    public static HandType of(Hand hand) {
        var labelCounts = hand.cards()
                .stream()
                .collect(groupingBy(Card::label, LinkedHashMap::new, summingInt(c -> 1)));

        return switch (labelCounts.values().stream().max(naturalOrder()).orElse(0)) {
            case 5 -> FIVE_OF_A_KIND;
            case 4 -> FOUR_OF_A_KIND;
            case 3 -> labelCounts.containsValue(2) ? FULL_HOUSE : THREE_OF_A_KIND;
            case 2 -> labelCounts.values().stream().filter(c -> c == 2).count() == 2 ? TWO_PAIRS : ONE_PAIR;
            default -> HIGH_CARD;
        };
    }

    public static HandType of(Hand hand, boolean isWildcard) {
        var type = of(hand);
        var numJokers = hand.cards().stream().filter(card -> card.label() == 'J').count();
        if (!isWildcard || numJokers == 0) {
            return type;
        }

        return switch (type) {
            case FIVE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE -> FIVE_OF_A_KIND;
            case THREE_OF_A_KIND -> FOUR_OF_A_KIND;
            case TWO_PAIRS -> numJokers == 2 ? FOUR_OF_A_KIND : FULL_HOUSE;
            case ONE_PAIR -> THREE_OF_A_KIND;
            default -> ONE_PAIR;
        };
    }

}
