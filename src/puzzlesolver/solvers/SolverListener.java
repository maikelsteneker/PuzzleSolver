package puzzlesolver.solvers;

import java.util.EventListener;
import puzzlesolver.model.Puzzle;

/**
 * Interface with events that can be triggered by a {@code Solver}.
 *
 * @author wstomv, Maikel Steneker (0753708)
 */
public interface SolverListener extends EventListener {

    /**
     * Reports a solution, given by current state of {@code puzzle}.
     *
     * @param solutionNumber  sequence number of solution
     * @param puzzle  the solved puzzle
     * @pre {@code puzzle} is solved
     */
    void solutionFound(int solutionNumber, Puzzle puzzle);

    /**
     * Reports a placement in the box, given by current state of {@code puzzle}.
     *
     * @param puzzle  the current state of the puzzle
     */
    void piecePlaced(Puzzle puzzle);

}
