package puzzlesolver.model;

import java.awt.Color;
import junit.framework.TestCase;

/**
 * Test Placement Class.
 *
 * @author Maikel Steneker
 * @since 25-3-2011
 */
public class PlacementTest extends TestCase {
    Placement instance;
    public PlacementTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Orientation[] o = {new Orientation()};
        instance = new Placement(new Position(0,0),
                new Piece('a',Color.black,o));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of exceptions in the constructor
     */
    public void testException1() {
        System.out.println("Exceptions");
        Orientation[] o = {new Orientation()};
        try {
            instance = new Placement(new Position(0,0), null);
            fail("Placement(new Position(0,0), null) should have thrown an "
                    + "exception.");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Placement(new Position(0,0), null) should have thrown a "
                    + "NullPointerException, "
                    + "but a " + e.toString() + " was thrown instead.");
        }
    }

    /**
     * Test of exceptions in the constructor
     */
    public void testException2() {
        System.out.println("Exceptions");
        Orientation[] o = {new Orientation()};
        try {
            instance = new Placement(null, new Piece('a', Color.black, o));
            fail("Placement(null, new Piece('a', Color.black,"
                    + " {new Orientation()}) should have thrown an exception.");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Placement(null, new Piece('a', Color.black,"
                    + " {new Orientation()}) should have thrown a"
                    + " NullPointerException, but a " + e.toString() + " was "
                    + "thrown instead.");
        }
    }

    /**
     * Test of getAnchorPos method, of class Placement.
     */
    public void testGetAnchorPos() {
        System.out.println("getAnchorPos");
        Orientation[] o = {new Orientation()};
        Position p = new Position(0,0);
        instance = new Placement(p, new Piece('a',Color.black,o));
        Position expResult = p;
        Position result = instance.getAnchorPos();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnchorPos method, of class Placement.
     */
    public void testSetAnchorPos() {
        System.out.println("setAnchorPos");
        Position anchorPos = new Position(1,2);
        instance.setAnchorPos(anchorPos);
        assertEquals(anchorPos, instance.getAnchorPos());

        try {
            instance.setAnchorPos(null);
            fail("setAnchorPos(null) should have thrown an exception.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("setAnchorPos(null) should have thrown a NullPointerException,"
                    + " but a " + e.toString() + " was thrown instead.");
        }
    }

    /**
     * Test of getPiece method, of class Placement.
     */
    public void testGetPiece() {
        System.out.println("getPiece");
        Orientation[] o = {new Orientation()};
        Piece p = new Piece('a', Color.black, o);
        instance = new Placement(new Position(0,0), p);
        Piece expResult = p;
        Piece result = instance.getPiece();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPiece method, of class Placement.
     */
    public void testSetPiece() {
        System.out.println("setPiece");
        Orientation[] o = {new Orientation()};
        Piece piece = new Piece('a', Color.black, o);
        instance.setPiece(piece);
        assertEquals(piece, instance.getPiece());

        try {
            instance.setAnchorPos(null);
            fail("setPiece(null) should have thrown an exception.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("setPiece(null) should have thrown a NullPointerException,"
                    + " but a " + e.toString() + " was thrown instead.");
        }
    }

}
