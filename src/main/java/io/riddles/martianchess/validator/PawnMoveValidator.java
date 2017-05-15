package io.riddles.martianchess.validator;

import io.riddles.martianchess.data.MartianChessPiece;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.model.ChessPieceColor;
import io.riddles.martianchess.model.MartianChessPieceType;
import io.riddles.martianchess.move.MartianChessMove;
import io.riddles.martianchess.model.ValidationResult;

import java.awt.*;

/**
 * ${PACKAGE_NAME}
 *
 * This file is a part of martianchess
 *
 * Copyright 2016 - present Riddles.io
 * For license information see the LICENSE file in the project root
 *
 * @author Joost
 */
public class PawnMoveValidator extends ChessPieceMoveValidator implements MoveValidator<MartianChessState> {

    @Override
    public Boolean isApplicable(MartianChessMove move, MartianChessState state) {

        return this.isMovedPieceOfType(move, state.getBoard(), MartianChessPieceType.PAWN);
    }

    @Override
    public ValidationResult validate(MartianChessMove move, MartianChessState state) {

        // Pawns move one space diagonally in any direction. (Unlike chess pawns, they may move backwards.)

        Point from = move.getFrom();
        Point to = move.getTo();

        int deltaX = Math.abs(to.x - from.x);
        int deltaY = Math.abs(to.y - from.y);

        MartianChessPiece chessPiece = state.getBoard().getFieldAt(from);

        boolean isValid = false;

        if (deltaX == deltaY && deltaX == 1) {
            isValid = true;
        }

        if (isValid) {
            return new ValidationResult(true, "");
        }

        return new ValidationResult(false, "The pawn can only move one space diagonally.");
    }
}
