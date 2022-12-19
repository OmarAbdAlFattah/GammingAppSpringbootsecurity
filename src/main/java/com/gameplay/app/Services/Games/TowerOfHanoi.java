package com.gameplay.app.Services.Games;

import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
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
}
