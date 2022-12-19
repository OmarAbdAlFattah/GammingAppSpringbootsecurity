package com.gameplay.app.Services.Games;

import org.springframework.stereotype.Service;

@Service

public class PokemonDamageCalculator {

    public static double calcEffectiveness(String type1, String type2){
        type1 = type1.toLowerCase();
        type2 = type2.toLowerCase();
        double effectiveness = 0;
        switch (type1) {
            case "fire":
                switch (type2) {
                    case "grass":
                        return effectiveness = 2;

                    case "water":
                        return effectiveness = 0.5;
                    case "electric":
                        return effectiveness = 1;
                    default:
                        return effectiveness = 1;

                }
            case "water":
                switch (type2) {
                    case "grass":
                        return effectiveness = 0.5;

                    case "fire":
                        return effectiveness = 2;

                    case "electric":
                        return effectiveness = 0.5;
                    default:
                        return effectiveness = 1;

                }
            case "grass":
                switch (type2) {
                    case "water":
                        return effectiveness = 2;

                    case "fire":
                        return effectiveness = 0.5;

                    case "electric":
                        return effectiveness = 1;
                    default:
                        return effectiveness = 1;

                }
            case "electric":
                switch (type2) {
                    case "grass":
                        return effectiveness = 1;

                    case "fire":
                        return effectiveness = 1;

                    case "water":
                        return effectiveness = 2;
                    default:
                        return effectiveness = 1;

                }


        }
        return effectiveness;
    }

    public static double calcDamage(String type1, String type2, double attack, double defence) {
        double effect = calcEffectiveness(type1, type2);
        return 50 * (attack / defence) * effect;
    }
}
