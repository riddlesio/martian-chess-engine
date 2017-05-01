package io.riddles.martianchess.validator;

import io.riddles.martianchess.data.ChessBoard;
import io.riddles.martianchess.data.ChessPiece;
import io.riddles.martianchess.model.ChessPieceType;
import io.riddles.martianchess.move.ChessMove;

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
public abstract class ChessPieceMoveValidator {


    protected Boolean isMovedPieceOfType(ChessMove move, ChessBoard board, ChessPieceType pieceType) {
        ChessPiece piece = board.getFieldAt(move.getFrom());
        return piece.hasType(pieceType);
    }
}
