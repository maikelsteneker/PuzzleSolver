package puzzlesolver.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents box consisting of cells
 *
 * @author Maikel Steneker (0753708)
 * @since 12-6-2011
 *
 * Invariants:
 * @inv cells != null
 * @inv getRowCount() == cells.length
 * @inv {\forall i; 0 <= i <= cells.length; getColCount() == cells[i].length}
 * @inv {\forall i; 0 <= i <= j <= cells.length;
 *                                          cells[i].length == cells[j].length}
 */
public class Box implements Iterable<Cell> {
    //Internal representation:

    private Cell[][] cells; //two-dimensional array of cells

    /**
     * Constructs the Box class
     *
     * @pre      {@code true}
     * @param    cells  two-dimensional array of cells
     * @modifies cells
     * @post     {@code this.cells = cells}
     * @throws   NullPointerException  If {@code cells == null}
     */
    public Box(Cell[][] cells) {
        //check precondition
        if (cells == null) {
            throw new NullPointerException("While constructing Box: "
                    + "cells == null");
        }

        this.cells = cells;
    }

    /**
     * Get the value of a cell
     *
     * @pre      {@code true}
     * @param    row  row of the cell
     * @param    col  column of the cell
     * @return   {@code cells[row][col]}
     * @throws   IndexOutOfBoundsException  If (row > cells.length) ||
     *                                         (col > cells[0].length)
     */
    public Cell getCell(int row, int col) throws IndexOutOfBoundsException {
        if (row > cells.length) {
            throw new IndexOutOfBoundsException("While executing getCell(" + row
                    + ", " + col + "): row > cells.length");
        }
        if (col > cells[0].length) {
            throw new IndexOutOfBoundsException("While executing getCell(" + row
                    + ", " + col + "): col > cells.length");
        }
        return cells[row][col];
    }

    /**
     * Returns the cell at position {@code p}
     *
     * @pre      {@code true}
     * @param    p  position to get the cell from
     * @return   {@code cells[row][col]}
     * @throws   IndexOutOfBoundsException  If (p.getRow() > cells.length) ||
     *                                         (p.getCol() > cells[0].length)
     */
    public Cell getCell(Position p) throws IndexOutOfBoundsException {
        if (p.getRow() > cells.length) {
            throw new IndexOutOfBoundsException("While executing getCell("
                    + p.getRow() + ", " + p.getCol() + "): row > cells.length");
        }
        if (p.getCol() > cells[0].length) {
            throw new IndexOutOfBoundsException("While executing getCell("
                    + p.getRow() + ", " + p.getCol() + "): col > cells.length");
        }
        return cells[p.getRow()][p.getCol()];
    }

    /**
     * Returns the cell at position {@code row}, {@code col}
     *
     * @pre      {@code true}
     * @param    c  the new value of the cell
     * @param    row  the row in the array
     * @param    col  the column in the array
     * @modifies cells
     * @post     {@code this.cells[row][col] = c}
     * @throws   IndexOutOfBoundsException  If (row > cells.length) ||
     *                                         (col > cells[0].length)
     * @throws   NullPointerException  If {@code c == null}
     */
    public void setCell(Cell c, int row, int col) {
        //check precondition
        if (c == null) {
            throw new NullPointerException("While executing setCell(c," + row
                    + "," + col + "): c == null");
        }
        this.cells[row][col] = c;
    }

    /**
     * Returns the number of rows
     *
     * @return {@code cells.length}
     */
    public int getRowCount() {
        return cells.length;
    }

    /**
     * Returns the number of columns
     *
     * @return {@code cells[0].length}
     */
    public int getColCount() {
        return cells[0].length;
    }

    /**
     * Places Piece {@code piece} on anchor position {@code anchorPos}
     * 
     * @param    anchorPos  anchor position where the piece will be placed
     * @param    piece  piece to be placed
     * @modifies cells
     * @post     {@code piece} has been placed on {@code anchorPos}
     * @throws   NullPointerException  If piece == null || anchorPos == null
     * @throws   IllegalArgumentException  If !canPlace(anchorPos, piece)
     */
    public void placePiece(Position anchorPos, Piece piece)
            throws IllegalArgumentException, NullPointerException {
        if (piece == null) {
            throw new NullPointerException("While executing placePiece("
                    + anchorPos.toString() + ", " + piece.toString() + "): "
                    + "piece == null");
        }
        if (anchorPos == null) {
            throw new NullPointerException("While executing placePiece("
                    + anchorPos.toString() + ", " + piece.toString() + "): "
                    + "anchorPos == null");
        }
        if (!canPlace(anchorPos, piece)) {
            throw new IllegalArgumentException("While executing placePiece("
                    + anchorPos.toString() + ", " + piece.toString() + "): "
                    + "piece cannot be placed at that position.");
        }

        int row, col;
        Placement placement = new Placement(anchorPos, piece);
        for (Position p : piece.getOrientation()) {
            //for each cell that the piece covers
            row = p.getRow() + anchorPos.getRow(); //compute row
            col = p.getCol() + anchorPos.getCol(); //compute col
            cells[row][col].setPlacement(placement); //set the placement
            cells[row][col].setState(CellState.occupied); //set the state
        }
    }

    /**
     * Determines whether {@code piece} can be placed at {@code anchorPos}
     *
     * @pre      {@code true}
     * @param    anchorPos  anchor position where the piece will be placed
     * @param    piece  piece to be placed
     * @return   {\forall i; 0 <= i < piece.getOrientation().getCount();
     *                 getCell(row, col).isEmpty()} with
     * row = piece.getOrientation().getPosition(i).getRow() + anchorPos.getRow()
     *  and
     * col = piece.getOrientation().getPosition(i).getCol() + anchorPos.getCol()
     */
    public boolean canPlace(Position anchorPos, Piece piece) {
        for (Position p : piece.getOrientation()) {
            //for each cell that the piece covers
            int x = p.getRow() + anchorPos.getRow();
            int y = p.getCol() + anchorPos.getCol();
            if (x >= getRowCount()
                    || y >= getColCount()
                    || !getCell(x, y).isEmpty()) {
                return false;
            }
        }
        //no problems were found
        return true;
    }

    /**
     * Removes the piece that is placed on {@code p}
     *
     * @pre      {@code true}
     * @param    p  the position to remove the piece from
     * @modifies cells
     * @post     getCell(p).getState() == CellState.empty
     * @post     getCell(p).getPlacement() == null
     * @throws   NullPointerException  If p == null
     * @throws   IllegalStateException  If getCell(p).isEmpty()
     * @throws   IllegalArgumentException  If p.getRow() >= getRowCount()
     *                                            || p.getCol() >= getColCount()
     */
    public void removePiece(Position p) {
        //check precondition
        if (p == null) {
            throw new NullPointerException("While executing removePiece(p): "
                    + "p == null");
        }
        if (getCell(p).isEmpty()) {
            throw new IllegalStateException("While executing removePiece(p): "
                    + "the cell at position p is already empty.");
        }
        if ((p.getRow() >= getRowCount()) || p.getCol() >= getColCount()) {
            throw new IllegalArgumentException("While executing removePiece(p):"
                    + " p is not in the box");
        }
        //find the placement to remove
        Placement placement = getCell(p).getPlacement();
        //find all the cells this piece covers
        for (Cell c : this) { //for each cell in the box
            if (!c.isEmpty() && c.getPlacement() != null && placement.equals(
                    c.getPlacement())) {
                //the cell contains the placement to remove
                c.setState(CellState.empty); //set the cell state to empty
                c.resetPlacement(); //reset the placement
            }
        }
    }

    /**
     * Block every Position that Piece {@code piece} on
     * anchor position {@code anchorPos} covers
     *
     * @param    anchorPos  anchor position where the piece will be placed
     * @param    piece  piece to use
     * @modifies cells
     * @post     every position in {@code piece.getOrientation()}
     * has been blocked starting at {@code anchorPos}
     * @throws   NullPointerException  If piece == null || anchorPos == null
     * @throws   IllegalArgumentException  If !canPlace(anchorPos, piece)
     */
    public void blockPiece(Position anchorPos, Piece piece)
            throws IllegalArgumentException, NullPointerException {
        if (piece == null) {
            throw new NullPointerException("While executing placePiece("
                    + anchorPos.toString() + ", " + piece.toString() + "): "
                    + "piece == null");
        }
        if (anchorPos == null) {
            throw new NullPointerException("While executing placePiece("
                    + anchorPos.toString() + ", " + piece.toString() + "): "
                    + "anchorPos == null");
        }
        if (!canPlace(anchorPos, piece)) {
            throw new IllegalArgumentException("While executing placePiece("
                    + anchorPos.toString() + ", " + piece.toString() + "): "
                    + "piece cannot be placed at that position.");
        }

        int row, col;
        for (Position p : piece.getOrientation()) {
            //for each cell that the piece covers
            row = p.getRow() + anchorPos.getRow(); //compute row
            col = p.getCol() + anchorPos.getCol(); //compute col
            cells[row][col].setState(CellState.blocked); //set the state
        }
    }

    /**
     * Returns foreach iterator over {@code cells}
     *
     * @return iterator
     */
    public Iterator<Cell> iterator() {
        return new BoxIterator();
    }

    /**
     * Inner iteration class
     */
    public class BoxIterator implements Iterator<Cell> {

        private int i; //index of next element
        private int sentinel; //index of the last element + 1

        BoxIterator() {
            i = 0;
            sentinel = cells.length * cells[0].length;
        }

        /**
         * Determines whether or not there is a next cell
         *
         * @return {@code (i < sentinel)}
         */
        public boolean hasNext() {
            return (i < sentinel);
        }

        /**
         * Returns the next element
         *
         * @return getCell(new Position(i/cells[0].length, i%cells[0].length))
         * @throws NoSuchElementException  If (i == sentinel)
         */
        public Cell next() throws NoSuchElementException {
            if (i == sentinel) {
                throw new NoSuchElementException("BoxIterator.next");
            }
            Position p = new Position(i / cells[0].length, i % cells[0].length);
            i++;
            return getCell(p);
        }

        /**
         * Removes the last element returned by the iterator
         */
        public void remove() {
            throw new UnsupportedOperationException("Not supported.");
        }
    }
}
