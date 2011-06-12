package puzzlesolver.textio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import junit.framework.TestCase;

/**
 * Test ReadBagOfPiecesFromFile
 *
 * @author Maikel Steneker (0753708)
 * @since 3-4-2011
 */
public class ReadBagOfPiecesFromFileTest extends TestCase {

    final static private String NONEXISTINGFILENAME = "DoesNotExist";

    public ReadBagOfPiecesFromFileTest(String testName) {
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
     * Test of readBagOfPiecesFromFile method, of class ReadBagOfPiecesFromFile.
     */
    public void testReadBagOfPiecesFromFile_String() throws Exception {
        System.out.println("readBagOfPiecesFromFile");
        String filename = NONEXISTINGFILENAME;
        try {
            ReadBagOfPiecesFromFile.readBagOfPiecesFromFile(filename);
            fail("While testing ReadBagOfPiecesFromFile(" + filename + "): "
                    + "no exception was thrown.");
        } catch (FileNotFoundException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("While testing ReadBagOfPiecesFromFile(" + filename + "): "
                    + "a FileNotFoundException was expected, but "
                    + e.toString() + " was thrown instead.");
        }

        filename = "SimplePieces.txt";
        File file = new File(filename);
        try {
            ReadBagOfPiecesFromFile.readBagOfPiecesFromFile(file);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of readBagOfPiecesFromFile method, of class ReadBagOfPiecesFromFile.
     */
    public void testReadBagOfPiecesFromFile_SimplePieces() throws Exception {
        System.out.println("readBagOfPiecesFromFile SimplePieces");
        File file = new File("SimplePieces.txt");
        try {
            ReadBagOfPiecesFromFile.readBagOfPiecesFromFile(file);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of readBagOfPiecesFromFile method, of class ReadBagOfPiecesFromFile.
     */
    public void testReadBagOfPiecesFromFile_SimplePieces2() throws Exception {
        System.out.println("readBagOfPiecesFromFile SimplePieces2");
        File file = new File("SimplePieces2.txt");
        try {
            ReadBagOfPiecesFromFile.readBagOfPiecesFromFile(file);
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }

    /**
     * Test of readBagOfPiecesFromFile method, of class ReadBagOfPiecesFromFile.
     */
    public void testReadBagOfPiecesFromFile_package() throws Exception {
        System.out.println("readBagOfPiecesFromFile package");
        File file = new File("package.txt");
        try {
            ReadBagOfPiecesFromFile.readBagOfPiecesFromFile(file);
            fail("No exception was thrown.");
        } catch (IOException e) {
            assertTrue(true); //expected exception, test passes
        } catch (Exception e) {
            fail("Unexpected exception " + e.toString() + " was thrown.");
        }
    }
}
