package io.riddles.martianchess.game.processor;

import io.riddles.martianchess.data.MartianChessBoard;
import io.riddles.martianchess.data.MartianChessPiece;
import io.riddles.martianchess.game.state.MartianChessPlayerState;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.model.ChessPieceColor;
import io.riddles.martianchess.model.ValidationResult;
import io.riddles.martianchess.move.MartianChessMove;
import io.riddles.martianchess.validator.MartianChessMoveValidator;

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
public class MartianChessLogic {

    /**
     * Takes a MartianChessState and applies the move.
     * Returns nothing, but transforms the given MartianChessState.
     */
    public static void executeMove(MartianChessState state, MartianChessPlayerState playerState) {

        MartianChessBoard board = state.getBoard();

        MartianChessMove move = playerState.getMove();
        MartianChessMoveValidator validator = new MartianChessMoveValidator();
        ValidationResult vr = validator.validate(move, state);
        if (vr.isValid()) {

            if (board.getFieldAt(move.getTo()) != null) {
                int capturePoints = 0;
                MartianChessPiece capturePiece = board.getFieldAt(move.getTo());
                switch (capturePiece.getType()) {
                    case PAWN:
                        capturePoints = 1;
                        break;
                    case DRONE:
                        capturePoints = 2;
                        break;
                    case QUEEN:
                        capturePoints = 3;
                        break;
                }
                playerState.addPoints(capturePoints);
                System.out.println("Added score: " + capturePoints + " Total: " + playerState.getScore());

            }
            board.move(move.getFrom(), move.getTo());
            if (move.getTo().getY() < state.getBoard().getHeight()/2) {
                board.getFieldAt(move.getTo()).setColor(ChessPieceColor.BLACK); /* Piece becomes black */
            } else {
                board.getFieldAt(move.getTo()).setColor(ChessPieceColor.WHITE); /* Piece becomes white */
            }

        }
    }

    public ChessPieceColor getPlayerColor(int playerId) {
        return (playerId == 0) ? ChessPieceColor.WHITE : ChessPieceColor.BLACK;
    }
}
