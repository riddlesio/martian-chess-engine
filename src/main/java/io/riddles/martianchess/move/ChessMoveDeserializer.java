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

package io.riddles.martianchess.move;

import io.riddles.martianchess.game.move.MoveType;
import io.riddles.javainterface.exception.InvalidInputException;
import io.riddles.javainterface.serialize.Deserializer;

import java.awt.*;

/**
 * io.riddles.go.game.move.GoMoveDeserializer - Created on 6/27/16
 *
 * [description]
 *
 * @author Joost - joost@riddles.io, Jim van Eeden - jim@riddles.io
 */
public class ChessMoveDeserializer implements Deserializer<MartianChessMove> {


    public ChessMoveDeserializer() {
    }

    @Override
    public MartianChessMove traverse(String string) {

        try {
            return visitMove(string);
        } catch (InvalidInputException ex) {
            return new MartianChessMove(ex);
        } catch (Exception ex) {
            return new MartianChessMove(new InvalidInputException("Failed to parse move"));
        }
    }

    private MartianChessMove visitMove(String input) throws InvalidInputException {
        input = input.replace(';', ' ');
        String[] split = input.split(" ");

        if (split.length == 3) {
            MoveType type = visitAssessment(split[0]);
            if (type == MoveType.MOVE) {
                Point from = parseCoordinate(split[1]);
                Point to = parseCoordinate(split[2]);
                return new MartianChessMove(from, to);
            }
        }

        throw new InvalidInputException("Failed to parse move");
    }

    private MoveType visitAssessment(String input) throws InvalidInputException {
        switch (input) {
            case "move":
                return MoveType.MOVE;
            default:
                throw new InvalidInputException("Move isn't valid");
        }
    }

    private Point parseCoordinate(String s) { // Notation: 1,5
        String[] split = s.split(",");
        int column = Integer.parseInt(split[0]);
        int row = Integer.parseInt(split[1]);
        return new Point(column, row);
    }
}
