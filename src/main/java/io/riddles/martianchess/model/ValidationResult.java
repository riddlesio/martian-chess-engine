package io.riddles.martianchess.model;

/**
 * Created by joost on 4/18/17.
 */

public class ValidationResult extends Tuple<Boolean, String> {

    public ValidationResult(Boolean isValid, String reason) {
        super(isValid, reason);
    }

    public boolean isValid() {
        return this.getFirst();
    }

    public String getReason() {
        return this.getSecond();
    }
}
