package puzzlesolver.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Stores an orientation as a list of positions.
 *
 * @author Maikel Steneker (0753708)
 * @since 3-4-2011
 * 
 * Invariants:
 * @inv {\forall i,j; 0 <= i < j <= getCount(); !getPosition(i).equals(getPosition(j))}
 * @inv {getCount() >= 0}
 */
public class Orientation implements Iterable<Position> {
    //Internal representation:
    private List<Position> ls; //list of (relative) positions

    /**
     * Constructs Orientation class
     *
     * @pre      {@code true}
     * @modifies {@code ls}
     * @post     {@code ls = new ArrayList<Position>()}
     */
    public Orientation() {
        ls = new ArrayList<Position>();
    }

    /**
     * Constructs Orientation class
     *
     * @pre      {\forall i; 0 <= i < a.length;
     *                          {\forall j; 0 <= j < i; !a[i].equals(a[j])}}
     * @param    a  Array of positions representing an orientation
     * @modifies {@code ls}
     * @post     {@code ls = Arrays.asList(optimizeArray(a))}
     * @throws   NullPointerException  If {@code a==null}
     */
    public Orientation(Position[] a) throws NullPointerException {
        //check precondition
        if (a==null) {
            throw new NullPointerException("While constructing Orientation: "
                    + "a == null");
        }

        ls = Arrays.asList(a);
    }

    /**
     * Returns the number of positions
     *
     * @pre     {@code true}
     * @return  {@code ls.size()}
     */
    public int getCount() {
        return ls.size();
    }

    /**
     * Returns the Position on location {@code i}
     *
     * @param  i  The number of the Position to return
     * @return ls.get(i)
     * @throws IllegalArgumentException  If i < 0 || i >= getCount()
     */
    public Position getPosition(int i) throws IllegalArgumentException {
        if (i < 0) {
            throw new IllegalArgumentException("While executing getPosition(" +
                    i + "): " + i + " < 0");
        }
        if (i >= getCount()) {
            throw new IllegalArgumentException("While executing getPosition(" +
                    i + "): " + i + " < " + getCount() + ".");
        }
        return ls.get(i);
    }

    /**
     * Adds a position to the list
     *
     * @pre      {@code true}
     * @param    p  Position to add
     * @modifies ls
     * @post     {@code ls.add(p)
     * @throws   NullPointerException  If (p == null)
     */
    public void add(Position p) throws NullPointerException{
        //check precondition
        if (p==null) {
            throw new NullPointerException("While executing add(" + p.toString()
                    + "): p == null");
        }

        ls.add(p);
    }

    /**
     * Represents the Orientation as String
     *
     * @pre     {@code true}
     * @return  String representation of the Orientation
     * @throws  IllegalArgumentException  If ls.isEmpty()
     */
    @Override
    public String toString() {
        //check precondition
        if (ls.isEmpty()) {
            throw new IllegalArgumentException("While executing toString(): ls "
                    + "is empty.");
        }
        String MARK = "#";
        String EMPTY = ".";

        //determine bounding box
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < getCount(); i++) {
            if (getPosition(i).getRow() > maxRow) {
                maxRow = getPosition(i).getRow();
            }
            if (getPosition(i).getCol() > maxCol) {
                maxCol = getPosition(i).getCol();
            }
        }
        //create boolean array representing bounding box
        boolean[][] orientation = new boolean[maxRow+1][maxCol+1];
        
        //mark positions in the array
        for (int i = 0; i < getCount(); i++) {
            orientation[getPosition(i).getRow()][getPosition(i).getCol()] = true;
        }
        
        //convert the array to a string
        String s = "";
        for (int i = 0; i < orientation.length; i++) {
            for (int j = 0; j < orientation[i].length; j++) {
                s = s + (orientation[i][j] ? MARK : EMPTY);
            }
            if (i < orientation.length - 1) {
                s = s + "\n";
            }
        }

        return s;
    }

    /**
     * Returns foreach iterator over {@code ls}
     *
     * @return iterator
     */
    public Iterator<Position> iterator() {
        return new OrientationIterator();
    }

    /**
     * Inner iteration class
     */
    public class OrientationIterator implements Iterator<Position> {
        private int i; //index of next element
        private int sentinel; //index of the last element + 1

        OrientationIterator() {
            i = 0;
            sentinel = ls.size();
        }

        /**
         * Determines whether or not there is a next piece
         *
         * @return {@code (i < sentinel)}
         */
        public boolean hasNext() {
            return (i < sentinel);
        }

        /**
         * Returns the next element
         *
         * @return getPiece(i++)
         * @throws NoSuchElementException  If (i == sentinel)
         */
        public Position next() throws NoSuchElementException {
            if (i==sentinel) {
                throw new NoSuchElementException("OrientationIterator.next");
            }
            return getPosition(i++);
        }

        /**
         * Removes the last element returned by the iterator
         */
        public void remove() {
            throw new UnsupportedOperationException("Not supported.");
        }

    }
}
