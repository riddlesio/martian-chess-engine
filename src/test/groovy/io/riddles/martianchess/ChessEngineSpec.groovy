/*
 * Copyright 2016 riddles.io (developers@riddles.io)
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *     For the full copyright and license information, please view the LICENSE
 *     file that was distributed with this source code.
 */

/**
 * Created by joost on 4/21/17.
 */

package io.riddles.martianchess

import io.riddles.martianchess.engine.MartianChessEngine
import io.riddles.martianchess.game.player.ChessPlayer
import io.riddles.martianchess.game.processor.ChessProcessor
import io.riddles.javainterface.game.player.PlayerProvider
import io.riddles.javainterface.game.state.AbstractState
import io.riddles.javainterface.io.FileIOHandler
import io.riddles.martianchess.game.state.MartianChessState
import spock.lang.Specification


class ChessEngineSpec extends Specification {

    public static class TestEngine extends MartianChessEngine {

        TestEngine(PlayerProvider<MartianChessEngine> playerProvider, String wrapperInput) {
            super(playerProvider, null);
            this.ioHandler = new FileIOHandler(wrapperInput);
        }
    }



    //@Ignore
    def "test if ChessEngine is created"() {

        setup:
        String[] botInputs = new String[2]

        def wrapperInput = "./src/test/resources/wrapper_input.txt"
        botInputs[0] = "./src/test/resources/bot1_input.txt"
        botInputs[1] = "./src/test/resources/bot2_input.txt"

        PlayerProvider<ChessPlayer> playerProvider = new PlayerProvider<>();
        ChessPlayer player1 = new ChessPlayer(0);
        player1.setIoHandler(new FileIOHandler(botInputs[0])); playerProvider.add(player1);
        ChessPlayer player2 = new ChessPlayer(1);
        player2.setIoHandler(new FileIOHandler(botInputs[1])); playerProvider.add(player2);

        def engine = new TestEngine(playerProvider, wrapperInput)

        AbstractState initialState = engine.willRun()
        AbstractState finalState = engine.run(initialState);
        engine.didRun(initialState, finalState);
        ChessProcessor processor = engine.getProcessor();

        expect:
        finalState instanceof MartianChessState;
        finalState.getBoard().toString() == ".,0,.,.,.,.,.,.,1,.,.,.,.,.,.,0,1,.,.,.,.,.,1,0,1,.,.,.,1,1,0,0,.,.,.,0,1,0,0,0,.,.";
        processor.getWinnerId(finalState) == 0;
    }
}