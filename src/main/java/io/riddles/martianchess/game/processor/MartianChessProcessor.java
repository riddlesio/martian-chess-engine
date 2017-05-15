package io.riddles.martianchess.game.processor;

import java.util.ArrayList;

import io.riddles.martianchess.data.MartianChessBoard;
import io.riddles.martianchess.game.player.ChessPlayer;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.move.ActionType;
import io.riddles.martianchess.move.MartianChessMove;
import io.riddles.martianchess.move.ChessMoveDeserializer;
import io.riddles.martianchess.game.state.MartianChessPlayerState;
import io.riddles.javainterface.engine.AbstractEngine;
import io.riddles.javainterface.game.player.PlayerProvider;
import io.riddles.javainterface.game.processor.PlayerResponseProcessor;
import io.riddles.javainterface.game.state.AbstractPlayerState;
import io.riddles.javainterface.io.PlayerResponse;

/**
 * io.riddles.MartianChess.game.processor.MartianChessProcessor - Created on 6/27/16
 *
 * This file is a part of martianchess
 *
 * Copyright 2016 - present Riddles.io
 * For license information see the LICENSE file in the project root
 *
 * @author Joost - joost@riddles.io, Jim van Eeden - jim@riddles.io
 */
public class MartianChessProcessor extends PlayerResponseProcessor<MartianChessState, ChessPlayer> {

    public MartianChessProcessor(PlayerProvider<ChessPlayer> playerProvider) {
        super(playerProvider);
    }

    @Override
    public MartianChessState createNextStateFromResponse(MartianChessState state, PlayerResponse input, int roundNumber) {

        /* Clone playerStates for next State */
        ArrayList<MartianChessPlayerState> nextPlayerStates = clonePlayerStates(state.getPlayerStates());
        MartianChessState nextState = new MartianChessState(state, nextPlayerStates, roundNumber);
        nextState.setPlayerId(input.getPlayerId());

        ChessMoveDeserializer moveDeserializer = new ChessMoveDeserializer();
        MartianChessMove move = moveDeserializer.traverse(input.getValue());
        MartianChessPlayerState playerState = getActivePlayerState(nextPlayerStates, input.getPlayerId());
        if (playerState != null) {
            playerState.setMove(move);
        }

        // parse the response
        if (move.getException() != null) {
            System.out.println("EXCEPTION '" + input.getValue() + "' " + move.getException().toString());
        }

        MartianChessLogic logic = new MartianChessLogic();
        MartianChessLogic.executeMove(nextState, playerState);

        //nextState.getBoard().dump();

        nextState.setPlayerstates(nextPlayerStates);
        return nextState;
    }

    private MartianChessPlayerState getActivePlayerState(ArrayList<MartianChessPlayerState> playerStates, int id) {
        for (MartianChessPlayerState playerState : playerStates) {
            if (playerState.getPlayerId() == id) { return playerState; }
        }
        return null;
    }

    private ArrayList<MartianChessPlayerState> clonePlayerStates(ArrayList<MartianChessPlayerState> playerStates) {
        ArrayList<MartianChessPlayerState> nextPlayerStates = new ArrayList<>();
        for (MartianChessPlayerState playerState : playerStates) {
            MartianChessPlayerState nextPlayerState = playerState.clone();
            nextPlayerStates.add(nextPlayerState);
        }
        return nextPlayerStates;
    }


    @Override
    public void sendUpdates(MartianChessState state, ChessPlayer player) {
        player.sendUpdate("round", state.getRoundNumber());
        player.sendUpdate("field", state.getBoard().toString());
    }


    @Override
    public boolean hasGameEnded(MartianChessState state) {
        boolean returnVal = false;
        if (state.getRoundNumber() > AbstractEngine.configuration.getInt("maxRounds")) returnVal = true;

        if (state.getPlayerStates().size() == 2) {
            int boardTopHalfPieces = state.getBoard().getNrPiecesOnHalf(MartianChessBoard.HALF_TOPHALF);
            int boardBottomHalfPieces = state.getBoard().getNrPiecesOnHalf(MartianChessBoard.HALF_BOTTOMHALF);

            if (boardTopHalfPieces == 0 || boardBottomHalfPieces == 0) {
                returnVal = true;
            }
        } else {
            return true;
        }
        return returnVal;
    }

    /* Returns winner playerId, or null if there's no winner. */
    @Override
    public Integer getWinnerId(MartianChessState state) {
        ArrayList<MartianChessPlayerState> playerStates = clonePlayerStates(state.getPlayerStates());
        ArrayList<MartianChessPlayerState> winners = new ArrayList<>();
        int maxScore = 0;
        for(MartianChessPlayerState ps : playerStates) {
            if (ps.getScore() > maxScore) {
                maxScore = ps.getScore();
                winners.clear();
                winners.add(ps);
            } else if (ps.getScore() == maxScore) {
                winners.add(ps);
            }
        }

        if (winners.size() == 1) {
            return winners.get(0).getPlayerId();
        } else { /* Everyone has the same score */
            return null;
        }
    }

    @Override
    public double getScore(MartianChessState state) {
        return state.getRoundNumber();
    }

    @Override
    public Enum getActionType(MartianChessState ChessState, AbstractPlayerState playerState) {
        return ActionType.MOVE;
    }


}
