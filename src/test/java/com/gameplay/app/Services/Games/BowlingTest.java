package com.gameplay.app.Services.Games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingTest {
    Bowling game;
    ArrayList<Integer> rolls;

    @BeforeEach
    void setUp() {
        game = new Bowling();
        rolls = new ArrayList<>();

    }

    @Test
    public void rollZeroScoreIsZero() {
        rolls.add(0);
        assertEquals(0,game.play(rolls));
    }

    @Test
    public void openFramesAddsPins() {
        rolls.add(2);
        rolls.add(4);
        assertEquals(6, game.play(rolls));
    }

    @Test
    public void spareAddNextBall(){
        rolls.add(6);
        rolls.add(4);
        rolls.add(3);
        rolls.add(0);

        assertEquals(16, game.play(rolls));
    }

    @Test
    public void aTenInTwoFramesIsNotAFrame(){
        rolls.add(3);
        rolls.add(6);
        rolls.add(4);
        rolls.add(2);
        assertEquals(15,game.play(rolls));

    }

    @Test
    public void aStrikeAddsNextTwoBalls() {
        rolls.add(10);
        rolls.add(3);
        rolls.add(2);
        assertEquals(20, game.play(rolls));
    }
    @Test
    public void perfectGameScoreIs300(){
        for(int i=0;i<12;i++)
        {
            rolls.add(10);
        }
        assertEquals(300, game.play(rolls));
    }

    @Test
    public void testPlayMainFunction() {
        rolls.add(10);
        rolls.add(3);
        rolls.add(2);

        assertEquals(20, game.play(rolls));
    }
}