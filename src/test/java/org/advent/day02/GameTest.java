package org.advent.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    void gameIdSetsCorrectly() {
        var underTest = new Game(10);
        assertEquals(10, underTest.getId());
    }

    @Test
    public void noRevealsOnInitialisation() {
        var underTest = new Game(1);
        assertEquals(0, underTest.getReveals().size());
    }

    @Test
    public void addsRevealedEntriesCorrectly() {
        var underTest = new Game(1);
        underTest.addReveal(new RGB(1, 2, 3));
        underTest.addReveal(new RGB(4, 5, 6));
        assertEquals(2, underTest.getReveals().size());
        assertEquals(new RGB(1, 2, 3), underTest.getReveals().get(0));
        assertEquals(new RGB(4, 5, 6), underTest.getReveals().get(1));
    }

}
