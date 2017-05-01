package io.riddles.martianchess.validator;

import io.riddles.martianchess.move.ChessMove;
import io.riddles.martianchess.model.ValidationResult;

/**
 * ${PACKAGE_NAME}
 *
 * This file is a part of martianchess
 *
 * Copyright 2016 - present Riddles.io
 * For license information see the LICENSE file in the project root
 *
 * @author Niko
 */
public interface MoveValidator<S> {

    Boolean isApplicable(ChessMove move, S state);

    ValidationResult validate(ChessMove move, S state);
}