package puzzlesolver.solvers;

import puzzlesolver.model.*;

/**
 * Provides methods to search a puzzle by trying to cover a specific cell in the
 * box in all possible ways.
 *
 * @author Maikel Steneker (0753708)
 * @since 12-6-2011
 */
public class BacktrackSolver extends Solver {

    /**
     * Constructs a new Solver for a given puzzle
     *
     * @param puzzle  the puzzle to be solved
     */
    public BacktrackSolver(Puzzle puzzle) {
        super(puzzle);
    }

    /**
     * Finds all solutions of the puzzle, and
     * reports them one by one to the listener.
     */
    @Override
    public void findAll() {
        if (puzzle.isSolved()) {
            processSolution();
        } else { //there is an empty cell
            Position e = findEmptyCell(puzzle.getBox());
            for (Piece p : puzzle.getBagOfPieces()) {
                if (isAvailable(p, puzzle)) {
                    for (Orientation o : p) {
                        for (Position pos : o) {
                            Position absPos = Position.addPositions(pos, e);
                            int index = puzzle.getBagOfPieces().getIndex(p);
                            while (!o.equals(puzzle.getBagOfPieces().getPiece(index).getOrientation())) {
                                p.turn(); //turn the piece until it's in the correct orientation
                            }
                            if (continueSearch && puzzle.canPlace(absPos, index)) {
                                puzzle.placePiece(absPos, index);
                                piecePlaced();
                                findAll();

                                puzzle.removePiece(findPlacementPositionToRemove(p, absPos));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Reports a solution to the listener
     *
     * @pre     {@code puzzle.isSolved()}
     * @post    {@code nSolutionsFound = (\old nSolutionsFound) + 1}
     * @post    {@code puzzle has been reported to the listener
     *                                          if listener != null
     */
    private void processSolution() {
        nSolutionsFound++;
        for (SolverListener listener : listeners) {
            listener.solutionFound(nSolutionsFound, puzzle);
        }
    }

    private void piecePlaced() {
        for (SolverListener listener : listeners) {
            listener.piecePlaced(puzzle);
        }
    }

    private Position findEmptyCell(Box b) {
        for (int i = 0; i < b.getRowCount(); i++) {
            for (int j = 0; j < b.getColCount(); j++) {
                if (b.getCell(i, j).isEmpty()) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    private boolean isAvailable(Piece i, Puzzle p) {
        return (p.getBagOfPieces().getMultiplicity(p.getBagOfPieces().getIndex(i)) > 0);
    }

    private Position findPlacementPositionToRemove(Piece p, Position anchorPos) {
        Position pos = null;
        for (int i = 0; i < puzzle.getBox().getRowCount(); i++) {
            for (int j = 0; j < puzzle.getBox().getColCount(); j++) {
                if (puzzle.getBox().getCell(i, j).getPlacement() != null && p.equals(puzzle.getBox().getCell(i, j).getPlacement().getPiece()) && anchorPos.equals(puzzle.getBox().getCell(i, j).getPlacement().getAnchorPos())) {
                    pos = new Position(i, j);
                }
            }
        }
        return pos;
    }
}
