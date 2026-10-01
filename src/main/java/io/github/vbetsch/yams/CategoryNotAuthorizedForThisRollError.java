package io.github.vbetsch.yams;

public class CategoryNotAuthorizedForThisRollError extends IllegalArgumentException {
    public CategoryNotAuthorizedForThisRollError(CategoryEnum category) {
        super("We cannot compute score with category " + category + " for this roll");
    }
}
