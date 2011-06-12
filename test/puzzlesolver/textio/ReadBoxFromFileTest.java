package puzzlesolver.textio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import junit.framework.TestCase;

/**
 * Test ReadBoxFromFile
 *
 * @author Maikel Steneker (0753708)
 * @since 3-4-2011
 */
public class ReadBoxFromFileTest extends TestCase {

    final static private String NONEXISTINGFILENAME = "DoesNotExist";

    public ReadBoxFromFileTest(String testName) {
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
     * Tests whether the files required for this test are present
     */
    public void testFilesArePresent() {
        System.out.println("testFilesArePresent");
        assertTrue(
                (new File("SimpleBox.txt").exists())
                && (new File("SimpleBox2.txt").exists())
                && (new File("SimplePieces.txt").exists())
                && (new File("SimplePieces2.txt").exists())
                && (new File("SimplePuzzle.txt").exists())
                && (new File("SimplePuzzle2.txt").exists())
                && (!new File(NONEXISTINGFILENAME).exists()));
    }

    /**
     * Test of readBoxFromFile method, of class ReadBoxFromFile.
     */
    public void testReadBoxFromFile_String() throws Exception {
        System.out.println("readBoxFromFile");
        String filename = NONEXISTINGFILENAME;
        try {
            ReadBoxFromFile.readBoxFromFile(filename);
            fail("While testing ReadBoxFromFile(" + filename + "): "
                    + "no exception was thrown.");
        } catch (FileNotFoundException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("While testing ReadBoxFromFile(" + filename + "): "
                    + "a FileNotFoundException was expected, but "
                    + e.toString() + " was thrown instead.");
        }

        filename = "SimpleBox.txt";
        File file = new File(filename);
        try {
            ReadBoxFromFile.readBoxFromFile(file);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of readBoxFromFile method, of class ReadBoxFromFile.
     */
    public void testReadBoxFromFile_SimpleBox() throws Exception {
        System.out.println("readPuzzleFromFile SimpleBox");
        File file = new File("SimpleBox.txt");
        try {
            ReadBoxFromFile.readBoxFromFile(file);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of readBoxFromFile method, of class ReadBoxFromFile.
     */
    public void testReadBoxFromFile_SimpleBox2() throws Exception {
        System.out.println("readPuzzleFromFile SimpleBox2");
        File file = new File("SimpleBox2.txt");
        try {
            ReadBoxFromFile.readBoxFromFile(file);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of readBoxFromFile method, of class ReadBoxFromFile.
     */
    public void testReadBoxFromFile_package() throws Exception {
        System.out.println("readPuzzleFromFile package");
        File file = new File("package.txt");
        try {
            ReadBoxFromFile.readBoxFromFile(file);
            fail("No exception was thrown.");
        } catch (IOException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }
}
