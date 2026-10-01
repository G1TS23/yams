package io.github.vbetsch.yams;

import java.util.List;
import java.util.stream.IntStream;

public class Yams {
    private int handleChanceScore(List<Integer> combination) {
        int result = 0;
        for (Integer integer : combination) {
            result += integer;
        }
        return result;
    }

    private int handleYamsScore(List<Integer> combination) {
        IntStream reducedCombination = combination
                .stream()
                .mapToInt(Integer::intValue)
                .distinct();
        if (reducedCombination.count() == 1) {
            IO.println("ITS A YAMS!!! GOOD GAME !!!");
            return 50;
        } else {
            return 0;
        }
    }

    private int handleTopPartScores(List<Integer> combination, int target) {
        return combination
                .stream()
                .filter(dice -> dice == target)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int handleThreeOfAKindScore(List<Integer> combination) {
        int result = 0;
        for (Integer integer : combination) {
            result += integer;
        }
        return result;
    }

    public int computeScore(List<Integer> combination, CategoryEnum category) {
        return switch (category) {
            case CategoryEnum.CHANCE -> this.handleChanceScore(combination);
            case CategoryEnum.YAMS -> this.handleYamsScore(combination);
            case CategoryEnum.ACES -> this.handleTopPartScores(combination, 1);
            case CategoryEnum.TWOS -> this.handleTopPartScores(combination, 2);
            case CategoryEnum.THREES -> this.handleTopPartScores(combination, 3);
            case CategoryEnum.FOURS -> this.handleTopPartScores(combination, 4);
            case CategoryEnum.FIVES -> this.handleTopPartScores(combination, 5);
            case CategoryEnum.SIXES -> this.handleTopPartScores(combination, 6);
            case CategoryEnum.THREE_OF_A_KIND -> this.handleThreeOfAKindScore(combination);
            default -> 1000;
        };
    }
}
