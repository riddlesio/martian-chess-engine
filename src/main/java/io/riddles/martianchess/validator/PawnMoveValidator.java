package io.riddles.martianchess.validator;

import io.riddles.martianchess.data.ChessPiece;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.model.ChessPieceColor;
import io.riddles.martianchess.model.ChessPieceType;
import io.riddles.martianchess.move.ChessMove;
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
 * @author Niko
 */
public class PawnMoveValidator extends ChessPieceMoveValidator implements MoveValidator<MartianChessState> {

    @Override
    public Boolean isApplicable(ChessMove move, MartianChessState state) {

        return this.isMovedPieceOfType(move, state.getBoard(), ChessPieceType.PAWN);
    }

    @Override
    public ValidationResult validate(ChessMove move, MartianChessState state) {

        Point from = move.getFrom();
        Point to = move.getTo();

        int deltaX = to.x - from.x;
        int deltaY = to.y - from.y;

        ChessPiece chessPiece = (ChessPiece) state.getBoard().getFieldAt(from);
        ChessPieceColor pieceColor = chessPiece.getColor();

        boolean isValid;
        if (ChessPieceColor.BLACK == pieceColor) {
            if (!chessPiece.hasMoved())
                isValid = deltaX == 0 && (deltaY == 1 || deltaY == 2);
            else
                isValid = deltaX == 0 && deltaY == 1;
        } else {
            if (!chessPiece.hasMoved())
                isValid = deltaX == 0 && (deltaY == -1 || deltaY == -2);
            else
                isValid = deltaX == 0 && deltaY == -1;
        }

        if (isValid) {
            if (Math.abs(deltaY) > 1)
                chessPiece.hadPreviousDoublePush();
            return new ValidationResult(true, "");
        }

        return new ValidationResult(false, "The pawn can only move forward to the unoccupied square immediately in front of it on the same file, or on its first move it can advance two squares along the same file.");
    }
}
