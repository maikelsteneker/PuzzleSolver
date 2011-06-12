package puzzlesolver.textio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import junit.framework.TestCase;

/**
 * Test ReadPuzzleFromFile
 *
 * @author Maikel Steneker
 * @since 3-4-2011
 */
public class ReadPuzzleFromFileTest extends TestCase {

    final static private String NONEXISTINGFILENAME = "DoesNotExist";

    public ReadPuzzleFromFileTest(String testName) {
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
     * Test of readPuzzleFromFile method, of class ReadPuzzleFromFile.
     */
    public void testReadPuzzleFromFile_String() throws Exception {
        System.out.println("readPuzzleFromFile");
        String filename = NONEXISTINGFILENAME;
        try {
            ReadPuzzleFromFile.readPuzzleFromFile(filename);
            fail("While testing ReadPuzzleFromFile(" + filename + "): "
                    + "no exception was thrown.");
        } catch (FileNotFoundException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("While testing ReadPuzzleFromFile(" + filename + "): "
                    + "a FileNotFoundException was expected, but "
                    + e.toString() + " was thrown instead.");
        }

        filename = "SimplePuzzle.txt";
        File file = new File(filename);
        try {
            ReadPuzzleFromFile.readPuzzleFromFile(file);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of readPuzzleFromFile method, of class ReadPuzzleFromFile.
     */
    public void testReadPuzzleFromFile_SimplePuzzle() throws Exception {
        System.out.println("readPuzzleFromFile SimplePuzzle");
        File file = new File("SimplePuzzle.txt");
        try {
            ReadPuzzleFromFile.readPuzzleFromFile(file);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of readPuzzleFromFile method, of class ReadPuzzleFromFile.
     */
    public void testReadPuzzleFromFile_SimplePuzzle2() throws Exception {
        System.out.println("readPuzzleFromFile SimplePuzzle2");
        File file = new File("SimplePuzzle2.txt");
        try {
            ReadPuzzleFromFile.readPuzzleFromFile(file);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of readPuzzleFromFile method, of class ReadPuzzleFromFile.
     */
    public void testReadPuzzleFromFile_package() throws Exception {
        System.out.println("readPuzzleFromFile package");
        File file = new File("package.txt");
        try {
            ReadPuzzleFromFile.readPuzzleFromFile(file);
            fail("No exception was thrown.");
        } catch (IOException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }
}
