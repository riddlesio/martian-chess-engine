package io.riddles.martianchess.validator;

import io.riddles.martianchess.data.MartianChessPiece;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.model.ChessPieceColor;
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
public class ChessPieceColorValidator extends ChessPieceMoveValidator implements MoveValidator<MartianChessState> {

    @Override
    public Boolean isApplicable(MartianChessMove move, MartianChessState state) {

        return true;
    }

    @Override
    public ValidationResult validate(MartianChessMove move, MartianChessState state) {

        MartianChessPiece piece = state.getBoard().getFieldAt(move.getFrom());
        if (piece.getColor() == getPlayerColor(state.getPlayerId())) {
            return new ValidationResult(true, "");
        }

        return new ValidationResult(false, "This piece doesn't belong to you");
    }


    private ChessPieceColor getPlayerColor(int playerId) {
        return (playerId == 0) ? ChessPieceColor.WHITE : ChessPieceColor.BLACK;
    }
}