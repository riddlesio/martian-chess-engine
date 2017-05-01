package io.riddles.martianchess.game.processor;

import io.riddles.martianchess.data.MartianChessBoard;
import io.riddles.martianchess.data.MartianChessPiece;
import io.riddles.martianchess.game.state.MartianChessPlayerState;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.model.ChessPieceColor;
import io.riddles.martianchess.model.ValidationResult;
import io.riddles.martianchess.move.MartianChessMove;
import io.riddles.martianchess.validator.MartianChessMoveValidator;

import java.awt.*;

/**
 * Created by joost on 4/21/17.
 */
public class MartianChessLogic {
    /**
     * Takes a BlockBattleState and applies the move(s).
     * Returns nothing, but transforms the given BlockBattleState.
     */
    public static void executeMove(MartianChessState state, MartianChessPlayerState playerState) {
        MartianChessBoard board = state.getBoard();
        int playerId = playerState.getPlayerId();

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

            }
            board.move(move.getFrom(), move.getTo());
            if (move.getTo().getY() < state.getBoard().getHeight()/2) {
                board.getFieldAt(move.getTo()).setColor(ChessPieceColor.BLACK); /* Piece becomes black */
            } else {
                board.getFieldAt(move.getTo()).setColor(ChessPieceColor.WHITE); /* Piece becomes white */
            }

        }
        System.out.println("Move " + board.getFieldAt(move.getFrom()) + " to " + move.getTo().getX() + "," + move.getTo().getY() + ": " + vr.isValid() + " "+ vr.getReason());


    }

    public ChessPieceColor getPlayerColor(int playerId) {
        return (playerId == 0) ? ChessPieceColor.WHITE : ChessPieceColor.BLACK;
    }
}
