package io.riddles.martianchess.validator;

import io.riddles.martianchess.move.MartianChessMove;
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

    Boolean isApplicable(MartianChessMove move, S state);

    ValidationResult validate(MartianChessMove move, S state);
}