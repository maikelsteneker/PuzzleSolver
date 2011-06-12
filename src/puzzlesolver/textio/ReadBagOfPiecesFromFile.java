package puzzlesolver.textio;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import puzzlesolver.model.BagOfPieces;
import puzzlesolver.model.Orientation;
import puzzlesolver.model.Piece;
import puzzlesolver.model.Position;

/**
 * Reads a bag of pieces from a text file and initializes it
 *
 * @author Maikel Steneker (0753708)
 * @since 28-3-2011
 */
class ReadBagOfPiecesFromFile {

    final static private Pattern pieceName = Pattern.compile(".");
    final static private Pattern minLine = Pattern.compile("-+");
    final static private Pattern eqLine = Pattern.compile("=+");

    public static BagOfPieces readBagOfPiecesFromFile(String filename)
            throws FileNotFoundException, IOException {
        File file = new File(filename);
        return readBagOfPiecesFromFile(file);
    }

    public static BagOfPieces readBagOfPiecesFromFile(File file)
            throws FileNotFoundException, IOException {
        Scanner fileScanner = new Scanner(file);
        Pattern row; //the row pattern for the current piece
        List<Piece> pieces = new ArrayList<Piece>(); //list of pieces
        List<Integer> multiplicityList =
                new ArrayList<Integer>(); //list of multiplicities
        int[] multiplicity; //array of multiplicities
        Orientation[] orientations; //list of orientations of the current piece

        readName(fileScanner); //read the bags name
        do { //read at least one piece
            readEqLine(fileScanner); //read the line of '='-characters
            Object[] pieceBase = readPieceBase(fileScanner); //read pieceBase
            row = Pattern.compile("[." + pieceBase[0] + "]+");
            orientations = //orientations stores the orientations of one piece
                    readOrientations(fileScanner, row); //read the orientations
            pieces.add(new Piece(pieceBase[0].toString().charAt(0),
                    (Color) pieceBase[1], orientations)); //add the piece
            multiplicityList.add((Integer) pieceBase[2]); //add its multiplicity
        } while (fileScanner.hasNext());
        multiplicity = convertIntegerListToArray(multiplicityList);
        return new BagOfPieces(convertPieceListToArray(pieces), multiplicity);
    }

    private static Piece[] convertPieceListToArray(List<Piece> list) {
        Piece[] array = new Piece[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    private static String readName(Scanner source) throws IOException {
        if (!source.hasNext()) {
            throw new IOException("File format invalid.");
        }
        return source.next();
    }

    private static void readEqLine(Scanner source) throws IOException {
        do {
            //ignore next token
            if (!(source.next().charAt(0) == '=')) {
                throw new IOException("File format invalid.");
            }
        } while (source.hasNext("="));
    }

    private static Object[] readPieceBase(Scanner source) throws IOException {
        return new Object[]{readPieceName(source),
                    readPieceColor(source),
                    readPieceMultiplicity(source)};
    }

    private static char readPieceName(Scanner source) throws IOException {
        if (!source.hasNext(pieceName)) {
            throw new IOException("File format invalid.");
        }
        return source.next().charAt(0);
    }

    private static Color readPieceColor(Scanner source) throws IOException {
        if (!source.hasNext()) {
            throw new IOException("File format invalid.");
        }
        return ColorConverter.readColor(source);
    }

    private static int readPieceMultiplicity(Scanner source)
            throws IOException {
        if (!source.hasNextInt()) {
            throw new IOException("File format invalid.");
        }
        return source.nextInt();
    }

    private static Orientation[] readOrientations(Scanner source, Pattern row)
            throws IOException {
        List<Orientation> orientations = new ArrayList<Orientation>();
        do {
            orientations.add(readOrientation(source, row));
        } while (!source.hasNext(eqLine) && source.hasNext());
        return convertOrientationListToArray(orientations);
    }

    private static Orientation[] convertOrientationListToArray(
            List<Orientation> list) {
        Orientation[] array = new Orientation[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    private static Orientation readOrientation(Scanner source, Pattern row)
            throws IOException {
        readMinLine(source);
        Position[] positions = new Position[0];
        int currentRow = 0;
        do {
            positions = addArrays(positions, readRow(source, currentRow));
            currentRow++;
        } while (source.hasNext(row));
        return new Orientation(positions);
    }

    private static void readMinLine(Scanner source) throws IOException {
        do {
            if (!source.hasNext(minLine)) {
                throw new IOException("File format invalid.");
            }

            //ignore next token
            source.next();
        } while (source.hasNext(minLine));
    }

    private static int[] convertIntegerListToArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).intValue();
        }
        return array;
    }

    private static Position[] readRow(Scanner source, int rowNumber) {
        String rowString = source.next(); //String representing the current row
        List<Position> positions = new ArrayList<Position>();

        for (int i = 0; i < rowString.length(); i++) { //for every character
            switch (rowString.charAt(i)) {
                case '.':
                    break;
                default:
                    positions.add(new Position(rowNumber, i));
                    break;
            }
        }
        return convertPositionListToArray(positions);
    }

    private static Position[] convertPositionListToArray(List<Position> list) {
        Position[] array = new Position[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    private static Position[] addArrays(Position[] a, Position[] b) {
        Position[] sum = new Position[a.length + b.length];
        System.arraycopy(a, 0, sum, 0, a.length);
        System.arraycopy(b, 0, sum, a.length, b.length);
        return sum;
    }
}
