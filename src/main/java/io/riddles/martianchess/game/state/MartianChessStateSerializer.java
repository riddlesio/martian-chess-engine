
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

package io.riddles.martianchess.game.state;

import io.riddles.martianchess.move.MartianChessMove;
import io.riddles.javainterface.game.state.AbstractStateSerializer;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * io.riddles.go.game.state.GoStateSerializer - Created on 6/27/16
 *
 * [description]
 *
 * @author Joost - joost@riddles.io, Jim van Eeden - jim@riddles.io
 */
public class MartianChessStateSerializer extends AbstractStateSerializer<MartianChessState> {

    @Override
    public String traverseToString(MartianChessState state) {
        return visitState(state).toString();
    }

    @Override
    public JSONObject traverseToJson(MartianChessState state) throws NullPointerException {
        return visitState(state);
    }

    private JSONObject visitState(MartianChessState state) throws NullPointerException {
        JSONObject stateJSON = new JSONObject();

        MartianChessPlayerState playerState = state.getPlayerStateById(state.getPlayerId());
        stateJSON.put("field", state.getBoard().toString());
        stateJSON.put("player", state.getPlayerId());

        JSONArray playersJSON = new JSONArray();

        for (MartianChessPlayerState ps : state.getPlayerStates()) {
            JSONObject playerJSON = new JSONObject();
            playerJSON.put("id", ps.getPlayerId());
            playerJSON.put("score", ps.getScore());
            playersJSON.put(playerJSON);
        }


        stateJSON.put("round", state.getRoundNumber());
        stateJSON.put("players", playersJSON);

        MartianChessMove move = playerState.getMove();
        String exceptionString = "";
        if (move != null && move.getException() != null) {
            exceptionString = move.getException().getMessage();
        }
        stateJSON.put("illegalMove", exceptionString);

        return stateJSON;
    }
}