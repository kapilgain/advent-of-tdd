package org.advent.day07;

import org.advent.utils.StringUtils;

import java.util.List;

public record Hand(long bid, List<Card> cards) {

    public static Hand parse(String line) {
        var parts = line.split("\\s+");
        var cards = parts[0];
        var bid = Long.parseLong(parts[1]);
        return new Hand(bid, parseCards(cards));
    }

    public static List<Card> parseCards(String cards) {
        return StringUtils.splitToCharList(cards)
                .stream()
                .map(Card::new)
                .toList();
    }

}
