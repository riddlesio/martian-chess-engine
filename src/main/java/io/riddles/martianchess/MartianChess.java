package io.riddles.martianchess;

import io.riddles.martianchess.engine.MartianChessEngine;
import io.riddles.martianchess.game.state.MartianChessState;
import io.riddles.javainterface.game.player.PlayerProvider;
import io.riddles.javainterface.io.IOHandler;


public class MartianChess {

    public static void main(String[] args) throws Exception {

        MartianChessEngine engine;
        engine = new MartianChessEngine(new PlayerProvider<>(), new IOHandler());

        MartianChessState initialState = engine.willRun();
        MartianChessState finalState = engine.run(initialState);
        engine.didRun(initialState, finalState);
    }
}
