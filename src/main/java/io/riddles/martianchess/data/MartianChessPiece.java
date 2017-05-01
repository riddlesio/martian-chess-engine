package io.riddles.martianchess.data;

import io.riddles.martianchess.model.ChessPieceColor;
import io.riddles.martianchess.model.MartianChessPieceType;

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
public class MartianChessPiece {
    MartianChessPieceType type;
    ChessPieceColor color;
    private boolean hasMoved;

    public MartianChessPiece(MartianChessPieceType type, ChessPieceColor color) {
        this.type = type;
        this.color = color;
    }

    public MartianChessPiece(MartianChessPiece piece) {
        this.hasMoved = piece.hasMoved();
        this.color = piece.getColor();
        this.type = piece.getType();
    }

    public Boolean hasColor(ChessPieceColor color) {
        return this.color == color;
    }

    public Boolean hasType(MartianChessPieceType type) {
        return this.type == type;
    }

    public ChessPieceColor getColor() {
        return this.color;
    }

    public MartianChessPieceType getType() {
        return this.type;
    }

    public void setMoved() { this.hasMoved = true; }
    public boolean hasMoved() { return this.hasMoved; }

}
