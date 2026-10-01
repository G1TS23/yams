package io.github.vbetsch.yams;

public class CategoryNotAuthorizedForThisRollError extends IllegalArgumentException {
    public CategoryNotAuthorizedForThisRollError() {
        super("We cannot compute score with category ThreeOfAKind for this roll");
    }
}
