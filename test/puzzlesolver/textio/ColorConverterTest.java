package puzzlesolver.textio;

import java.awt.Color;
import java.util.Scanner;
import junit.framework.TestCase;

/**
 * Test ColorConverter class
 *
 * @author Maikel Steneker (0753708)
 * @since 3-4-2011
 */
public class ColorConverterTest extends TestCase {
    
    public ColorConverterTest(String testName) {
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
     * Test of fromName method, of class ColorConverter.
     */
    public void testFromName() {
        String name;
        Color expResult;
        Color result;

        name = "RED";
        System.out.println(name);
        expResult = Color.RED;
        result = ColorConverter.fromName(name);
        assertEquals(expResult, result);

        name = "CYAN";
        System.out.println(name);
        expResult = Color.CYAN;
        result = ColorConverter.fromName(name);
        assertEquals(expResult, result);

        name = "YELLOW";
        System.out.println(name);
        expResult = Color.YELLOW;
        result = ColorConverter.fromName(name);
        assertEquals(expResult, result);

        name = "GREEN";
        System.out.println(name);
        expResult = Color.GREEN;
        result = ColorConverter.fromName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of readColor method, of class ColorConverter.
     */
    public void testReadColor() {
        System.out.println("readColor");
        Scanner scanner = new Scanner("TEST RED TEST");
        Color expResult = null;
        Color result = ColorConverter.readColor(scanner);
        assertEquals(expResult, result);
        expResult = Color.RED;
        result = ColorConverter.readColor(scanner);
        assertEquals(expResult, result);
    }

}
