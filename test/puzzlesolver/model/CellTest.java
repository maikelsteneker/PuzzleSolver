package puzzlesolver.model;

import java.awt.Color;
import junit.framework.TestCase;

/**
 * Test Cell Class
 *
 * @author Maikel Steneker (0753708)
 * @since 26-3-2011
 */
public class CellTest extends TestCase {

    Cell instance;

    public CellTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Orientation[] o = {new Orientation()};
        instance = new Cell(CellState.empty, new Placement(new Position(0, 0),
                new Piece('a', Color.black, o)));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test the exception throwing in the constructor
     */
    public void testException() {
        try {
            instance = new Cell(null, null);
            fail("Cell(null, null) should have thrown an exception, but it "
                    + "did not.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("Cell(null, null) should have thrown a NullPointerException,"
                    + " but a " + e.toString() + " was thrown instead.");
        }
    }

    /**
     * Test of getState method, of class Cell.
     */
    public void testGetState() {
        System.out.println("getState");
        CellState expResult = CellState.empty;
        CellState result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of setState method, of class Cell.
     */
    public void testSetState() {
        System.out.println("setState");
        CellState state = CellState.occupied;
        instance.setState(state);
        assertEquals(state, instance.getState());

        try {
            instance.setState(null);
            fail("setState(null) should have thrown an exception, but it "
                    + "did not.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("setState(null) should have thrown a NullPointerException, but"
                    + " a " + e.toString() + " was thrown instead.");
        }
    }

    /**
     * Test of getPlacement method, of class Cell.
     */
    public void testGetPlacement() {
        System.out.println("getPlacement");
        Orientation[] o = {new Orientation()};
        Placement p = new Placement(new Position(0, 0), new Piece('a',
                Color.black, o));
        instance = new Cell(CellState.empty, p);
        Placement expResult = p;
        Placement result = instance.getPlacement();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPlacement method, of class Cell.
     */
    public void testSetPlacement() {
        System.out.println("setPlacement");
        Orientation[] o = {new Orientation()};
        Placement placement0 = new Placement(new Position(0, 0), new Piece('a',
                Color.black, o));
        Placement placement = new Placement(new Position(1, 2), new Piece('b',
                Color.white, o));
        instance = new Cell(CellState.empty, null);
        instance.setPlacement(placement);
        assertEquals(placement, instance.getPlacement());

        try {
            instance.setPlacement(placement0);
            fail("setPlacement(" + placement0.toString() + ") should have "
                    + "thrown an exception, but it did not.");
        } catch (IllegalStateException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("setPlacement(" + placement0.toString() + ") should have"
                    + " thrown a NullPointerException, but a " + e.toString()
                    + " was thrown instead.");
        }
    }

    /**
     * Test of resetPlacement method, of class Cell.
     */
    public void testResetPlacement() {
        System.out.println("resetPlacement");
        instance.resetPlacement();
        assertEquals(null, instance.getPlacement());
    }

    /**
     * Test of isEmpty method, of class Cell.
     */
    public void testIsEmpty() {
        System.out.println("isEmpty");
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);

        instance.setState(CellState.occupied);
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);

        instance.setState(CellState.blocked);
        expResult = false;
        result = instance.isEmpty();
        assertEquals(expResult, result);
    }
}
