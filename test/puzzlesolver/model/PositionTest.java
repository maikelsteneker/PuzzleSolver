package puzzlesolver.model;

import junit.framework.TestCase;

/**
 * Tests Position Class.
 *
 * @author Maikel Steneker
 * @since 11-5-2011
 */
public class PositionTest extends TestCase {
    
    public PositionTest(String testName) {
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
     * Test of getRow method, of class Position.
     */
    public void testGetRow() {
        System.out.println("getRow");
        Position instance = new Position(1,0);
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRow method, of class Position.
     */
    public void testSetRow() {
        System.out.println("setRow");
        int row = 2;
        Position instance = new Position(1,0);
        instance.setRow(row);
        assertEquals(row, instance.getRow());
    }

    /**
     * Test of getCol method, of class Position.
     */
    public void testGetCol() {
        System.out.println("getCol");
        Position instance = new Position(0,1);
        int expResult = 1;
        int result = instance.getCol();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCol method, of class Position.
     */
    public void testSetCol() {
        System.out.println("setCol");
        int col = 2;
        Position instance = new Position(0,1);
        instance.setCol(col);
        assertEquals(col, instance.getCol());
    }

    public void testAddPositions() {
        System.out.println("addPositions");
        Position pos = new Position(0,0);
        Position result = Position.addPositions(pos, pos);
        Position expResult = pos;
        assertEquals(expResult.getCol(), result.getCol());
        assertEquals(expResult.getRow(), result.getRow());
        
        Position pos1 = new Position(1,2);
        Position pos2 = new Position(-3,-5);
        result = Position.addPositions(pos1, pos2);
        expResult = new Position(-2,-3);
        assertEquals(expResult.getCol(), result.getCol());
        assertEquals(expResult.getRow(), result.getRow());
    }
}
