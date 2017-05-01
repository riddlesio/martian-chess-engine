package io.riddles.martianchess.game.state;

import io.riddles.martianchess.move.MartianChessMove;
import io.riddles.javainterface.game.state.AbstractPlayerState;

/**
 * Created by joost on 4/18/17.
 */
public class MartianChessPlayerState extends AbstractPlayerState<MartianChessMove> {

    public MartianChessPlayerState(int playerId) {
        super(playerId);

    }

    public MartianChessPlayerState clone() {
        MartianChessPlayerState psClone = new MartianChessPlayerState(this.playerId);
        return psClone;
    }

}
