package io.riddles.martianchess.validator;

import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.model.ValidationResult;
import io.riddles.martianchess.data.ChessBoard;
import io.riddles.martianchess.data.ChessPiece;
import io.riddles.martianchess.move.ChessMove;

import java.awt.*;
import java.util.ArrayList;

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
public final class ChessMoveValidator implements MoveValidator<MartianChessState> {

    private ArrayList<MoveValidator> validators;

    public ChessMoveValidator() {

        ArrayList<MoveValidator> validators = new ArrayList<>();

        validators.add(new FromNotEmptyValidator());
        validators.add(new ToEmptyOrOppositePlayerValidator());
        validators.add(new ChessPieceColorValidator());
        validators.add(new BishopMoveValidator());
        validators.add(new KingMoveValidator());
        validators.add(new KnightMoveValidator());
        validators.add(new PawnMoveValidator());
        validators.add(new QueenMoveValidator());
        validators.add(new RookMoveValidator());

        this.validators = validators;
    }

    @Override
    public Boolean isApplicable(ChessMove move, MartianChessState state) {

        return true;
    }

    @Override
    public ValidationResult validate(ChessMove move, MartianChessState state) {
        ChessBoard board = state.getBoard();

        //get the piece at the from location
        Point from = move.getFrom();
        ChessPiece fromPiece = board.getFieldAt(from);

        boolean result = true;
        for (MoveValidator validator : validators) {
            if (validator.isApplicable(move, state)) {
                ValidationResult vr = validator.validate(move, state);
                if (!vr.isValid()) {
                    return vr;
                }
            }
        }


        /*
        if (validator != null) {
            ValidationResult result = validator.validate(move, state);
            if (!result.isValid()) {
                return result;
            }
        }
        */
        return new ValidationResult(true, "Nice");
    }

}
