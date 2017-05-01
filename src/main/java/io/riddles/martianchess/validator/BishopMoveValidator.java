package io.riddles.martianchess.validator;

import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.move.ChessMove;
import io.riddles.martianchess.model.ValidationResult;
import io.riddles.martianchess.model.*;

import java.awt.*;

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
public class BishopMoveValidator extends ChessPieceMoveValidator implements MoveValidator<MartianChessState> {

    @Override
    public Boolean isApplicable(ChessMove move, MartianChessState state) {

        return this.isMovedPieceOfType(move, state.getBoard(), ChessPieceType.BISHOP);
    }

    @Override
    public ValidationResult validate(ChessMove move, MartianChessState state) {

        Point from = move.getFrom();
        Point to = move.getTo();

        int deltaX = Math.abs(to.x - from.x);
        int deltaY = Math.abs(to.y - from.y);

        boolean isValid = deltaX == deltaY;

        if (isValid) {
            return new ValidationResult(true, "");
        }

        return new ValidationResult(false, "The bishop can only move diagonally");
    }
}
