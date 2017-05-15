package io.riddles.martianchess.move;

import io.riddles.javainterface.game.move.AbstractMove;

import java.awt.*;

/**
 * @author Niko van Meurs <niko@riddles.io>
 */
public final class MartianChessMove extends AbstractMove {

	
	public enum MoveTypes{
		Regular
	}
	
	
	public MoveTypes moveType;
	
			
    /**
     * Coordinate of the Field from which should be moved
     */
    private Point from;

    /**
     * Coordinate of the Field to which a Piece should be moved
     */
    private Point to;

    
    /**
     *
     * @param from Points to the field from which the move is executed
     * @param to   Points to the field to which the move is executed
     */
    public MartianChessMove(Point from, Point to) {

        this.from = from;
        this.to = to;
        this.moveType = MoveTypes.Regular;
    }

    public MartianChessMove(Exception exception) {
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
