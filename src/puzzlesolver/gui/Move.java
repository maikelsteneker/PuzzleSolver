package puzzlesolver.gui;

import puzzlesolver.model.*;

/**
 * Stores two Positions the user has selected for moving a piece
 *
 * @author Maikel Steneker
 * @since 2-6-2011
 */
public class Move {
    //Internal representation:

    private Puzzle puzzle;
    private Position inBox;
    private Position inBag;
    private Direction direction = null;

    /**
     * Constructs the Move class
     *
     * @param puzzle  the puzzle {@code this.inBox} and {@code this.inBag}
     *                                                              refer to
     * @modifies this.puzzle
     * @post this.puzzle == puzzle
     */
    public Move(Puzzle puzzle) {
        this.inBox = null;
        this.inBag = null;
        this.puzzle = puzzle;
    }

    /**
     * Get the value of inBox
     *
     * @return the value of inBox
     */
    public Position getInBox() {
        return inBox;
    }

    /**
     * Set the value of inBox
     *
     * @pre inBox != null
     * @param inBox new value of inBox
     * @modifies this.inBox, this.direction
     * @post this.inBox == inbox
     * @post (direction == null) ==> (this.direction == Direction.remove)
     * @post (direction == Direction.place) ==> (this.inBox ==
     *         correctPosition(inBox,this.inBag,findAnchorPosition(this.inBag)))
     */
    public void setInBox(Position inBox) {
        this.inBox = inBox;
        if (direction == null) {
            this.direction = Direction.remove;
        } else if (direction == Direction.place) {
            this.inBox = correctPosition(inBox, this.inBag,
                    findAnchorPosition(this.inBag));
        }
    }

    /**
     * Get the value of inBag
     *
     * @return the value of inBag
     */
    public Position getInBag() {
        return inBag;
    }

    /**
     * Set the value of inBag
     *
     * @pre inBag != null
     * @param inBag  new value of inBag
     * @modifies this.inBag, this.direction
     * @post (inBag points at a piece) ==>
     *                      ((this.direction == null) ==> (this.inBag == null))
     *       !(inBag points at a piece) ==>
     *                      ((this.inBag == inBag) && ((direction == null) ==>
     *                      (this.direction == Direction.place)))
     */
    public void setInBag(Position inBag) {
        if (Image.generateVirtualBox(this.puzzle.getBagOfPieces()).
                getCell(inBag).isEmpty()) {
            //the user didn't click on a piece
            if (this.direction == null) {
                this.inBag = null; //reset this position
            }
            return; //and return
        }

        this.inBag = inBag;

        if (direction == null) {
            this.direction = Direction.place;
        }
    }

    /**
     * Returns the direction of {@code this}
     *
     * @return {@code direction}
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the anchor position of the placement at {@code inBag}
     *
     * @param inBag  the position to look for
     * @return the anchor position
     */
    private Position findAnchorPosition(Position inBag) {
        /**
         * Look for the cell in the bag that has:
         * - The same placement
         * - The lowest row or col number
         */
        int row = Integer.MAX_VALUE;
        int col = Integer.MAX_VALUE;

        Box vbag = Image.generateVirtualBox(this.puzzle.getBagOfPieces());
        Placement placement = vbag.getCell(inBag).getPlacement();

        for (int i = 0; i < vbag.getRowCount(); i++) {
            for (int j = 0; j < vbag.getColCount(); j++) {
                if (vbag.getCell(i, j).getState() == CellState.occupied
                        && placement.equals(vbag.getCell(i, j).getPlacement())) {
                    if (i < row) {
                        row = i;
                    }
                    if (j < col) {
                        col = j;
                    }
                }
            }
        }
        return new Position(row, col);
    }

    /**
     * Returns the correct position
     *
     * @param inBox  position in the box
     * @param inBag  position in the bag
     * @param anchorPosition  anchor position
     * @return correct position
     */
    public static Position correctPosition(Position inBox, Position inBag,
            Position anchorPosition) {
        //determine the offset of the bag position
        int rowOffset = inBag.getRow() - anchorPosition.getRow();
        int colOffset = inBag.getCol() - anchorPosition.getCol();

        //determine the correct inBox position
        int row = inBox.getRow() - rowOffset;
        int col = inBox.getCol() - colOffset;

        //return the new position
        return new Position(row, col);
    }

    /**
     * Returns the correct position
     *
     * @param pos  position to correct
     * @return correct position
     */
    public Position correctPosition(Position pos) {
        return correctPosition(pos, this.getInBag(),
                this.findAnchorPosition(inBag));
    }
}
