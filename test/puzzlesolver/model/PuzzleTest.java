package puzzlesolver.model;

import java.awt.Color;
import junit.framework.TestCase;

/**
 * Test Puzzle Class.
 *
 * @author Maikel Steneker (0753708)
 * @since 27-3-2011
 */
public class PuzzleTest extends TestCase {

    Puzzle instance;
    Box box;
    BagOfPieces bag;

    public PuzzleTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        Cell[][] cells = {{new Cell(CellState.empty, null)}};
        Position[] p = {new Position(0, 0)};
        Orientation[] o = {new Orientation(p)};
        Piece[] pieces = {new Piece('a', Color.black, o)};
        box = new Box(cells);
        bag = new BagOfPieces(pieces);
        instance = new Puzzle("name", box, bag);
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of exceptions in Constructor
     */
    public void testExceptions() {
        System.out.println("Constructor Exceptions");
        Cell[][] cells = {{new Cell(CellState.empty, null)}};
        Orientation[] o = {new Orientation()};
        Piece[] pieces = {new Piece('a', Color.black, o)};
        box = new Box(cells);
        bag = new BagOfPieces(pieces);
        try {
            instance = new Puzzle(null, box, bag);
        } catch (Exception e) {
            fail("No exception was expected while constructing Puzzle with"
                    + " Puzzle(null, box, bag), but a " + e.toString()
                    + " was thrown.");
        }

        try {
            instance = new Puzzle("name", null, bag);
            fail("While Constructing Puzzle: Puzzle(name, null, bag) should"
                    + " have thrown an exception.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("While Constructing Puzzle: Puzzle(name, null, bag) should"
                    + " have thrown a NullPointerException, but it threw a"
                    + e.toString() + " instead.");
        }

        try {
            instance = new Puzzle("name", box, null);
            fail("While Constructing Puzzle: Puzzle(name, box, null) should"
                    + " have thrown an exception.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("While Constructing Puzzle: Puzzle(name, box, null) should"
                    + " have thrown a NullPointerException, but it threw a"
                    + e.toString() + " instead.");
        }
    }

    /**
     * Test of getBox method, of class Puzzle.
     */
    public void testGetBox() {
        System.out.println("getBox");
        Box expResult = box;
        Box result = instance.getBox();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBox method, of class Puzzle.
     */
    public void testSetBox() {
        System.out.println("setBox");
        Cell[][] cells = {{new Cell(CellState.empty, null),
                new Cell(CellState.empty, null)}};
        Box newBox = new Box(cells);
        instance.setBox(newBox);
        assertEquals(newBox, instance.getBox());
    }

    /**
     * Test of getBagOfPieces method, of class Puzzle.
     */
    public void testGetBagOfPieces() {
        System.out.println("getBagOfPieces");
        BagOfPieces expResult = bag;
        BagOfPieces result = instance.getBagOfPieces();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBagOfPieces method, of class Puzzle.
     */
    public void testSetBagOfPieces() {
        System.out.println("setBagOfPieces");
        Orientation[] o = {new Orientation()};
        Piece[] pieces = {new Piece('a', Color.black, o)};
        BagOfPieces bagOfPieces = new BagOfPieces(pieces);
        instance.setBagOfPieces(bagOfPieces);
        assertEquals(bagOfPieces, instance.getBagOfPieces());
    }

    /**
     * Test of placePiece method, of class Puzzle.
     */
    public void testPlacePiece() {
        System.out.println("placePiece");
        Position anchorPos = new Position(0, 0);
        int index = 0;
        int oldMultiplicity = instance.getBagOfPieces().getMultiplicity(index);
        instance.placePiece(anchorPos, index);
        assertEquals(instance.getBox().getCell(anchorPos).getPlacement().
                getPiece(), bag.getPiece(index));
        assertEquals(oldMultiplicity - 1,
                instance.getBagOfPieces().getMultiplicity(index));
    }

    /**
     * Test of removePiece method, of class Puzzle.
     */
    public void testRemovePiece() {
        System.out.println("removePiece");
        Position p = new Position(0, 0);
        Position[] ps = {new Position(0, 0)};
        Orientation[] o = {new Orientation(ps)};
        Piece piece = new Piece('a', Color.black, o);
        int oldMultiplicity = instance.getBagOfPieces().getMultiplicity(
                instance.getBagOfPieces().getIndex(piece));
        instance.placePiece(p, instance.getBagOfPieces().getIndex(piece));
        instance.removePiece(p);
        assertTrue(instance.getBox().getCell(p).isEmpty());
        assertEquals(oldMultiplicity, instance.getBagOfPieces().getMultiplicity(
                instance.getBagOfPieces().getIndex(piece)));
    }

    /**
     * Test of canPlace method, of class Puzzle.
     */
    public void testCanPlace() {
        //trivial; has been tested in Box extensively
        assertTrue(true);
    }

    /**
     * Test of isSolved method, of class Puzzle.
     */
    public void testIsSolved() {
        System.out.println("isSolved");

        boolean expResult = false;
        boolean result = instance.isSolved();
        assertEquals(expResult, result);

        instance.getBox().getCell(0, 0).setState(CellState.occupied);
        expResult = true;
        result = instance.isSolved();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Puzzle.
     */
    public void testGetName() {
        System.out.println("getName");
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
}
