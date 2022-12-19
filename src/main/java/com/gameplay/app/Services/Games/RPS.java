package com.gameplay.app.Services.Games;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class RPS {
    
    public static String rps(String choice1, String choice2){
        choice2 = choice2.toLowerCase();
        choice1 = choice1.toLowerCase();
        String result = "";
        if (choice1.equals(choice2))
            return result = "DRAW";
        else {
            boolean oneIsSiccor = choice1.equals("scissor") | choice2.equals("scissor");
            boolean oneIsRock = choice1.equals("rock") | choice2.equals("rock");
            boolean oneIsPaper = choice1.equals("paper") | choice2.equals("paper");

            if (oneIsRock & oneIsSiccor) {
                return result = "ROCK";
            } else {
                if (oneIsPaper & oneIsRock) {
                    return result = "PAPER";
                } else if(oneIsSiccor & oneIsPaper)
                    return result = "SCISSOR";
            }
        }
        return result;
    }
}
