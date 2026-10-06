package io.github.vbetsch.yams;

public class InvalidRollSizeError extends IllegalArgumentException {
    public InvalidRollSizeError(int actualSize) {
        super("Roll must contain exactly " + Yams.DICES_AMOUNT + " dices but got " + actualSize);
    }
}
