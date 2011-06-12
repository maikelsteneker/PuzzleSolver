package puzzlesolver.solvers;

import java.util.ArrayList;
import java.util.List;
import puzzlesolver.model.Puzzle;

/**
 * Base class for (automatic) puzzle solvers.
 *
 * Listeners can be added via {@code addListener}.
 *
 * @author wstomv, Maikel Steneker (0753708)
 */
public abstract class Solver {
    /**
     *
     */
    protected Puzzle puzzle; // the puzzle being solved
    /**
     * Number of solutions found
     */
    protected int nSolutionsFound;
    /**
     * Receives events
     */
    protected List<SolverListener> listeners;
    /**
     * The solver should continue searching
     */
    protected boolean continueSearch;

    /**
     * Constructs a new solver for given puzzle.
     *
     * @param puzzle the puzzle to be solved
     */
    public Solver(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.listeners = new ArrayList<SolverListener>();
        this.continueSearch = true;
    }

    /**
     * Gets the value of nSolutionsFound
     *
     * @return the value of nSolutionsFound
     */
    public int getNSolutionsFound() {
        return nSolutionsFound;
    }

    /**
     * Sets puzzle to solve.
     * 
     * @param puzzle  the puzzle to solve
     */
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Adds a listener for solver events.
     *
     * @param listener  the listener to be notified
     * @return this.listeners.add(listener)
     */
    public boolean addListener(SolverListener listener) {
        return this.listeners.add(listener);
    }

    /**
     * Removes a listener.
     *
     * @param listener  the listener to be removed
     * @return this.listeners.remove(listener)
     */
    public boolean removeListener(SolverListener listener) {
        return this.listeners.remove(listener);
    }

    /**
     * Removes all listeners.
     *
     * @return {\forall i; 0 <= i < listeners.size();
     *          removeListener(listeners.get(i)}
     */
    public boolean resetListeners() {
        boolean removeListener = true;
        for (SolverListener listener : listeners) {
            removeListener = removeListener && removeListener(listener);
        }
        return removeListener;
    }

    /**
     * Stops the solving process
     *
     * @post this.continueSearch = false
     */
    public void stop() {
        this.continueSearch = false;
    }

    /**
     * Finds all solutions of the puzzle, and
     * reports them one by one to the listener.
     */
    public abstract void findAll();

}
