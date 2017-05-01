package io.riddles.martianchess.game.processor;

import io.riddles.martianchess.data.MartianChessBoard;
import io.riddles.martianchess.game.state.MartianChessPlayerState;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.model.ChessPieceColor;
import io.riddles.martianchess.model.ValidationResult;
import io.riddles.martianchess.move.MartianChessMove;
import io.riddles.martianchess.validator.ChessMoveValidator;

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
        ChessMoveValidator validator = new ChessMoveValidator();
        ValidationResult vr = validator.validate(move, state);
        if (vr.isValid()) {
            if (board.getFieldAt(move.getTo()) != null) {
                /* Capture piece */
            }
            board.move(move.getFrom(), move.getTo());
        }
        System.out.println("Move " + board.getFieldAt(move.getFrom()) + " to " + move.getTo().getX() + "," + move.getTo().getY() + ": " + vr.isValid() + " "+ vr.getReason());


    }

    public ChessPieceColor getPlayerColor(int playerId) {
        return (playerId == 0) ? ChessPieceColor.WHITE : ChessPieceColor.BLACK;
    }
}
