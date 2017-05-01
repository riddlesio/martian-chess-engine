package io.riddles.martianchess.game.state;

import io.riddles.martianchess.move.ChessMove;
import io.riddles.javainterface.game.state.AbstractPlayerState;

/**
 * Created by joost on 4/18/17.
 */
public class ChessPlayerState extends AbstractPlayerState<ChessMove> {

    public ChessPlayerState(int playerId) {
        super(playerId);

    }

    public ChessPlayerState clone() {
        ChessPlayerState psClone = new ChessPlayerState(this.playerId);
        return psClone;
    }

}
