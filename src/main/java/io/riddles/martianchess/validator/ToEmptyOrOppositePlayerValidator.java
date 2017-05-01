package io.riddles.martianchess.validator;

import io.riddles.martianchess.data.ChessBoard;
import io.riddles.martianchess.data.ChessPiece;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.model.ValidationResult;
import io.riddles.martianchess.move.ChessMove;

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
public class ToEmptyOrOppositePlayerValidator extends ChessPieceMoveValidator implements MoveValidator<MartianChessState> {

    @Override
    public Boolean isApplicable(ChessMove move, MartianChessState state) {
        return true;
    }

    @Override
    public ValidationResult validate(ChessMove move, MartianChessState state) {

        Point to = move.getTo();
        Point from = move.getFrom();

        ChessBoard board = state.getBoard();

        ChessPiece toPiece = board.getFieldAt(to);
        ChessPiece fromPiece = board.getFieldAt(from);

        boolean isValid = false;

        if (toPiece == null) {
            isValid = true;
        } else {
            if (toPiece.getColor() != fromPiece.getColor()) {
                isValid = true;
            }
        }


        if (isValid) {
            return new ValidationResult(true, "");
        }

        return new ValidationResult(false, "Your piece already occupies that field.");
    }
}
