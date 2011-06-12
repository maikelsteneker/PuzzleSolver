package puzzlesolver.textio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import puzzlesolver.model.Box;
import puzzlesolver.model.Cell;
import puzzlesolver.model.CellState;

/**
 * Reads a box from a text file and initializes it
 *
 * @author Maikel Steneker (0753708)
 * @since 28-3-2011
 */
class ReadBoxFromFile {
    final static private Pattern row = Pattern.compile("[.#]+");

    public static Box readBoxFromFile(String filename)
            throws FileNotFoundException, IOException {
        File file = new File(filename);
        return readBoxFromFile(file);
    }

    public static Box readBoxFromFile(File file)
            throws FileNotFoundException, IOException {
        Scanner fileScanner = new Scanner(file);
        readName(fileScanner);
        Cell[][] cells = new Cell[readDimension(fileScanner)]
                                 [readDimension(fileScanner)];
        readCells(fileScanner, cells);
        return new Box(cells);
    }

    private static String readName(Scanner source) throws IOException {
        if (!source.hasNext()) {
            throw new IOException("File format invalid.");
        }
        return source.next();
    }

    private static int readDimension(Scanner source) throws IOException {
        if (!source.hasNextInt()) {
            throw new IOException("File format invalid.");
        }
        return source.nextInt();
    }

    private static void readCells(Scanner source, Cell[][] cells)
            throws IOException {
        for (int i = 0; i < cells.length; i++) {
            if (source.hasNext(row)) {
                cells[i] = readRow(source);
            } else {
                throw new IOException("File format invalid.");
            }
        }
    }

    private static Cell[] readRow(Scanner source) {
        String oneRow = source.next();
        Cell[] cells = new Cell[oneRow.length()];
        for (int i = 0; i < oneRow.length(); i++) {
            cells[i] = (oneRow.charAt(i) == '#') ?
                new Cell(CellState.blocked, null) :
                new Cell(CellState.empty, null);
        }
        return cells;
    }

}
