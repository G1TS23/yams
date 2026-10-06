package io.github.vbetsch.yams;

import java.util.List;
import java.util.stream.IntStream;

public class Yams {
    static final int DICES_AMOUNT = 5;

    private int calculateSumOfDices(List<Integer> roll) {
        return roll
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int handleChanceScore(List<Integer> roll) {
        return this.calculateSumOfDices(roll);
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

    private boolean containsDuplicatesNth(List<Integer> roll, int duplicatesNumber) {
        IntStream reducedRoll = roll
                .stream()
                .mapToInt(Integer::intValue)
                .distinct();
        return reducedRoll.count() == (Yams.DICES_AMOUNT - duplicatesNumber + 1);
    }

    private int handleThreeOfAKindScore(List<Integer> roll) throws IllegalArgumentException {
        if (!this.containsDuplicatesNth(roll, 3)) {
            throw new CategoryNotAuthorizedForThisRollError(CategoryEnum.THREE_OF_A_KIND);
        }
        return this.calculateSumOfDices(roll);
    }

    private int handleFourOfAKindScore(List<Integer> roll) throws IllegalArgumentException {
        if (!this.containsDuplicatesNth(roll, 4)) {
            throw new CategoryNotAuthorizedForThisRollError(CategoryEnum.FOUR_OF_A_KIND);
        }
        return this.calculateSumOfDices(roll);
    }

    public int computeScore(List<Integer> roll, CategoryEnum category) throws InvalidRollSizeError {
        if(roll.size() != Yams.DICES_AMOUNT) {
            throw new InvalidRollSizeError(roll.size());
        }
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
            case CategoryEnum.FOUR_OF_A_KIND -> this.handleFourOfAKindScore(roll);
            default -> 1000;
        };
    }
}
