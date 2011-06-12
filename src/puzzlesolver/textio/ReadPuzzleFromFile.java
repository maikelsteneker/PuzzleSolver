package puzzlesolver.textio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import puzzlesolver.model.Box;
import puzzlesolver.model.BagOfPieces;
import puzzlesolver.model.Puzzle;

/**
 * Reads a puzzle from a text file and initializes it
 *
 * @author Maikel Steneker (0753708)
 * @since 12-6-2011
 */
public class ReadPuzzleFromFile {
    /**
     * Reads a puzzle from the file with the name {@code filename}
     *
     * @param  filename  filename of the file to read
     * @return the parsed puzzle
     * @throws FileNotFoundException  If the file does not exist
     * @throws IOException
     */
    public static Puzzle readPuzzleFromFile(String filename)
            throws FileNotFoundException, IOException {
        File file = new File(filename);
        return readPuzzleFromFile(file);
    }

    /**
     * Reads a puzzle from the file {@code file}
     * @param  file  file to read
     * @return the parsed puzzle
     * @throws FileNotFoundException  If the file does not exist
     * @throws IOException 
     */
    public static Puzzle readPuzzleFromFile(File file)
            throws FileNotFoundException, IOException {
        Scanner fileScanner = new Scanner(file);
        return new Puzzle(readName(fileScanner), readBox(fileScanner,
                file.getParentFile()),
                readBagOfPieces(fileScanner, file.getParentFile()));
    }

    private static String readName(Scanner source) throws IOException {
        if (!source.hasNext()) {
            throw new IOException("File format invalid.");
        }
        return source.next();
    }

    private static Box readBox(Scanner source, File dir)
            throws FileNotFoundException, IOException {
        if (!source.hasNext()) {
            throw new IOException("File format invalid.");
        }
        //return ReadBoxFromFile.readBoxFromFile(source.next());
        return ReadBoxFromFile.readBoxFromFile(new File(dir,source.next()));
    }

    private static BagOfPieces readBagOfPieces(Scanner source, File dir)
            throws FileNotFoundException, IOException {
        if (!source.hasNext()) {
            throw new IOException("File format invalid.");
        }
        //return ReadBagOfPiecesFromFile.readBagOfPiecesFromFile(source.next());
        return ReadBagOfPiecesFromFile.readBagOfPiecesFromFile(
                new File(dir,source.next()));
    }
}
