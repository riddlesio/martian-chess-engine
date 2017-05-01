package io.riddles.martianchess.validator;

import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.move.ChessMove;
import io.riddles.martianchess.model.ValidationResult;
import io.riddles.martianchess.model.*;

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
public class QueenMoveValidator extends ChessPieceMoveValidator implements MoveValidator<MartianChessState> {

    @Override
    public Boolean isApplicable(ChessMove move, MartianChessState state) {

        return this.isMovedPieceOfType(move, state.getBoard(), ChessPieceType.QUEEN);
    }

    @Override
    public ValidationResult validate(ChessMove move, MartianChessState state) {

        MoveValidator bishopMoveValidator = new BishopMoveValidator();
        MoveValidator rookMoveValidator = new RookMoveValidator();

        Boolean isValidBishopMove = bishopMoveValidator.validate(move, state).isValid();
        Boolean isValidRookMove = rookMoveValidator.validate(move, state).isValid();

        boolean isValid = isValidBishopMove || isValidRookMove;
        if (isValid) {
            return new ValidationResult(true, "");
        }

        return new ValidationResult(false, "The queen can only move horizontally, vertically or diagonally");
    }
}
