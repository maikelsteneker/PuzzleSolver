package puzzlesolver.model;

import junit.framework.TestCase;

/**
 * Tests Orientation class.
 *
 * @author Maikel Steneker
 * @since 25-3-2011
 */
public class OrientationTest extends TestCase {

    public OrientationTest(String testName) {
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

    /**
     * Test of Orientation(a) constructor.
     */
    public void testConstructorException() {
        System.out.println("constructorException");
        try {
            Orientation instance = new Orientation(null);
            fail("Orientation(a) should have thrown an exception while testing "
                    + "with Orientation(null)");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception
        } catch (Exception e) {
            fail("Orientation(a) should have thrown an NullPointerException "
                    + "while testing with Orientation(null), but "
                    + e.toString() + " was thrown instead.");
        }
        try {
            Position[] a = {new Position(0, 0)};
            Orientation instance = new Orientation(a);
        } catch (Exception e) {
            fail("Orientation(a) should not have thrown an exception while "
                    + "testing with Orientation({new Position(0,0})), but "
                    + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of getCount method, of class Orientation.
     */
    public void testGetCount() {
        System.out.println("getCount");
        Orientation instance = new Orientation();
        int expResult = 0;
        int result = instance.getCount();
        assertEquals(expResult, result);

        instance.add(new Position(0, 0));
        expResult = 1;
        result = instance.getCount();
        assertEquals(expResult, result);

        Position[] a = {new Position(0, 0)};
        instance = new Orientation(a);
        expResult = 1;
        result = instance.getCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition and add methods, of class Orientation.
     */
    public void testGetPositionAndAdd() {
        System.out.println("getPosition and add");
        Orientation instance = new Orientation();
        Position[] p = {new Position(0, 0), new Position(3, 1)};
        for (int i = 0; i < p.length; i++) {
            instance.add(p[i]);
        }
        for (int i = 0; i < p.length; i++) {
            if (!(instance.getPosition(i).equals(p[i]))) {
                fail(p[i].toString() + " was not correctly added: "
                        + instance.getPosition(i));
            }
        }
        try {
            instance.getPosition(p.length);
            fail("getPosition(i) should have thrown an exception while testing "
                    + "with getPosition(" + p.length + ").");
        } catch (IllegalArgumentException e) {
            assertTrue(true); //expected exception, the test passes
        } catch (Exception e) {
            fail("getPosition(i) should have thrown an IllegalArgumentException"
                    + " while testing with getPosition(" + p.length + "), but "
                    + e.toString() + " was thrown instead.");
        }
        try {
            instance.getPosition(-1);
            fail("getPosition(i) should have thrown an exception while testing"
                    + " with getPosition(-1).");
        } catch (IllegalArgumentException e) {
            assertTrue(true); //expected exception, the test passes
        } catch (Exception e) {
            fail("getPosition(i) should have thrown an IllegalArgumentException"
                    + " while testing with getPosition(-1), but "
                    + e.toString() + " was thrown instead.");
        }
    }

    /**
     * Test of toString method, of class Orientation.
     */
    public void testToString() {
        System.out.println("toString");

        Position[] p1 = {new Position(0, 0)};
        Orientation instance = new Orientation(p1);
        String expResult = "#";
        String result = instance.toString();
        assertEquals(expResult, result);

        Position[] p2 = {new Position(0, 0), new Position(1, 0)};
        instance = new Orientation(p2);
        expResult = "#\n#";
        result = instance.toString();
        assertEquals(expResult, result);

        Position[] p3 = {new Position(0, 0), new Position(1, 0),
            new Position(0, 1)};
        instance = new Orientation(p3);
        expResult = "##\n#.";
        result = instance.toString();
        assertEquals(expResult, result);

        instance = new Orientation();
        try {
            instance.toString();
            fail("toString() should have thrown an exception while testing with"
                    + " an empty Orientation.");
        } catch (IllegalArgumentException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("toString() should have thrown an IllegalArgumentException "
                    + "while testing with an empty Orientation, but "
                    + e.toString() + " was thrown instead.");
        }
    }
}
