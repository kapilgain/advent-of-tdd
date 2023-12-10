package org.advent.day02;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameValidatorTest {

    @Test
    void gameInvalidIfAnyRedRevealIsMoreThanMaxRed() {
        var game = new Game(1);
        game.addReveal(new RGB(1, 0, 0));
        game.addReveal(new RGB(2, 0, 0));
        var underTest = new GameValidator();
        assertFalse(underTest.isGameValid(game, new RGB(0, 1, 1)));
        assertFalse(underTest.isGameValid(game, new RGB(1, 1, 1)));
    }

    @Test
    void gameInvalidIfAnyGreenRevealIsMoreThanMaxGreen() {
        var game = new Game(1);
        game.addReveal(new RGB(0, 1, 0));
        game.addReveal(new RGB(0, 2, 0));
        var underTest = new GameValidator();
        assertFalse(underTest.isGameValid(game, new RGB(1, 0, 1)));
        assertFalse(underTest.isGameValid(game, new RGB(1, 1, 1)));
    }

    @Test
    void gameInvalidIfAnyBlueRevealIsMoreThanMaxBlue() {
        var game = new Game(1);
        game.addReveal(new RGB(0, 0, 1));
        game.addReveal(new RGB(0, 0, 2));
        var underTest = new GameValidator();
        assertFalse(underTest.isGameValid(game, new RGB(1, 1, 0)));
        assertFalse(underTest.isGameValid(game, new RGB(1, 1, 1)));
    }


    @Test
    void gameValidIfAllRevealsDoNotExceedMaxColours() {
        var game = new Game(1);
        game.addReveal(new RGB(1, 2, 3));
        game.addReveal(new RGB(4, 5, 6));
        var underTest = new GameValidator();
        assertTrue(underTest.isGameValid(game, new RGB(4, 5, 6)));
        assertTrue(underTest.isGameValid(game, new RGB(7, 8, 9)));
    }

    @Test
    void zeroSumForEmptyGameList() {
        var validator = new GameValidator();
        var underTest = validator.sumOfValidGameIds(Collections.emptyList(), new RGB(1, 1, 1));
        assertEquals(0, underTest);
    }

    @Test
    void sumsValidGameIds() {
        var game1 = new Game(1);
        game1.addReveal(new RGB(4, 0, 3));
        game1.addReveal(new RGB(1, 2, 6));
        game1.addReveal(new RGB(0, 2, 0));

        var game2 = new Game(2);
        game2.addReveal(new RGB(0, 2, 1));
        game2.addReveal(new RGB(1, 3, 4));
        game2.addReveal(new RGB(1, 1, 0));

        var game3 = new Game(3);
        game3.addReveal(new RGB(20, 8, 6));
        game3.addReveal(new RGB(4, 13, 5));
        game3.addReveal(new RGB(1, 5, 0));

        var game4 = new Game(4);
        game4.addReveal(new RGB(3, 1, 6));
        game4.addReveal(new RGB(6, 3, 0));
        game4.addReveal(new RGB(14, 3, 15));

        var game5 = new Game(5);
        game5.addReveal(new RGB(6, 3, 1));
        game5.addReveal(new RGB(1, 2, 2));

        var validator = new GameValidator();
        var underTest = validator.sumOfValidGameIds(
                List.of(game1, game2, game3, game4, game5),
                new RGB(12, 13, 14)
        );

        assertEquals(8, underTest);
    }

}
