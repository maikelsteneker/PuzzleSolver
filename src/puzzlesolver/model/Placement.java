package puzzlesolver.model;

/**
 * Stores the placement of a piece (in the cells of the box)
 *
 * @author Maikel Steneker (0753708)
 * @since 21-3-2011
 *
 * Invariants:
 * anchorPos != null
 * piece != null
 */
public class Placement {
    //Internal representation:
    private Position anchorPos; //anchor position
    private Piece piece; //the piece

    /**
     * Constructs Placement class
     * 
     * @pre      {@code true}
     * @param    anchorPos  anchor position
     * @param    piece  piece
     * @modifies anchorPos
     * @modifies piece
     * @post     {@code this.anchorPos = anchorPos}
     * @post     {@code this.piece = piece}
     * @throws   NullPointerException  If piece == null || anchorPos == null
     */
    public Placement(Position anchorPos, Piece piece)
            throws NullPointerException {
        //check precondition
        if (piece == null) {
            throw new NullPointerException("While constructing Placement: "
                    + "piece == null");
        }
        if (anchorPos == null) {
            throw new NullPointerException("While constructing Placement: "
                    + "anchorPos == null");
        }

        this.anchorPos = anchorPos;
        this.piece = piece;
    }

    /**
     * Return the anchor position
     *
     * @pre    {@code true}
     * @return {@code anchorPos}
     */
    public Position getAnchorPos() {
        return anchorPos;
    }

    /**
     * Set the value of anchorPos
     *
     * @pre      {@code true}
     * @param    anchorPos  new value of anchorPos
     * @modifies anchorPos
     * @post     {@code this.anchorPos = anchorPos}
     * @throws   NullPointerException  If anchorPos == null
     */
    public void setAnchorPos(Position anchorPos) throws NullPointerException {
        if (anchorPos == null) {
            throw new NullPointerException("While executing setAnchorPos("
                    + anchorPos.toString() + "): anchorPos == null");
        }
        this.anchorPos = anchorPos;
    }

    /**
     * Returns the piece
     *
     * @pre    {@code true}
     * @return {@code piece}
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Set the value of piece
     *
     * @pre      {@code true}
     * @param    piece new value of piece
     * @modifies piece
     * @post     {@code this.piece = piece}
     * @throws   NullPointerException  If piece == null
     */
    public void setPiece(Piece piece) throws NullPointerException {
        if (piece == null) {
            throw new NullPointerException("While executing setPiece("
                    + piece.toString() + "): piece == null");
        }
        this.piece = piece;
    }
}
