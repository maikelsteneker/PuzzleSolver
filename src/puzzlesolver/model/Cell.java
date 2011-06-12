package puzzlesolver.model;

/**
 * Represents a cell
 *
 * @author Maikel Steneker (0753708)
 * @since 21-3-2011
 *
 * Invariants:
 * @inv state != null
 * @inv state == CellState.empty || state == CellState.occupied
 *                               || state == CellState.blocked
 * @inv getState() == state
 * @inv getPlacement() == placement
 * @inv isEmpty() == (state == empty)
 */
public class Cell {
    //Internal representation:
    private CellState state; //state of the cell
    private Placement placement; //placement of the cell (can be null)

    /**
     * Constructs Cell class
     *
     * @pre      {@code true}
     * @param    state  state of the cell
     * @param    placement  placement of the cell
     * @modifies state
     * @modifies placement
     * @post     {@code this.state == state}
     * @post     {@code this.placement == placement}
     * @throws   NullPointerException  If state == null
     */
    public Cell(CellState state, Placement placement)
            throws NullPointerException {
        //check precondition
        if (state == null) {
            throw new NullPointerException("While constructing Cell: "
                    + "state == null");
        }

        this.state = state;
        this.placement = placement;
    }

    /**
     * Get the value of state
     *
     * @return the value of state
     */
    public CellState getState() {
        return state;
    }

    /**
     * Set the value of state
     *
     * @param    state  new value of state
     * @modifies state
     * @throws   NullPointerException  If state == null
     * @post     {@code this.state == state}
     */
    public void setState(CellState state) throws NullPointerException {
        if (state==null) {
            throw new NullPointerException("While executing setState("
                    + state.toString() + "): state == null");
        }
        this.state = state;
    }

    /**
     * Get the value of placement
     *
     * @return the value of placement
     */
    public Placement getPlacement() {
        return placement;
    }

    /**
     * Set the value of placement
     *
     * @pre      {@code true}
     * @param    placement new value of placement
     * @modifies placement
     * @post     {@code this.placement == placement}
     * @post     {@code this.state == CellState.occupied}
     * @throws   IllegalStateException  If !isEmpty() && placement != null
     */
    public void setPlacement(Placement placement) {
        if (!isEmpty() && placement != null) {
            throw new IllegalStateException("While executing setPlacement(" +
                    placement.toString() + "): cell is " + state.toString());
        }
        this.placement = placement;
        this.state = CellState.occupied;
    }

    /**
     * Resets the value of placement to null
     *
     * @modifies placement
     * @post     {@code this.placement == null}
     */
    public void resetPlacement() {
        this.placement = null;
    }

    /**
     * Returns whether of not the cell is empty
     *
     * @return this.state == CellState.empty
     */
    public boolean isEmpty() {
        return (this.state == CellState.empty);
    }
}
