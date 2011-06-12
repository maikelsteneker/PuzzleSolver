package puzzlesolver.model;

import java.awt.Color;
import junit.framework.TestCase;

/**
 * Tests Piece Class.
 *
 * @author Maikel Steneker
 * @since 25-3-2011
 */
public class PieceTest extends TestCase {
    Piece instance;
    public PieceTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Orientation[] a = {new Orientation()};
        instance = new Piece('a', Color.black, a);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getColor method, of class Piece.
     */
    public void testGetColor() {
        System.out.println("getColor");

        Color expResult = Color.black;
        Color result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setColor method, of class Piece.
     */
    public void testSetColor() {
        System.out.println("setColor");
        Color c = Color.white;
        instance.setColor(c);
        assertEquals(c, instance.getColor());
    }

    /**
     * Test the NullPointerException of setColor
     */
    public void testSetColorException() {
        try {
            instance.setColor(null);
            fail("setColor(null) should have thrown an exception.");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("setColor(null) should have thrown a NullPointerException, "
                    + "but a " + e.toString() + " was thrown instead.");
        }
    }

    /**
     * Test of getName method, of class Piece.
     */
    public void testGetName() {
        System.out.println("getName");
        char expResult = 'a';
        char result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Piece.
     */
    public void testSetName() {
        System.out.println("setName");
        char name = 'b';
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getOrientation method, of class Piece.
     */
    public void testGetOrientation() {
        System.out.println("getOrientation");
        Position[] p = {new Position(0,0)};
        Orientation[] a = {new Orientation(p)};
        instance = new Piece('a', Color.black, a);
        Orientation expResult = a[0];
        Orientation result = instance.getOrientation();
        assertEquals(expResult, result);
    }

    /**
     * Test of turn method, of class Piece.
     */
    public void testTurn_0args() {
        System.out.println("turn");
        Position[][] p = {
            {new Position(0,0), new Position(1,1)},
            {new Position(2,2), new Position(3,3), new Position(4,4)},
            {new Position(5,5), new Position(6,6)}
        };
        Orientation[] o = new Orientation[p.length];
        for (int i = 0; i < o.length; i++) {
            o[i] = new Orientation(p[i]);
        }
        instance = new Piece('a', Color.black, o);
        for (int i = 0; i < (2 * o.length); i++) {
            if (!(o[i%o.length].equals(instance.getOrientation()))) {
                fail("turn() did not succesfully turn: orientation != " +
                        (i % o.length) + "after turning " + i + "times.");
            }
            instance.turn();
        }
        assertTrue(true);
    }

    /**
     * Test of turn method, of class Piece.
     */
    public void testTurn_int() {
        System.out.println("turn");
        Position[][] p = {
            {new Position(0,0), new Position(1,1)},
            {new Position(2,2), new Position(3,3), new Position(4,4)},
            {new Position(5,5), new Position(6,6)}
        };
        Orientation[] o = new Orientation[p.length];
        for (int i = 0; i < o.length; i++) {
            o[i] = new Orientation(p[i]);
        }
        instance = new Piece('a', Color.black, o);
        for (int i = 0; i < (2 * o.length); i++) {
            if (!(o[(2*i)%o.length].equals(instance.getOrientation()))) {
                fail("turn() did not succesfully turn: orientation != " +
                        ((2*i) % o.length) + "after turning " + (2*i) + "times.");
            }
            instance.turn(2);
        }
        assertTrue(true);
    }

    /**
     * Test of the NullPointerExceptions in the constructor
     */
    public void testExceptions() {
        try {
            instance = new Piece('a', Color.black, null);
            fail("Piece(name, c, o) should have thrown an exception while "
                    + "testing with Piece('a', Color.black, null).");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, the test passes
        } catch (Exception e) {
            fail("Piece(name, c, o) should have thrown a NullPointerException"
                    + " while testing with Piece('a', Color.black, null), but "
                    + e.toString() + " was thrown instead.");
        }
        Orientation[] o = {new Orientation()};
        try {
            instance = new Piece('a', null, o);
            fail("Piece(name, c, o) should have thrown an exception while "
                    + "testing with Piece('a', null, " + o.toString() + ").");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, the test passes
        } catch (Exception e) {
            fail("Piece(name, c, o) should have thrown a NullPointerException"
                    + " while testing with Piece('a', null, " + o.toString()
                    + "), but " + e.toString() + " was thrown instead.");
        }
    }

    /**
     * Test of equals method, of class Piece
     */
    public void testEquals() {
        Position[] p1 = {new Position(0,0)};
        Orientation[] a = {new Orientation(p1)};
        instance = new Piece('a', Color.black, a);
        assertTrue(instance.equals(instance));

        Position[] p2 = {new Position(0,0), new Position(0,1)};
        Orientation[] b = {new Orientation(p2)};
        Piece piece = new Piece('a', Color.black, b);
        assertTrue(!instance.equals(piece));
    }
}
