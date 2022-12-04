package com.gameplay.app.Games;

import java.util.Stack;

public class TowerOfHanoi {
    public static int counter;
    public static int hanoi(int numberOfDisks, char source, char dest, char aux){

        if (numberOfDisks == 0)
            System.err.println("CANNOT HAVE 0 disks");

        if (numberOfDisks == 1) {
            counter++;
            return counter;
        }
        else {
            hanoi(numberOfDisks-1, source,aux,dest);
            hanoi(numberOfDisks-1, aux, dest, source);
            counter++;
            return counter;
        }
    }

    public static void main(String args[])
    {
        int n = 0; // Number of disks

        System.out.println(hanoi(n, 'A','C', 'B')); // A, B and C are names of rods
    }
}
