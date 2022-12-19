package com.gameplay.app.Services.Games;

import org.springframework.stereotype.Service;

@Service
public class DiceScore {
    public static int[] calcFreq(int[] arr){
        int[] freq = {0,0,0,0,0,0};
        for (int i : arr) {
            freq[i-1]++;
        }
        return freq;
    }

    public static int diceScore(int[] arr){

        int[] freq = calcFreq(arr);
        int result = 0;

        for (int i = 0; i < freq.length; i++) {
            if (i == 0) {  //Number 1
                if(freq[i] < 3)
                    result += freq[i]*100;
                else if (freq[i] == 3) {
                    result += 1000;
                } else result += 1000 + (freq[i]-3)*100;
            }

            if (i == 4) {
                if (freq[i] < 3)
                    result += freq[i]*50;
                else if (freq[i] == 3) {
                    result += 500;
                } else result += 500 + (freq[i]-3)*50;
            } else if (freq[i] >= 3) {
                result += (i+1)*100;
            }
        }
        return result;
    }
}
