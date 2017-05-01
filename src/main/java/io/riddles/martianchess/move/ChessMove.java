package io.riddles.martianchess.move;

import io.riddles.martianchess.model.ChessPieceType;
import io.riddles.javainterface.game.move.AbstractMove;

import java.awt.*;

/**
 * @author Niko van Meurs <niko@riddles.io>
 */
public final class ChessMove extends AbstractMove {

	
	public enum MoveTypes{
		Castling, Promotion, Regular
	}
	
	
	public MoveTypes moveType;
	
    /**
     * boolean which indicates if the castling is on the king side
     */	
	private boolean kingCastle;
	
    /**
     * PieceType which the pawn will promote into
     */	
	private ChessPieceType promotionPiece;
	
			
    /**
     * Coordinate of the Field from which should be moved
     */
    private Point from;

    /**
     * Coordinate of the Field to which a Piece should be moved
     */
    private Point to;

 
    
    /**
     * Returns if the castling is a king castle
     * @return boolean
     */
    public ChessPieceType getPromotionType() {
        return promotionPiece;
    } 
        
    
    /**
     * Returns if the castling is a king castle
     * @return boolean
     */
    public boolean isKingCastle() {
        return kingCastle;
    } 
    
    
    /**
     *
     * @param kingCastle indicates if the castling is on the king side
     */
    public ChessMove(boolean kingCastle) {

        this.kingCastle = kingCastle;
        this.moveType = MoveTypes.Castling;
    }    
    
    /**
     *
     * @param from Points to the field from which the move is executed
     * @param to   Points to the field to which the move is executed
     */
    public ChessMove(Point from, Point to, ChessPieceType promotionPiece) {

        this.from = from;
        this.to = to;
        this.promotionPiece = promotionPiece;
        this.moveType = MoveTypes.Promotion;
    }

    
    
    /**
     *
     * @param from Points to the field from which the move is executed
     * @param to   Points to the field to which the move is executed
     */
    public ChessMove(Point from, Point to) {

        this.from = from;
        this.to = to;
        this.moveType = MoveTypes.Regular;
    }

    public ChessMove(Exception exception) {
        super(exception);
    }



    /**
     * Returns the coordinate for the move's source Field
     * @return Coordinate
     */
    public Point getFrom() {
        return from;
    }

    /**
     * Returns the coordinate for the move's target Field
     * @return Coordinate
     */
    public Point getTo() {
        return to;
    }
}
