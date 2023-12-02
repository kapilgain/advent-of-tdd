package org.advent.day02;

import java.util.List;
import java.util.function.Predicate;

public class GameValidator {

    public boolean isGameValid(Game game, RGB maxBalls) {
        for (var reveal : game.getReveals()) {
            if (reveal.red() > maxBalls.red()) {
                return false;
            }

            if (reveal.green() > maxBalls.green()) {
                return false;
            }

            if (reveal.blue() > maxBalls.blue()) {
                return false;
            }
        }

        return true;
    }

    public int sumOfValidGameIds(List<Game> games, RGB maxBalls) {
        Predicate<Game> validator = game -> isGameValid(game, maxBalls);
        return games.stream().filter(validator).map(Game::getId).mapToInt(Integer::intValue).sum();
    }

}
