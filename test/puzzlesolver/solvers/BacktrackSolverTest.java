package puzzlesolver.solvers;

import junit.framework.TestCase;
import puzzlesolver.model.Puzzle;
import puzzlesolver.textio.ReadPuzzleFromFile;

/**
 * Provides a very basic test to test the BacktrackSolver.
 *
 * @author Maikel Steneker (0753708)
 * @since 12-6-2011
 */
public class BacktrackSolverTest extends TestCase {
    
    public BacktrackSolverTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBasic() {
        Puzzle puzzle = null;
        try {
            puzzle = ReadPuzzleFromFile.readPuzzleFromFile("SimplePuzzle.txt");
        } catch (Exception e) {
            fail("Couldn't read file.");
        }

        Solver solver = new BacktrackSolver(puzzle);
        SolverListener listener = new PrintBox();
        solver.addListener(listener);
        solver.findAll();
        System.out.println();
        System.out.println("Total number of solutions: " + solver.getNSolutionsFound());
    }

}
