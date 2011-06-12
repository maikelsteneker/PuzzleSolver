package puzzlesolver.model;

/**
 * Represents the state of a cell; either empty, occupied or blocked.
 *
 * @author Maikel Steneker (0753708)
 * @since 2-4-2011
 */
public enum CellState {
    /**
     * The cell is empty, a piece can be placed here
     */
    empty,
    /**
     * The cell is occupied by a piece
     */
    occupied,
    /**
     * The cell is blocked, a piece cannot be placed here
     */
    blocked
}
