package puzzlesolver.model;

import java.awt.Color;
import junit.framework.TestCase;

/**
 * Test BagOfPieces Class
 *
 * @author Maikel Steneker (0753708)
 * @since 26-3-2011
 */
public class BagOfPiecesTest extends TestCase {

    BagOfPieces instance;

    public BagOfPiecesTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Orientation[] o = {new Orientation()};
        Piece[] p = {new Piece('a', Color.black, o)};
        instance = new BagOfPieces(p);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getPiece method, of class BagOfPieces.
     */
    public void testGetPiece() {
        System.out.println("getPiece");
        Orientation[] o = {new Orientation()};
        Piece[] p = {new Piece('a', Color.black, o)};
        instance = new BagOfPieces(p);
        int index = 0;
        Piece expResult = p[0];
        Piece result = instance.getPiece(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMultiplicity method, of class BagOfPieces.
     */
    public void testGetMultiplicity() {
        System.out.println("getMultiplicity");
        int index = 0;
        int expResult = 1;
        int result = instance.getMultiplicity(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of removePiece method, of class BagOfPieces.
     */
    public void testRemovePiece() {
        System.out.println("removePiece");
        int index = 0;
        instance.removePiece(index);
        assertEquals(instance.getMultiplicity(index), 0);
    }

    /**
     * Test of restorePiece method, of class BagOfPieces.
     */
    public void testRestorePiece() {
        System.out.println("restorePiece");
        int index = 0;
        instance.restorePiece(index);
        assertEquals(instance.getMultiplicity(index), 2);
    }

    /**
     * Test of getIndex method, of class BagOfPieces.
     */
    public void testGetIndex() {
        System.out.println("getIndex");
        Position[] p = {new Position(0, 0)};
        Orientation[] o = {new Orientation(p)};
        Piece piece = new Piece('a', Color.black, o);
        Piece fakePiece = new Piece('b', Color.white, o);
        Piece[] pieces = {fakePiece, piece};
        instance = new BagOfPieces(pieces);
        int expResult = 1;
        int result = instance.getIndex(piece);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class BagOfPieces
     */
    public void testIsEmpty() {
        System.out.println("isEmpty");
        assertTrue("Bag was not empty, but isEmpty() returned true",
                !instance.isEmpty());
        instance.removePiece(0);
        assertTrue("Bag was empty, but isEmpty() returned false",
                instance.isEmpty());
    }
}
