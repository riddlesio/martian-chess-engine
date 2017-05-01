package io.riddles.martianchess.game.processor;

import java.util.ArrayList;

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
 * [description]
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
        playerState.setMove(move);


        // parse the response
        if (move.getException() != null) {
            System.out.println("EXCEPTION '" + input.getValue() + "' " + move.getException().toString());
        }

        MartianChessLogic logic = new MartianChessLogic();
        logic.executeMove(nextState, playerState);

        nextState.getBoard().dump();

        nextState.setPlayerstates((ArrayList)nextPlayerStates);
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
        return returnVal;
    }

    /* Returns winner playerId, or null if there's no winner. */
    @Override
    public Integer getWinnerId(MartianChessState state) {
        return null;
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
