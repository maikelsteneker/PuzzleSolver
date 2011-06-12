package puzzlesolver.model;

import java.awt.Color;
import junit.framework.TestCase;

/**
 * Test Box Class
 *
 * @author Maikel Steneker (0753708)
 * @since 26-3-2011
 */
public class BoxTest extends TestCase {

    Box instance;

    public BoxTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Cell[][] cells = {{new Cell(CellState.empty, null)}};
        instance = new Box(cells);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test the exception of the constructor
     */
    public void testException() {
        System.out.println("Constructor Exception");
        try {
            instance = new Box(null);
            fail("Box(null) should have thrown an exception.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("Box(null) should have thrown a NullPointerException, but it "
                    + "threw a " + e.toString() + " instead.");
        }
    }

    /**
     * Test of getCell method, of class Box.
     */
    public void testGetCell_int_int() {
        System.out.println("getCell");
        int row = 0;
        int col = 0;
        Cell[][] cells = {{new Cell(CellState.empty, null)}};
        instance = new Box(cells);
        Cell expResult = cells[0][0];
        Cell result = instance.getCell(row, col);
        assertEquals(expResult, result);

        try {
            instance.getCell(1, 0);
            fail("getCell(1,0) should have thrown an exception.");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("getCell(1,0) should have thrown an IndexOutOfBoundsException,"
                    + " but it threw a " + e.toString() + " instead.");
        }

        try {
            instance.getCell(0, 1);
            fail("getCell(0,1) should have thrown an exception.");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("getCell(0,1) should have thrown an IndexOutOfBoundsException,"
                    + " but it threw a " + e.toString() + " instead.");
        }
    }

    /**
     * Test of getCell method, of class Box.
     */
    public void testGetCell_Position() {
        System.out.println("getCell");
        Position p = new Position(0, 0);
        Cell[][] cells = {{new Cell(CellState.empty, null)}};
        instance = new Box(cells);
        Cell expResult = cells[0][0];
        Cell result = instance.getCell(p);
        assertEquals(expResult, result);

        p = new Position(1, 0);
        try {
            instance.getCell(p);
            fail("getCell(" + p.toString() + ") should have thrown an exception"
                    + ".");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("getCell(" + p.toString() + ") should have thrown an "
                    + "IndexOutOfBoundsException, but it threw a "
                    + e.toString() + " instead.");
        }

        p = new Position(0, 1);
        try {
            instance.getCell(p);
            fail("getCell(" + p.toString() + ") should have thrown an exception"
                    + ".");
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("getCell(" + p.toString() + ") should have thrown an "
                    + "IndexOutOfBoundsException, but it threw a "
                    + e.toString() + " instead.");
        }
    }

    /**
     * Test of setCell method, of class Box.
     */
    public void testSetCell() {
        System.out.println("setCell");
        Cell c = new Cell(CellState.empty, null);
        int row = 0;
        int col = 0;
        instance.setCell(c, row, col);
        assertEquals(c, instance.getCell(row, col));

        try {
            instance.setCell(null, 0, 0);
            fail("setCell(null, 0,0) should have thrown an exception, but it "
                    + "did not.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("setCell(null, 0,0) should have thrown a NullPointerException,"
                    + " but it threw a " + e.toString() + " instead.");
        }
    }

    /**
     * Test of getRowCount method, of class Box.
     */
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = 1;
        int result = instance.getRowCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColCount method, of class Box.
     */
    public void testGetColCount() {
        System.out.println("getColCount");
        int expResult = 1;
        int result = instance.getColCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of placePiece method, of class Box.
     */
    public void testPlacePiece() {
        System.out.println("placePiece");
        Position anchorPos = new Position(0, 0);
        Position[] p = {new Position(0, 0)};
        Orientation[] o = {new Orientation(p)};
        Piece piece = new Piece('a', Color.black, o);
        Placement placement = new Placement(anchorPos, piece);
        Cell[][] cells = {{new Cell(CellState.empty, placement)}};
        instance = new Box(cells);
        instance.placePiece(anchorPos, piece);
        assertTrue(placement.getAnchorPos() == instance.getCell(anchorPos).
                getPlacement().getAnchorPos()
                && placement.getPiece().equals(instance.getCell(anchorPos).
                getPlacement().getPiece()));

        if (instance.getCell(anchorPos).isEmpty()) {
            instance.removePiece(anchorPos);
        }
        try {
            instance.placePiece(anchorPos, null);
            fail("placePiece(" + anchorPos + ", null) should have thrown an "
                    + "exception, but it did not.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("placePiece(" + anchorPos + ", null) should have thrown a "
                    + "NullPointerException, but it threw a "
                    + e.toString() + " instead.");
        }

        if (instance.getCell(anchorPos).isEmpty()) {
            instance.removePiece(anchorPos);
        }
        try {
            instance.placePiece(null, piece);
            fail("placePiece(null, " + piece.toString() + ") should have thrown"
                    + " an exception, but it did not.");
        } catch (NullPointerException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("placePiece(null, " + piece.toString() + ") should have thrown"
                    + " a NullPointerException, but it threw a "
                    + e.toString() + " instead.");
        }

        if (instance.getCell(anchorPos).isEmpty()) {
            instance.removePiece(anchorPos);
        }
        Position[] p2 = {new Position(0, 0), new Position(0, 1)};
        Orientation[] o2 = {new Orientation(p2)};
        piece = new Piece('a', Color.black, o2);
        try {
            instance.placePiece(anchorPos, piece);
            fail("placePiece(" + anchorPos.toString() + ", " + piece.toString()
                    + ") should have thrown an exception, but it did not.");
        } catch (IllegalArgumentException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("placePiece(" + anchorPos.toString() + ", " + piece.toString()
                    + ") should have thrown an IllegalArgumentException, but it"
                    + " threw a " + e.toString() + " instead.");
        }
    }

    /**
     * Test of canPlace method, of class Box.
     */
    public void testCanPlace() {
        System.out.println("canPlace");
        Position anchorPos = new Position(0, 0);
        Position[] p = {new Position(0, 0), new Position(0, 1)};
        Orientation[] o = {new Orientation(p)};
        Piece piece = new Piece('a', Color.black, o);
        boolean expResult = false;
        boolean result = instance.canPlace(anchorPos, piece);
        assertEquals(expResult, result);

        Position[] p2 = {new Position(0, 0)};
        Orientation[] o2 = {new Orientation(p2)};
        piece = new Piece('a', Color.black, o2);
        expResult = true;
        result = instance.canPlace(anchorPos, piece);
        assertEquals(expResult, result);
    }

    /**
     * Test of removePiece method, of class Box.
     */
    public void testRemovePiece() {
        System.out.println("removePiece");
        Position[] pos = {new Position(0, 0), new Position(0, 1)};
        Orientation[] o = {new Orientation(pos)};
        Piece piece = new Piece('a', Color.red, o);
        Position p = new Position(0, 0);
        Placement placement = new Placement(p, piece);
        Cell[][] cells = {{new Cell(CellState.occupied, placement), new Cell(CellState.occupied, placement)}};
        instance = new Box(cells);
        instance.removePiece(p);
        assertTrue(instance.getCell(p).isEmpty());
    }

    /**
     * Test of BoxIterator, inner class of class Box
     */
    public void testIterator() {
        System.out.println("Iterator(1)");
        Cell cell00 = new Cell(CellState.empty, null);
        Cell cell01 = new Cell(CellState.empty, null);
        Cell cell10 = new Cell(CellState.empty, null);
        Cell cell11 = new Cell(CellState.empty, null);
        Cell[][] cells = {{cell00, cell01}, {cell10, cell11}};
        instance = new Box(cells);
        boolean[][] covered = new boolean[2][2];
        for (Cell c : instance) {
            if (c.equals(cell00) && !covered[0][0]) {
                covered[0][0] = true;
            } else if (c.equals(cell00) && covered[0][0]) {
                fail("A cell was returned twice.");
            } else if (c.equals(cell01) && !covered[0][1]) {
                covered[0][1] = true;
            } else if (c.equals(cell01) && covered[0][1]) {
                fail("A cell was returned twice.");
            } else if (c.equals(cell10) && !covered[1][0]) {
                covered[1][0] = true;
            } else if (c.equals(cell10) && covered[1][0]) {
                fail("A cell was returned twice.");
            } else if (c.equals(cell11) && !covered[1][1]) {
                covered[1][1] = true;
            } else if (c.equals(cell11) && covered[1][1]) {
                fail("A cell was returned twice.");
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (!covered[i][j]) {
                    fail("One or more cells were not returned.");
                }
            }
        }
    }

    /**
     * Test of BoxIterator, inner class of class Box
     */
    public void testIterator2() {
        System.out.println("Iterator(2)");
        Position[] pos = {new Position(0, 0), new Position(0, 1)};
        Orientation[] o = {new Orientation(pos)};
        Piece piece = new Piece('a', Color.red, o);
        Position p = new Position(0, 0);
        Placement placement = new Placement(p, piece);
        Cell cell00 = new Cell(CellState.occupied, placement);
        Cell cell01 = new Cell(CellState.occupied, placement);
        Cell[][] cells = {{cell00, cell01}};
        instance = new Box(cells);
        boolean[][] covered = new boolean[1][2];

        for (Cell c : instance) {
            if (c.equals(cell00) && !covered[0][0]) {
                covered[0][0] = true;
            } else if (c.equals(cell00) && covered[0][0]) {
                fail("A cell was returned twice.");
            } else if (c.equals(cell01) && !covered[0][1]) {
                covered[0][1] = true;
            } else if (c.equals(cell01) && covered[0][1]) {
                fail("A cell was returned twice.");
            }
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (!covered[i][j]) {
                    fail("One or more cells were not returned.");
                }
            }
        }
    }
}
