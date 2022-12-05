package com.gameplay.app.DTOS.Requests;

public class TowerOfHanoiRequestDTO {
    private int numberOfDisks;
    private char source;
    private char dest;
    private char aux;

    private int counter;

    public int getNumberOfDisks() {
        return numberOfDisks;
    }

    public void setNumberOfDisks(int numberOfDisks) {
        this.numberOfDisks = numberOfDisks;
    }

    public char getSource() {
        return source;
    }

    public void setSource(char source) {
        this.source = source;
    }

    public char getDest() {
        return dest;
    }

    public void setDest(char dest) {
        this.dest = dest;
    }

    public char getAux() {
        return aux;
    }

    public void setAux(char aux) {
        this.aux = aux;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
