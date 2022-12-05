package com.gameplay.app.DTOS.Requests;

public class DiceScoreRequestDTO {
    private int[] arr = new int[5];

    public void setArr(int[] arr) {
        this.arr = arr;
    }
    public int[] getArr() {
        return arr;
    }
}
