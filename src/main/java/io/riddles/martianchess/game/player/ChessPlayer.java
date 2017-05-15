package io.riddles.martianchess.game.player;

import io.riddles.javainterface.game.player.AbstractPlayer;

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
public class ChessPlayer extends AbstractPlayer {




    public ChessPlayer(int id) {
        super(id);
    }

    public String toString() {
        return "ChessPlayer " + this.getId();
    }


}
