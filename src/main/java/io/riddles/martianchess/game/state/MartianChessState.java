package io.riddles.martianchess.game.state;

import io.riddles.martianchess.data.MartianChessBoard;
import io.riddles.javainterface.game.player.PlayerBound;
import io.riddles.javainterface.game.state.AbstractState;

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
public final class MartianChessState extends AbstractState<MartianChessPlayerState> implements PlayerBound {

    private MartianChessBoard board;
    private int playerId;

    public MartianChessState(MartianChessState previousState, ArrayList<MartianChessPlayerState> playerStates, int roundNumber) {
        super(previousState, playerStates, roundNumber);
        if (previousState != null && previousState.getBoard() != null) {
            this.board = new MartianChessBoard(previousState.getBoard());
        }
    }

    public void setBoard(MartianChessBoard board) { this.board = board; }
    public MartianChessBoard getBoard() { return board; }

    @Override
    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int id) { this.playerId = id; }
}
