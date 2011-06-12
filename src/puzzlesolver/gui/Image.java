package puzzlesolver.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import puzzlesolver.model.*;

/**
 * Provides static methods to draw a puzzle and get specific coordinates in
 * the image.
 *
 * @author Maikel Stenker (0753708)
 * @since 12-6-2011
 */
public class Image {

    private final static int SQLENGTH = 25; //length in pixels of one cell
    private final static Color GRIDCOLOR = Color.BLACK; //color of the lines
    private final static Color EMPTYCELL = Color.WHITE; //color of an empty cell
    private final static Color BLOCKEDCELL = Color.GRAY; //color of both
    //a blocked cell and a piece with multiplicity 0

    /**
     * Draws a Cell on {@code g} with Color {@code c} on coordinates {@code x},
     * {@code y}
     *
     * @pre   g != null, c != null, x >= 0, y >= 0
     * @param x  x coordinate or the upper left corner of the cell to draw
     * @param y  y coordinate or the upper left corner of the cell to draw
     * @param g  Graphics to draw the cell on
     * @param c  Color of the cell
     * @modifies g
     * @post  the cell has been drawn
     */
    private static void drawCell(int x, int y, Graphics g, Color c) {
        g.setColor(c);
        g.fillRect(x * SQLENGTH, y * SQLENGTH, SQLENGTH, SQLENGTH);
        g.setColor(GRIDCOLOR);
        g.drawRect(x * SQLENGTH, y * SQLENGTH, SQLENGTH, SQLENGTH);
    }

    /**
     * Draws {@code puzzle} on {@code g}
     *
     * @pre puzzle != null
     * @param puzzle  the puzzle to draw
     * @param g  the graphics to draw {@code puzzle} on
     * @modifies g
     * @post {@code puzzle} has been drawn on {@code g}
     */
    public static void drawPuzzle(Puzzle puzzle, Graphics g) {
        if (g != null) {
            drawBagOfPieces(puzzle.getBagOfPieces(), g);
            drawBox(puzzle.getBox(), g, calcHeight(puzzle.getBagOfPieces()) + 1);
        }
    }

    /**
     * Draws {@code b} on {@code g} with vertical bagHeight {@code bagHeight}
     *
     * @pre   b != null, g != null, bagHeight >= 0,
     * @param b  the box to draw
     * @param g  the graphics to draw {@code b} on
     * @param offset  the vertical bagHeight of the box
     * @modifies g
     * @post {@code b} has been drawn on {@code g}
     */
    public static void drawBox(Box b, Graphics g, int offset) {
        for (int i = 0; i < b.getRowCount(); i++) {
            for (int j = 0; j < b.getColCount(); j++) {
                if (b.getCell(i, j).getState().equals(CellState.empty)) {
                    drawCell(j, i + offset, g, EMPTYCELL);
                } else if (b.getCell(i, j).getState().equals(CellState.blocked)) {
                    drawCell(j, i + offset, g, BLOCKEDCELL);
                } else {
                    drawCell(j, i + offset, g, b.getCell(i, j).getPlacement().getPiece().getColor());
                }
            }
        }
    }

    /**
     * Draws {@code piece} on {@code g} with the top left of the bounding box
     * on {@code point}.
     *
     * @pre piece != null, g != null, point != null, point.x > 0, point.y > 0
     * @param piece  the piece to draw
     * @param g  the graphics to draw {@code piece} on
     * @param point  the top left of the bounding box of {@code piece}
     * @modifies g
     * @post {@code piece} has been drawn on {@code g} at {@code point}
     */
    public static void drawPiece(Piece piece, Graphics g, Point point) {
        Color c = piece.getColor();
        int x;
        int y;
        for (Position p : piece.getOrientation()) {
            x = (p.getCol() * SQLENGTH) + point.x;
            y = (p.getRow() * SQLENGTH) + point.y;
            g.setColor(c);
            g.fillRect(x, y, SQLENGTH, SQLENGTH);
            g.setColor(GRIDCOLOR);
            g.drawRect(x, y, SQLENGTH, SQLENGTH);
        }
    }

    /**
     * Draws {@code bag} on {@code g}
     *
     * @pre   g != null
     * @param bag  the bag to draw
     * @param g  the graphics to draw {@code bag} on
     * @modifies g
     * @post {@code bag} has been drawn on {@code g}
     */
    private static void drawBagOfPieces(BagOfPieces bag, Graphics g) {
        Box vbag = generateVirtualBox(bag);

        //draw the box
        drawBox(vbag, g, 0);
        drawMultiplicity(bag, vbag, g);
    }

    /**
     * Generates a box that contains every piece in {@code bag}
     *
     * @pre bag != null
     * @param bag  the bag of pieces to place in the virtual box
     * @return a box containing every piece in {@code bag}
     */
    public static Box generateVirtualBox(BagOfPieces bag) {
        //determine the size of the virtual box
        int width = calcWidth(bag);
        int height = calcHeight(bag);

        //construct cells array
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell(CellState.empty, null);
            }
        }

        //construct virtual box
        Box vbox = new Box(cells);

        //place all pieces in the box
        placePieces(bag, vbox);
        return vbox;
    }

    /**
     * Places every piece in {@code bag} in {@code box}. Pieces with
     * multiplicity 0 block the cells they would normally cover.
     *
     * @pre box != null, bag != null
     * @param bag  the bag to get the pieces from
     * @param box  the box to place the pieces in
     * @modifies box
     * @post every piece in {@code bag} has been placed in {@code box}
     */
    private static void placePieces(BagOfPieces bag, Box box) {
        int offset = 0;

        for (Piece p : bag) {
            placePiece(p, box, offset,
                    (bag.getMultiplicity(bag.getIndex(p)) > 0));
            offset = offset + calcWidth(p) + 1;
        }
    }

    /**
     * Places {@code piece} in {@code box} on {@code new Position(0,offset)},
     * either in the normal way (if {@code active} or by blocking the cells the
     * piece covers.
     *
     * @pre piece != null, box != null, offset >= 0
     * @param piece  the piece to place
     * @param box  the box to place the piece in
     * @param offset  the column of the piece
     * @param active  if true, the piece is placed normally; if false, every
     *               cell the piece in its current orientation covers is blocked
     * @modifies box
     * @post {@code piece} has been placed or blocked
     */
    private static void placePiece(Piece piece, Box box, int offset, boolean active) {
        Position pos = new Position(0, offset);
        if (active) {
            box.placePiece(pos, piece);
        } else {
            box.blockPiece(pos, piece);
        }
    }

    /**
     * Returns the width of {@code bag} so it can store every piece in every
     * orientation.
     *
     * @pre   bag != null
     * @param bag  the bag to calculate the width of
     * @return (\sum Piece p; p in bag; calcWidth(p) + 1) - 1
     */
    private static int calcWidth(BagOfPieces bag) {
        int width = 0;
        for (Piece p : bag) {
            //the total width of the bag is the width of each piece + 1 cell
            //between every piece (minus 1 at the end)
            width = width + calcWidth(p) + 1;
        }
        
        //width now has an extra cell at the end that's not neccessary
        return width - 1;
    }

    /**
     * Returns maximum width of {@code piece}; the maximum of the width of
     * every orientation.
     *
     * @pre piece != null
     * @param piece  the piece to calculate the width of
     * @return (\max Orientation o; o in piece;
     *                              (\max Position p; p in o; p.getCol() + 1))
     */
    private static int calcWidth(Piece piece) {
        int width = 0;
        for (Orientation o : piece) {
            for (Position p : o) {
                if (p.getCol() + 1 > width) {
                    width = p.getCol() + 1;
                }
            }
        }
        return width;
    }

    /**
     * Returns the height of {@code bag}
     *
     * @pre bag != null
     * @param bag  the bag to calculate the height for
     * @return (\max Piece p; p in bag; calcHeight(p))
     */
    private static int calcHeight(BagOfPieces bag) {
        int height = 0;
        for (Piece p : bag) {
            if (calcHeight(p) > height) {
                height = calcHeight(p);
            }
        }
        return height;
    }

    /**
     * Calculates the height of {@code piece}
     *
     * @pre piece != null
     * @param piece  the piece to calculate the height for
     * @return (\max Orientation o; o in piece;
     *                                (\max Position p; p in o; p.getRow() + 1))
     */
    private static int calcHeight(Piece piece) {
        int height = 0;
        for (Orientation o : piece) {
            for (Position p : o) {
                if (p.getRow() + 1 > height) {
                    height = p.getRow() + 1;
                }
            }
        }
        return height;
    }

    /**
     * Calculates the width of {@code box}
     *
     * @pre box != null
     * @param box  the box to calculate the width of
     * @return box.getColCount()
     */
    private static int calcWidth(Box box) {
        return box.getColCount();
    }

    /**
     * Calculates the width of {@code box}
     *
     * @pre box != null
     * @param box  the box to calculate the width of
     * @return box.getRowCount()
     */
    private static int calcHeight(Box box) {
        return box.getRowCount();
    }

    /**
     * Determines whether {@code point} points to the box of {@code puzzle}
     *
     * @pre point != null, puzzle != null
     * @param puzzle  the puzzle {@code point} points to
     * @param point  the point that is used
     * @return (point.getX() < (calcWidth(puzzle.getBox()) * SQLENGTH) &&
     *          point.getY() > ((bagHeight + 1) * SQLENGTH) && point.getY()
     *          <(((calcHeight(puzzle.getBox())) * SQLENGTH)
     *          + ((bagHeight + 1) * SQLENGTH)))
     *          with bagHeight = calcHeight(puzzle.getBagOfPieces())
     */
    public static boolean pointsToBox(Puzzle puzzle, Point point) {
        if (puzzle == null) {
            return false;
        }
        //draw the bag on a null object to discover the bagHeight
        //int bagHeight = drawBagOfPieces(puzzle.getBagOfPieces(), null);
        int bagHeight = calcHeight(puzzle.getBagOfPieces());

        return (point.getX() < (calcWidth(puzzle.getBox()) * SQLENGTH) && point.getY() > ((bagHeight + 1) * SQLENGTH) && point.getY() < (((calcHeight(puzzle.getBox())) * SQLENGTH) + ((bagHeight + 1) * SQLENGTH)));
    }

    /**
     * Determines whether {@code point} points to the bag of {@code puzzle}
     *
     * @pre point != null, point.x >= 0, point.y >= 0
     * @param puzzle  the puzzle {@code point} points to
     * @param point  the point that is used
     * @return (puzzle != null && (point.getX()
     *      <= (calcWidth(puzzle.getBagOfPieces()) * SQLENGTH) && point.getY()
     *      <= (calcHeight(puzzle.getBagOfPieces())) * SQLENGTH))
     */
    public static boolean pointsToBag(Puzzle puzzle, Point point) {
        if (puzzle == null) {
            return false;
        }
        return (point.getX() <= (calcWidth(puzzle.getBagOfPieces()) * SQLENGTH) && point.getY() <= (calcHeight(puzzle.getBagOfPieces())) * SQLENGTH);
    }

    /**
     * Returns the position in the box where {@code point} points to.
     *
     * @pre {@code pointsToBag(puzzle, point) || (puzzle == null)}
     * @param puzzle  the puzzle {@code point} points to
     * @param point  the point that is used
     * @return {@code new Position(((point.y - 1) / SQLENGTH) - (bagHeight + 1),
     *                       ((point.x - 1) / SQLENGTH))}  If puzzle != null
     *          {@code null}  If (puzzle == null)
     */
    public static Position findPositionInBox(Puzzle puzzle, Point point) {
        if (puzzle == null) {
            return null;
        }
        //draw the bag on a null object to discover the bagHeight
        int bagHeight = calcHeight(puzzle.getBagOfPieces());

        int x = ((point.x - 1) / SQLENGTH);
        int y = ((point.y - 1) / SQLENGTH) - (bagHeight + 1);
        return new Position(y, x);
    }

    /**
     * Returns the position in the bag where {@code point} points to.
     *
     * @pre {@code pointsToBag(puzzle, point) || (puzzle == null)}
     * @param puzzle  the puzzle {@code point} points to
     * @param point  the point that is used
     * @return {@code new Position((point.y - 1) / SQLENGTH,
     *          (point.x - 1) / SQLENGTH)}  If puzzle != null
     *          {@code null}  If (puzzle == null)
     */
    public static Position findPositionInBag(Puzzle puzzle, Point point) {
        if (puzzle == null) {
            return null;
        }
        int x = (point.x - 1) / SQLENGTH;
        int y = (point.y - 1) / SQLENGTH;
        return new Position(y, x);
    }

    /**
     * Determines whether {@code point} points at a piece in {@code puzzle}
     *
     * @param puzzle  the puzzle {@code point} points to
     * @param point  the point that is used
     * @return {@code point} points to a piece in {@code puzzle}
     */
    public static boolean pointsAtPiece(Puzzle puzzle, Point point) {
        Position pos;
        Box space;
        if (pointsToBox(puzzle, point)) {
            pos = findPositionInBox(puzzle, point);
            space = puzzle.getBox();
        } else if (pointsToBag(puzzle, point)) {
            pos = findPositionInBag(puzzle, point);
            space = generateVirtualBox(puzzle.getBagOfPieces());
        } else {
            return false;
        }
        return (space.getCell(pos).getState() == CellState.occupied);
    }

    /**
     * Determines whether {@code point} points at {@code placement}
     *
     * @param puzzle  the puzzle {@code point} points to
     * @param point  the point that is used
     * @param placement  the placement to look for
     * @return {@code point} points to {@code placement} in {@code puzzle}
     */
    public static boolean pointsAtPiece(Puzzle puzzle, Point point, Placement placement) {
        Position pos;
        Box space;
        if (pointsToBox(puzzle, point)) {
            pos = findPositionInBox(puzzle, point);
            space = puzzle.getBox();
        } else if (pointsToBag(puzzle, point)) {
            pos = findPositionInBag(puzzle, point);
            space = generateVirtualBox(puzzle.getBagOfPieces());
        } else {
            return false;
        }
        return (space.getCell(pos).getPlacement() != null
                && placement.equals(space.getCell(pos).getPlacement()));
    }

    /**
     * Draws {@code piece} on {@code g} with the top left of the bounding box
     * on {@code point} with offset {@code offset}
     *
     * @pre piece != null, g != null, point != null, point.x > 0, point.y > 0
     * @param piece  the piece to draw
     * @param g  the graphics to draw {@code piece} on
     * @param point  the top left of the bounding box of {@code piece}
     * @param offset  the offset that is used to correct {@code point}
     * @modifies g, point
     * @post {@code piece} has been drawn on {@code g} at {@code point - offset}
     */
    public static void drawPiece(Piece piece, Graphics g, Point point, Point offset) {
        point.x = point.x - offset.x;
        point.y = point.y - offset.y;
        drawPiece(piece, g, point);
    }

    /**
     * Determine the offset of {@code point} in {@code puzzle}.
     *
     * @pre puzzle != null, point != null
     * @param puzzle  the puzzle {@code point} points to
     * @param point  the point to determine the offset for
     * @modifies point
     * @return point - anchorPoint
     * with anchorPoint = findAnchorPointInBox(puzzle, placement)
     *                                         If pointsToBox(puzzle, placement)
     *                  = findAnchorPointInBag(puzzle, placement)
     *                                         If pointsToBag(puzzle, placement)
     * with placement   = puzzle.getBox().getCell(
     *                          findPositionInBox(puzzle, point)).getPlacement()
     *                                      If pointsToBox(puzzle, placement)
     *                  = generateVirtualBox(puzzle.getBagOfPieces()).getCell(
     *                          findPositionInBag(puzzle, point)).getPlacement()
     *
     */
    public static Point determineOffset(Puzzle puzzle, Point point) {
        Position pos = null;
        Box space = null;
        Placement placement;
        Point anchorPoint = null;
        if (pointsToBox(puzzle, point)) {
            pos = findPositionInBox(puzzle, point);
            space = puzzle.getBox();
        } else if (pointsToBag(puzzle, point)) {
            pos = findPositionInBag(puzzle, point);
            space = generateVirtualBox(puzzle.getBagOfPieces());
        }

        placement = space.getCell(pos).getPlacement();
        if (pointsToBox(puzzle, point)) {
            anchorPoint = findAnchorPointInBox(puzzle, placement);
        } else if (pointsToBag(puzzle, point)) {
            anchorPoint = findAnchorPointInBag(puzzle.getBagOfPieces(), placement.getPiece());
        }
        point.x = point.x - anchorPoint.x;
        point.y = point.y - anchorPoint.y;
        return point;
    }

    /**
     * Finds the top-left Point of the bounding box of {@code placement}.
     *
     * @pre puzzle != null, placement != null, pointsToBox(puzzle, anchorPoint)
     *                with anchorPoint as the top-left point of the bounding box
     * @param puzzle  the puzzle to find {@code placement} in
     * @param placement  the placement to look for
     * @return the anchor point of {@code placement}
     */
    private static Point findAnchorPointInBox(Puzzle puzzle, Placement placement) {
        Point p = new Point();
        Box box = puzzle.getBox();
        int row = Integer.MAX_VALUE;
        int col = Integer.MAX_VALUE;
        for (int i = 0; i < box.getRowCount(); i++) {
            for (int j = 0; j < box.getColCount(); j++) {
                if (box.getCell(i, j).getState() == CellState.occupied && placement.equals(box.getCell(i, j).getPlacement())) {
                    if (i < row) {
                        row = i;
                    }
                    if (j < col) {
                        col = j;
                    }
                }
            }
        }
        p.x = (col * SQLENGTH);
        p.y = ((row + calcHeight(puzzle.getBagOfPieces()) + 1) * SQLENGTH);
        return p;
    }

    /**
     * Finds the top-left Point of the bounding box of {@code placement}.
     *
     * @pre puzzle != null, placement != null, pointsToBag(puzzle, anchorPoint)
     *                with anchorPoint as the top-left point of the bounding box
     * @param puzzle  the puzzle to find {@code placement} in
     * @param placement  the placement to look for
     * @return the anchor point of {@code placement}
     */
    private static Point findAnchorPointInBag(BagOfPieces bag, Piece piece) {
        int offset = 0;
        for (int i = 0; i < bag.getIndex(piece); i++) {
            offset += (calcWidth(bag.getPiece(i)) + 1);
        }
        return new Point(offset * SQLENGTH, 0);

    }

    /**
     * Draws the multiplicity of every piece on the anchor Position.
     *
     * @pre bag != null, vbox != null, g != null
     * @param bag  the bag containing the pieces
     * @param vbox  the box representation of {@code bag}
     * @param g  the graphics to draw the multiplicity on
     * @modifies g
     * @post the multiplicity for all pieces in {@code bag} has been drawn on
     *          the anchor position of that piece on {@code g}
     */
    private static void drawMultiplicity(BagOfPieces bag, Box vbox, Graphics g) {
        for (Piece p : bag) {
            for (Cell c : vbox) {
                if (c.getPlacement() != null && p.equals(c.getPlacement().getPiece())) {
                    g.drawString("" + bag.getMultiplicity(bag.getIndex(p)), findAnchorPointInBag(bag, p).x + SQLENGTH / 2, findAnchorPointInBag(bag, p).y + 3 * SQLENGTH / 4);
                }
            }
        }
    }
}
