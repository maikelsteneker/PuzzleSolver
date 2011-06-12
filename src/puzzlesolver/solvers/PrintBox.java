package puzzlesolver.solvers;

import java.io.PrintStream;
import javax.swing.JTextArea;
import puzzlesolver.model.*;

/**
 * Implements the SolverListener by textually representing solutions
 *
 * @author Maikel Steneker (0753708)
 * @since 12-6-2011
 */
public class PrintBox implements SolverListener {
    /**
     * Constructs PrintBox class
     *
     * @pre box != null
     * @param box  the box to print
     */
    public static void printBox(Box box) {
        printBox(box, System.out);
    }

    /**
     * Sends a textual representation of {@code box} to {@code target}.
     *
     * @pre box != null, target != null
     * @param box  the box to print
     * @param target  the target to send the textual representation of
     *                      {@code box} to
     * @post {@code box} has been printed to {@code target}
     */
    public static void printBox(Box box, PrintStream target) {
        for (int i = 0; i < box.getRowCount(); i++) {
            for (int j = 0; j < box.getColCount(); j++) {
                Cell c = box.getCell(i, j);
                if (c.isEmpty()) {
                    target.print(".");
                } else if (c.getState().equals(CellState.blocked)) {
                    target.print("#");
                }
                else {
                    target.print(box.getCell(i, j).getPlacement().getPiece().getName());
                }
                target.print(" ");
            }
            target.println();
        }
    }

    /**
     * Sends a textual representation of {@code box} to {@code target}.
     *
     * @pre box != null, target != null
     * @param box  the box to print
     * @param target  the target to send the textual representation of
     *                      {@code box} to
     * @post {@code box} has been printed to {@code target}
     */
    public static void printBox(Box box, JTextArea target) {
        //this method is provided to easily show textual representations in a
        //TextArea inside a GUI
        //it is more or less a direct copy of printBox(Box, PrintStream) above
        for (int i = 0; i < box.getRowCount(); i++) {
            for (int j = 0; j < box.getColCount(); j++) {
                Cell c = box.getCell(i, j);
                if (c.isEmpty()) {
                    target.append(".");
                } else if (c.getState().equals(CellState.blocked)) {
                    target.append("#");
                }
                else {
                    target.append(String.valueOf(box.getCell(i, j).
                            getPlacement().getPiece().getName()));
                }
                target.append(" ");
            }
            target.append("\n");
        }
    }

    /**
     * Prints a solution.
     *
     * @pre puzzle != null
     * @param solutionNumber  the number of the current solution
     * @param puzzle  the puzzle containing the solution
     */
    public void solutionFound(int solutionNumber, Puzzle puzzle) {
        System.out.println("Solution " + solutionNumber + ":");
        printBox(puzzle.getBox());
        System.out.println();
    }

    /**
     * Empty implementation to ignore piece placements.
     *
     * @param puzzle  current puzzle containing a partial solution
     */
    public void piecePlaced(Puzzle puzzle) {
        //do nothing
    }
}
