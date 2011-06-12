package puzzlesolver.textio;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import puzzlesolver.model.BagOfPieces;
import puzzlesolver.model.Box;
import puzzlesolver.model.Orientation;
import puzzlesolver.model.Piece;
import puzzlesolver.model.Puzzle;

/**
 * Writes a puzzle to a file.
 *
 * @author Maikel Steneker (0753708)
 * @since 2-4-2011
 */
public class WritePuzzleToFile {

    final static private String PUZZLE = ".txt";
    final static private String BOX = "_Box.txt";
    final static private String BAG = "_Pieces.txt";
    final static private int LINELENGTH = 10;
    final static private char WORDSEPERATOR = ' ';
    final static private char PIECESEPERATOR = '=';
    final static private char ORIENTATIONSEPERATOR = '-';

    /**
     * Writes {@code puzzle} to a file
     *
     * @param puzzle  puzzle to write
     * @return false if the file a file already exists
     * @throws IOException  If there is a problem while writing to the file
     */
    public static boolean writePuzzleToFile(Puzzle puzzle) throws IOException {
        return writePuzzleToFile(puzzle, puzzle.getName() + PUZZLE);
    }

    /**
     * Writes {@code puzzle} to the file with the name {@code filename}
     *
     * @param puzzle  puzzle to write
     * @param filename  filename of the file to write to
     * @return false if the file a file already exists
     * @throws IOException  If there is a problem while writing to the file
     */
    public static boolean writePuzzleToFile(Puzzle puzzle, String filename)
            throws IOException {
        File puzzleFile = new File(filename);
        return writePuzzleToFile(puzzle, puzzleFile);
    }

    /**
     * Writes {@code puzzle} to the file {@code file}
     * 
     * @param puzzle  puzzle to write
     * @param file  the file to write to
     * @return false if the file a file already exists
     * @throws IOException  If there is a problem while writing to the file
     */
    public static boolean writePuzzleToFile(Puzzle puzzle, File file)
            throws IOException {
        if (!file.createNewFile()) {
            //the file already exists
            return false;
        }
        return confirmWritePuzzleToFile(puzzle, file);
    }

    private static boolean confirmWritePuzzleToFile(Puzzle puzzle, File puzzleFile) throws IOException {
        File boxFile = new File(puzzle.getName() + BOX);
        File bagFile = new File(puzzle.getName() + BAG);
        if (!boxFile.createNewFile()) {
            //the file already exists
            return false;
        } else if (!bagFile.createNewFile()) {
            //the file already exists
            return false;
        }
        confirmWritePuzzleToFile(puzzle, puzzleFile, boxFile, bagFile);
        return true;
    }

    private static void confirmWritePuzzleToFile(Puzzle puzzle, File puzzleFile, File boxFile, File bagFile) throws IOException {
        writePuzzleFile(puzzle, puzzleFile, boxFile.getName(), bagFile.getName());
        writeBoxFile(puzzle.getBox(), boxFile, puzzle.getName());
        writeBagFile(puzzle.getBagOfPieces(), bagFile, puzzle.getName());
    }

    private static void writePuzzleFile(Puzzle puzzle, File puzzleFile, String boxFileName, String bagFileName) throws IOException {
        FileWriter fstream = new FileWriter(puzzleFile);
        BufferedWriter out = new BufferedWriter(fstream);

        out.write(puzzle.getName()); //write the name of the puzzle
        out.newLine();
        out.write(boxFileName); //write the name of the box file
        out.newLine();
        out.write(bagFileName); //write the name of the bag file
        out.close();
    }

    private static void writeBoxFile(Box box, File boxFile, String name) throws IOException {
        FileWriter fstream = new FileWriter(boxFile);
        BufferedWriter out = new BufferedWriter(fstream);

        out.write(name); //write the name of the box
        out.newLine();
        out.write(Integer.toString(box.getRowCount())); //write the number of rows
        out.write(WORDSEPERATOR);
        out.write(Integer.toString(box.getColCount())); //write the number of columns
        out.newLine();

        for (int i = 0; i < box.getRowCount(); i++) {
            for (int j = 0; j < box.getColCount(); j++) {
                out.write(box.getCell(i, j).isEmpty() ? '.' : '#');
            }
            if (i < box.getRowCount() - 1) {
                out.newLine();
            }
        }

        out.close();
    }

    private static void writeBagFile(BagOfPieces bagOfPieces, File bagFile, String name) throws IOException {
        FileWriter fstream = new FileWriter(bagFile);
        BufferedWriter out = new BufferedWriter(fstream);

        out.write(name);
        out.newLine();
        
        for (Piece p : bagOfPieces) {
            for (int i = 0; i < LINELENGTH; i++) {
                out.write(PIECESEPERATOR);
            }
            out.newLine();
            
            out.write(p.getName());
            out.write(WORDSEPERATOR);
            out.write(convertColorToName(p.getColor()));
            out.write(WORDSEPERATOR);
            out.write(Integer.toString(bagOfPieces.getMultiplicity(bagOfPieces.getIndex(p))));
            out.newLine();

            for (Orientation o : p) {
                for (int i = 0; i < LINELENGTH; i++) {
                    out.write(ORIENTATIONSEPERATOR);
                }
                out.newLine();
                for (int i = 0; i < o.toString().length(); i++) {
                    if ((o.toString().charAt(i))=='\n') {
                        out.newLine();
                    } else {
                        out.write(o.toString().replace('#', p.getName()).charAt(i));
                    }
                }
                out.newLine();
            }
            out.newLine();
        }

        out.close();
    }

    static private String convertColorToName(Color c) {
        if (c.equals(Color.RED)) {
            return "RED";
        } else if (c.equals(Color.CYAN)) {
            return "CYAN";
        } else if (c.equals(Color.YELLOW)) {
            return "YELLOW";
        } else if (c.equals(Color.GREEN)) {
            return "GREEN";
        } else {
            return "UNKNOWN";
        }
    }
}
