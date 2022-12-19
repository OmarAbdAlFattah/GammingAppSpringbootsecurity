package com.gameplay.app.Services.Games;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiceScoreTest {

    @Test
      void calcFrequencyOfEachNumber() {
        int[] arr = {2, 3, 4, 6, 2};
        int[] expArr = {0,2,1,1,0,1};
        DiceScore diceScore = new DiceScore();
        Assert.assertArrayEquals(expArr,diceScore.calcFreq(arr));
    }

    @Test
    void diceScoreNoEdgeCases() {
        int[] arr = {2, 3, 4, 6, 2};
        assertEquals(0, DiceScore.diceScore(arr));

    }

    @Test
    void diceScoreMoreThanThreeFives(){
        int[] arr = {5,5,5,5,1};
        assertEquals(650, DiceScore.diceScore(arr));
    }

    @Test
    void diceScoreForAllButOnesAndFives(){
        int[] arr = {3,3,3,3,3};
        assertEquals(300, DiceScore.diceScore(arr));
    }

    @Test
    void diceScoreIfAllOnesShouldAddThousand(){
        int[] arr = {1,1,1,1,1};
        assertEquals(1200, DiceScore.diceScore(arr));
    }

}