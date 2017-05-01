package io.riddles.martianchess.engine;

import io.riddles.martianchess.data.ChessBoard;
import io.riddles.martianchess.game.ChessSerializer;
import io.riddles.martianchess.game.player.ChessPlayer;
import io.riddles.martianchess.game.processor.ChessLogic;
import io.riddles.martianchess.game.processor.ChessProcessor;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.martianchess.game.state.ChessPlayerState;
import io.riddles.javainterface.configuration.Configuration;
import io.riddles.javainterface.engine.AbstractEngine;
import io.riddles.javainterface.engine.GameLoopInterface;
import io.riddles.javainterface.engine.TurnBasedGameLoop;
import io.riddles.javainterface.exception.TerminalException;
import io.riddles.javainterface.game.player.PlayerProvider;
import io.riddles.javainterface.io.IOHandler;

import java.util.ArrayList;

/**
 * This class is the connecting instance between the Chess game and the
 * encapsulating framework. It should implement all methods required for
 * the Riddles.io framework to retrieve the necessary game data.
 *
 * Copyright 2016 - present Riddles.io
 * For license information see the LICENSE file in the project root
 *
 * @author Niko van Meurs <niko@riddles.io>
 */

public class MartianChessEngine extends AbstractEngine<ChessProcessor, ChessPlayer, MartianChessState> {

    public MartianChessEngine(PlayerProvider<ChessPlayer> playerProvider, IOHandler ioHandler) throws TerminalException {
        super(playerProvider, ioHandler);
    }

    /* createPlayer creates and initialises a Player for the game.
 * returns: a Player
 */
    @Override
    protected ChessPlayer createPlayer(int id) {
        ChessPlayer p = new ChessPlayer(id);
        return p;
    }

    @Override
    protected Configuration getDefaultConfiguration() {
        Configuration configuration = new Configuration();
        configuration.put("maxRounds", 200); /* Note: in the previous version of Block Battle, maxRounds was set to -1 */
        configuration.put("field_height", 8); /* Note: in the previous version of Block Battle, maxRounds was set to -1 */
        configuration.put("field_width", 8); /* Note: in the previous version of Block Battle, maxRounds was set to -1 */
        return configuration;
    }


    /* createProcessor creates and initialises a Processor for the game.
     * returns: a Processor
     */
    @Override
    protected ChessProcessor createProcessor() {

        return new ChessProcessor(playerProvider);
    }

    @Override
    protected GameLoopInterface createGameLoop() {

        return new TurnBasedGameLoop<MartianChessState>();
    }

    @Override
    protected void sendSettingsToPlayer(ChessPlayer player) {

        String playerNames = "";
        for(ChessPlayer p : playerProvider.getPlayers()) {
            playerNames += p.getName() + ",";
        }
        playerNames = playerNames.substring(0, playerNames.length()-1);

        //player.sendSetting("field_height", configuration.getInt("fieldHeight"));
        //player.sendSetting("field_width", configuration.getInt("fieldWidth"));
        player.sendSetting("max_rounds", configuration.getInt("maxRounds"));
        player.sendSetting("player_names", playerNames);
        player.sendSetting("your_bot", player.getName());
        player.sendSetting("your_color", new ChessLogic().getPlayerColor(player.getId()).toString().toLowerCase());
    }


    /* getPlayedGame creates a serializer and serialises the game
     * returns: String with the serialised game.
     */
    @Override
    protected String getPlayedGame(MartianChessState state) {
        ChessSerializer serializer = new ChessSerializer();
        return serializer.traverseToString(this.processor, state);
    }

    /* getInitialState creates an initial state to start the game with.
     * returns: MartianChessState
     */
    @Override
    protected MartianChessState getInitialState() {
        ArrayList<ChessPlayerState> playerStates = new ArrayList<>();
        ChessBoard board = new ChessBoard(configuration.getInt("fieldWidth"), (configuration.getInt("fieldHeight")));
        board.setFieldsFromString(
                "R,N,B,Q,K,B,N,R," +
                "P,P,P,P,P,P,P,P," +
                ".,.,.,.,.,.,.,.," +
                ".,.,.,.,.,.,.,.," +
                ".,.,.,.,.,.,.,.," +
                ".,.,.,.,.,.,.,.," +
                "p,p,p,p,p,p,p,p," +
                "r,n,b,q,k,b,n,r" );

        for (ChessPlayer player : playerProvider.getPlayers()) {
            ChessPlayerState playerState = new ChessPlayerState(player.getId());
            playerStates.add(playerState);
        }

        MartianChessState state = new MartianChessState(null, playerStates, 0);
        state.setBoard(board);

        return state;
    }
}
