package io.riddles.martianchess.model;

/**
 * ${PACKAGE_NAME}
 *
 * This file is a part of martianchess
 *
 * Copyright 2016 - present Riddles.io
 * For license information see the LICENSE file in the project root
 *
 * @author joost
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
