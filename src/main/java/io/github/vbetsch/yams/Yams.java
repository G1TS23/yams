package io.github.vbetsch.yams;

import java.util.List;
import java.util.stream.IntStream;

public class Yams {
    private int handleChanceScore(List<Integer> roll) {
        int result = 0;
        for (Integer integer : roll) {
            result += integer;
        }
        return result;
    }

    private int handleYamsScore(List<Integer> roll) {
        IntStream reducedRoll = roll
                .stream()
                .mapToInt(Integer::intValue)
                .distinct();
        if (reducedRoll.count() == 1) {
            IO.println("ITS A YAMS!!! GOOD GAME !!!");
            return 50;
        } else {
            return 0;
        }
    }

    private int handleTopPartScores(List<Integer> roll, int target) {
        return roll
                .stream()
                .filter(dice -> dice == target)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private boolean rollContainsThreeOfAKind(List<Integer> roll) {
        IntStream reducedRoll = roll
                .stream()
                .mapToInt(Integer::intValue)
                .distinct();
        return reducedRoll.count() == 3;
    }

    private int handleThreeOfAKindScore(List<Integer> roll) throws IllegalArgumentException {
        if (!this.rollContainsThreeOfAKind(roll)) {
            throw new IllegalArgumentException("We cannot compute score with category ThreeOfAKind for this roll");
        }
        return roll
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int computeScore(List<Integer> roll, CategoryEnum category) {
        return switch (category) {
            case CategoryEnum.CHANCE -> this.handleChanceScore(roll);
            case CategoryEnum.YAMS -> this.handleYamsScore(roll);
            case CategoryEnum.ACES -> this.handleTopPartScores(roll, 1);
            case CategoryEnum.TWOS -> this.handleTopPartScores(roll, 2);
            case CategoryEnum.THREES -> this.handleTopPartScores(roll, 3);
            case CategoryEnum.FOURS -> this.handleTopPartScores(roll, 4);
            case CategoryEnum.FIVES -> this.handleTopPartScores(roll, 5);
            case CategoryEnum.SIXES -> this.handleTopPartScores(roll, 6);
            case CategoryEnum.THREE_OF_A_KIND -> this.handleThreeOfAKindScore(roll);
            default -> 1000;
        };
    }
}
