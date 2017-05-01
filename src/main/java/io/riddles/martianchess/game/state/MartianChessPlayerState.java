package io.riddles.martianchess.game.state;

import io.riddles.martianchess.move.MartianChessMove;
import io.riddles.javainterface.game.state.AbstractPlayerState;

/**
 * Created by joost on 4/18/17.
 */
public class MartianChessPlayerState extends AbstractPlayerState<MartianChessMove> {

    int score = 0;

    public MartianChessPlayerState(int playerId) {
        super(playerId);

    }

    public MartianChessPlayerState clone() {
        MartianChessPlayerState psClone = new MartianChessPlayerState(this.playerId);
        psClone.setScore(score);
        return psClone;
    }

    public void addPoints(int points) {
        this.score += points;
    }

    public int getScore() { return this.score; }
    public void setScore(int score) { this.score = score; }

}
