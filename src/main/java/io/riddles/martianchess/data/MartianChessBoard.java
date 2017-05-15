package io.riddles.martianchess.data;

import io.riddles.martianchess.model.ChessPieceColor;
import io.riddles.javainterface.game.data.Board;
import io.riddles.martianchess.model.MartianChessPieceType;

import java.awt.*;


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
public class MartianChessBoard extends Board<MartianChessPiece>{
    public static final String EMPTY_FIELD = ".";
    public static final int HALF_TOPHALF = 1;
    public static final int HALF_BOTTOMHALF = 2;


    public MartianChessBoard(int width, int height) {
        super(width, height);

        fields = new MartianChessPiece[width][height];
        clear();
    }

    public MartianChessBoard(int width, int height, String initialBoard) {
        super(width, height);

        fields = new MartianChessPiece[width][height];
        clear();
        setFieldsFromString(initialBoard);
    }

    public MartianChessBoard(MartianChessBoard board) {
        super(board.getWidth(), board.getHeight());
        fields = new MartianChessPiece[width][height];
        clear();
        setFieldsFromString(board.toString());
    }


    public void clear() {
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                fields[x][y] = null;
            }
        }
    }

    public void setFieldsFromString(String input) {
        String[] split = input.split(",");
        int x = 0;
        int y = 0;
        this.fields = new MartianChessPiece[width][height];
        for(int c = 0; c < split.length; c++) {
            String fieldString = split[c];
            this.fields[x][y] = this.fieldFromString(fieldString);
            x++;

            if(x == this.width) {
                x = 0;
                y++;
            }
        }
    }

    /* Return a Piece, or null if unable to parse or empty. */
    public MartianChessPiece fieldFromString(String input) {
        switch (input) {
            case "Q":
                return new MartianChessPiece(MartianChessPieceType.QUEEN, ChessPieceColor.BLACK);
            case "D":
                return new MartianChessPiece(MartianChessPieceType.DRONE, ChessPieceColor.BLACK);
            case "P":
                return new MartianChessPiece(MartianChessPieceType.PAWN, ChessPieceColor.BLACK);
            case "q":
                return new MartianChessPiece(MartianChessPieceType.QUEEN, ChessPieceColor.WHITE);
            case "d":
                return new MartianChessPiece(MartianChessPieceType.DRONE, ChessPieceColor.WHITE);
            case "p":
                return new MartianChessPiece(MartianChessPieceType.PAWN, ChessPieceColor.WHITE);
            case ".":
                return null;
        }
        return null;
    }

    private String getStringFromField(MartianChessPiece p) {
        if (p != null) {
            if (p.getColor() == ChessPieceColor.BLACK) {
                switch (p.getType()) {
                    case QUEEN:
                        return "Q";
                    case DRONE:
                        return "D";
                    case PAWN:
                        return "P";
                }
            } else {
                switch (p.getType()) {
                    case QUEEN:
                        return "q";
                    case DRONE:
                        return "d";
                    case PAWN:
                        return "p";
                }
            }
        }
        return ".";
    }

    public String toString() {
        String s = "";
        int counter = 0;
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                if (counter > 0)
                    s += ",";
                s += getStringFromField(this.fields[x][y]);
                counter++;
            }
        }
        return s;
    }

    public void move(Point from, Point to) {
        fields[to.x][to.y] = fields[from.x][from.y];
        fields[from.x][from.y] = null;
    }

    @Override /* TODO: Logic is cumbersome */
    public MartianChessPiece getFieldAt(Point point) {
        if (point.x >= 0 && point.x < this.width && point.y >= 0 && point.y < this.height) {
            if (fields[point.x][point.y] != null) {
                return fields[point.x][point.y];
            }
        }
        return null;
    }

    @Override
    public void dump() {
        for(int y = 0; y < this.height; y++) {
            for(int x = 0; x < this.width; x++) {
                System.out.print(getStringFromField(fields[x][y]));
            }
            System.out.print("\n");
        }
    }

    public int getNrPiecesOnHalf(int half) {
        int count = 0;
        if (half == HALF_TOPHALF) {
            for(int y = 0; y < this.height / 2; y++) {
                for (int x = 0; x < this.width; x++) {
                    if (fields[x][y] != null) count++;
                }
            }
        } else {
            for(int y = this.height / 2; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    if (fields[x][y] != null) count++;
                }
            }
        }
        return count;
    }
}
