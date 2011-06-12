package puzzlesolver.model;

/**
 * Stores a position as two coordinates ({@code row} and {@code col}).
 *
 * @author Maikel Steneker (0753708)
 * @since 11-5-2011
 */
public class Position {
    //Internal representation:
    private int row;
    private int col;

    /**
     * Constructs Position class
     *
     * @pre     {@code true}
     * @param   row  row number
     * @param   col  column number
     * @post    {@code this.row = row}
     * @post    {@code this.col = col}
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Get the value of row
     *
     * @pre     {@code true}
     * @return  {@code row}
     */
    public int getRow() {
        return row;
    }

    /**
     * Set the value of row
     *
     * @pre   {@code true}
     * @param row  new value of row
     * @post  {@code this.row = row}
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Get the value of col
     *
     * @pre    {@code true}
     * @return {@code col}
     */
    public int getCol() {
        return col;
    }

    /**
     * Set the value of col
     *
     * @pre   {@code true}
     * @param col  new value of col
     * @post  {@code this.col = col}
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Add position {@code pos1} to {@code pos2}
     *
     * @param pos1  position to add
     * @param pos2  position to add
     * @return {@code new Position(r,c)} with
     *          {@code r = pos1.getRow() + pos2.getRow()}
     *          {@code c = pos1.getCol() + pos2.getCol()}
     */
    public static Position addPositions(Position pos1, Position pos2) {
        int r = pos1.getRow() + pos2.getRow();
        int c = pos1.getCol() + pos2.getCol();
        return new Position(r,c);
    }
}
