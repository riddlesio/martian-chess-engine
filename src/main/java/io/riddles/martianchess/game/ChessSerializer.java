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

package io.riddles.martianchess.game;

import io.riddles.martianchess.game.processor.MartianChessProcessor;
import io.riddles.martianchess.game.state.MartianChessStateSerializer;
import io.riddles.martianchess.game.state.MartianChessState;
import org.json.JSONArray;
import org.json.JSONObject;

import io.riddles.javainterface.game.AbstractGameSerializer;

/**
 * ConnectfourSerializer takes a ConnectfourState and serialises it and all previous states into a JSON String.
 * Customize this to add all game specific data to the output.
 *
 * @author Joost - joost@riddles.io, Jim van Eeden - jim@riddles.io
 */
public class ChessSerializer extends AbstractGameSerializer<MartianChessProcessor, MartianChessState> {

    public ChessSerializer() {
        super();
    }

    @Override
    public String traverseToString(MartianChessProcessor processor, MartianChessState initialState) {
        JSONObject game = new JSONObject();

        game = addDefaultJSON(initialState, game, processor);

        // put all states
        MartianChessStateSerializer serializer = new MartianChessStateSerializer();
        JSONArray states = new JSONArray();

        JSONObject initialJsonState = serializer.traverseToJson(initialState);
        initialJsonState.put("round", initialState.getRoundNumber() - 1);
        states.put(initialJsonState);

        MartianChessState state = initialState;
        int counter = 0;
        while (state.hasNextState()) {
            state = (MartianChessState) state.getNextState();
            JSONObject jsonState = serializer.traverseToJson(state);

            if (counter % 2 == 1) {
                jsonState.put("round", state.getRoundNumber() - 1);
            }
            counter++;

            states.put(jsonState);
        }

        game.put("states", states);

        return game.toString();
    }
}