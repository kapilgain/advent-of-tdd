package org.advent.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameParserTest {

    @Test
    void throwsRuntimeExceptionIfGameDataNotInCorrectFormat() {
        var underTest = new GameParser();
        assertThrows(RuntimeException.class, () -> underTest.parse("Hello World"));
        assertThrows(RuntimeException.class, () -> underTest.parse("Game notAnIntegerId: 1 red, 2 green, 3 blue"));
    }

    @Test
    void parsesGameId() {
        var underTest = new GameParser();

        assertEquals(
                1,
                underTest.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green").getId()
        );

        assertEquals(
                2,
                underTest.parse("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue").getId()
        );

        assertEquals(
                3,
                underTest.parse("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red").getId()
        );

        assertEquals(
                4,
                underTest.parse("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red").getId()
        );

        assertEquals(
                5,
                underTest.parse("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green").getId()
        );
    }

    @Test
    void parsesReveals() {
        var underTest = new GameParser();

        assertEquals(
                List.of(
                        new RGB(4, 0, 3),
                        new RGB(1, 2, 6),
                        new RGB(0, 2, 0)
                ),
                underTest.parse("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green").getReveals());

        assertEquals(
                List.of(
                        new RGB(0, 2, 1),
                        new RGB(1, 3, 4),
                        new RGB(0, 1, 1)
                ),
                underTest.parse("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue").getReveals()
        );

        assertEquals(
                List.of(
                        new RGB(20, 8, 6),
                        new RGB(4, 13, 5),
                        new RGB(1, 5, 0)
                ),
                underTest.parse(
                        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
                ).getReveals()
        );

        assertEquals(
                List.of(
                        new RGB(3, 1, 6),
                        new RGB(6, 3, 0),
                        new RGB(14, 3, 15)
                ),
                underTest.parse(
                        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
                ).getReveals()
        );

        assertEquals(
                List.of(
                        new RGB(6, 3, 1),
                        new RGB(1, 2, 2)
                ),
                underTest.parse("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green").getReveals()
        );
    }

}
