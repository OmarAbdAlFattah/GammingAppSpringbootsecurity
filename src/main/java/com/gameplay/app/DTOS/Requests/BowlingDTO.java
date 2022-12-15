package com.gameplay.app.DTOS.Requests;

import java.util.ArrayList;

public class BowlingDTO {
    private ArrayList<Integer> rolls = new ArrayList<>();

    public ArrayList<Integer> getRolls() {
        return rolls;
    }

    public void setRolls(ArrayList<Integer> rolls) {
        this.rolls = rolls;
    }
}
