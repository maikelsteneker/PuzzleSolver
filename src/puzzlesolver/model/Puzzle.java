package puzzlesolver.model;

/**
 * Represents a puzzle consisting of a Box and a BagOfPieces
 *
 * @author Maikel Steneker (0753708)
 * @since 10-5-2011
 */
public class Puzzle {
    //Internal representation:
    private Box box; //the box of this puzzle
    private BagOfPieces bag; //the bag of pieces of this puzzle
    private String name; //the name of this puzzle

    /**
     * Construct Puzzle class
     *
     * @param name  the name of this puzzle
     * @param box  the box of this puzzle
     * @param bag  the bag of pieces of this puzzle
     * @modifies name
     * @modifies box
     * @modifies bag
     * @post     {@code this.name == name}
     * @post     {@code this.box == box}
     * @post     {@code this.bag == bag}
     * @throws   NullPointerException  If box == null || bag == null
     */
    public Puzzle(String name, Box box, BagOfPieces bag)
            throws NullPointerException {
        if (box == null) {
            throw new NullPointerException("While constructing Puzzle: box == "
                    + "null");
        }
        if (bag == null) {
            throw new NullPointerException("While constructing Puzzle: bag == "
                    + "null");
        }
        this.name = name;
        this.box = box;
        this.bag = bag;        
    }

    /**
     * Get the value of box
     *
     * @return the value of box
     */
    public Box getBox() {
        return box;
    }

    /**
     * Set the value of box
     *
     * @param box new value of box
     */
    public void setBox(Box box) {
        this.box = box;
    }

    /**
     * Get the value of bag
     *
     * @return the value of bag
     */
    public BagOfPieces getBagOfPieces() {
        return bag;
    }

    /**
     * Set the value of bag
     *
     * @param bagOfPieces  the new value
     */
    public void setBagOfPieces(BagOfPieces bagOfPieces) {
        this.bag = bagOfPieces;
    }

    /**
     * Move the piece at index {@code index} in the bag to position
     * {@code anchorPos} in the box
     *
     * @pre      canPlace(anchorPos, index)
     * @param    anchorPos  position where the piece will be placed
     * @param    index  the index of the piece in {@code bag}
     * @modifies box
     * @modifies bag
     * @post     {@code bag.getPiece(index)} has been removed from the bag
     *           and has been placed in the box on position {@code anchorPos}.
     * @throws   NullPointerException  If anchorPos == null
     */
    public void placePiece(Position anchorPos, int index)
            throws NullPointerException {
        //check precondition
        if (anchorPos == null) {
            throw new NullPointerException("While executing placePiece("
                    + anchorPos.toString() + ", " + bag.getPiece(index).toString() + "): "
                    + "anchorPos == null");
        }

        //place piece
        box.placePiece(anchorPos, bag.getPiece(index));
        bag.removePiece(index);
    }

    /**
     * Move the piece on position {@code p} in the box to the bag
     * 
     * @param    p  the position where the piece to remove is in the box
     * @modifies box
     * @modifies bag
     * @post     box.getCell(p).getPlacement().getPiece() has been removed from
     *           the box and placed in the bag
     * @throws   NullPointerException  If p == null
     */
    public void removePiece(Position p) throws NullPointerException {
        //check precondition
        if (p == null) {
            throw new NullPointerException("While executing removePiece("
                    + p.toString() + "): p == null");
        }

        //remove piece
        bag.restorePiece(bag.getIndex(box.getCell(p).getPlacement().getPiece()));
        box.removePiece(p);
    }
    
    /**
     * Determines whether the piece at index {@code index} can be placed at
     * {@code anchorPos}
     * 
     * @param  anchorPos
     * @param  index  the index of the piece in {@code bag}
     * @return {@code (!(bag.getMultiplicity(index) == 0)
                && box.canPlace(anchorPos, bag.getPiece(index)))}
     * @throws   NullPointerException  If anchorPos == null
     */
    public boolean canPlace(Position anchorPos, int index)
            throws NullPointerException {
        if (anchorPos == null) {
            throw new NullPointerException("While executing canPlace: "
                    + "anchorPos == null");
        } else if (0 > anchorPos.getRow() || anchorPos.getRow() >= box.getRowCount()
                || 0 > anchorPos.getCol() || anchorPos.getCol() >= box.getColCount()) {
            return false;
        }
        return (!(bag.getMultiplicity(index) == 0)
                && box.canPlace(anchorPos, bag.getPiece(index)));
    }

    /**
     * Determines whether the puzzle is currently solved
     *
     * @return {\forall i; 0 <= i < box.getRowCount(); {\forall j;
     *             0 <= j < box.getColCount(); !box.getCell(i,j).isEmpty()=}}
     */
    public boolean isSolved() {
        for (Cell c : this.box) {
            if (c.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }
}
