package puzzlesolver.model;

import java.awt.Color;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Stores a piece
 *
 * @author Maikel Steneker (0753708)
 * @since 3-4-2011
 *
 * Invariants:
 * @inv orientations != null
 * @inv c != null
 * @inv {\forall i,j; 0 <= i < j <= orientations.length; orientations[i]!=orientations[j]}
 */
public class Piece implements Iterable<Orientation>{
    //Internal representation:
    private char name; //name of the piece, represented by a single character
    private Color c; //color of the piece
    private Orientation[] orientations; //array containing possible orientations of the piece
    private int orientation; //the current orientation of the piece

    /**
     * Constructs Piece class
     *
     * @pre      {@code true}
     * @param    name  name of the piece
     * @param    c  color of the piece
     * @param    o  array containing orientations
     * @modifies name
     * @modifies c
     * @modifies orientations
     * @post     {@code this.name = name}
     * @post     {@code this.c = c}
     * @post     {@code this.orientations = o}
     * @throws   NullPointerException  If {@code (c == null || (o == null)}}
     */
    public Piece(char name, Color c, Orientation[] o)
            throws NullPointerException {
        //check precondition
        if (c == null) {
            throw new NullPointerException("While constructing Piece: "
                    + "c == null");
        }
        if (o == null) {
            throw new NullPointerException("While constructing Piece: "
                    + "o == null");
        }

        this.name = name;
        this.c = c;
        this.orientations = o;
    }

    /**
     * Returns the color of the piece
     *
     * @pre     {@code true}
     * @return  {@code c}
     */
    public Color getColor() {
        return c;
    }

    /**
     * Sets a new color for the piece
     *
     * @pre      {@code true}
     * @param    c  Color to set
     * @modifies c
     * @post     {@code this.c = c}
     * @throws   NullPointerException  If {@code c == null}
     */
    public void setColor(Color c) throws NullPointerException {
        //check precondition
        if (c == null) {
            throw new NullPointerException("While executing setColor(c): c == "
                    + "null");
        }

        this.c = c;
    }

    /**
     * Returns the name of the piece
     *
     * @pre     {@code true}
     * @return  {@code name}
     */
    public char getName() {
        return name;
    }

    /**
     * Sets a new name for the piece
     *
     * @pre      {@code true}
     * @param    name  Name to set
     * @modifies name
     * @post     {@code this.name = name}
     */
    public void setName(char name) {
        this.name = name;
    }

    /**
     * Turns the piece by 90 degrees or flips it
     *
     * @pre      {@code true}
     * @modifies orientation
     * @post     {@code orientation = (orientation + 1) % orientations.length}
     */
    public void turn() {
        orientation = (orientation + 1) % orientations.length;
    }

    /**
     * Turns the piece by 90 degrees or flips it {@code t} times
     *
     * @pre      {@code true}
     * @param    t  number of times to turn
     * @modifies orientation
     * @post     {@code orientation = (orientation + t) % orientations.length}
     */
    public void turn(int t) {
        orientation = (orientation + t) % orientations.length;
    }

    /**
     * Returns the current orientation
     *
     * @pre     {@code true}
     * @return  {@code orientations[orientation]}
     */
    public Orientation getOrientation() {
        return orientations[this.orientation];
    }

    /**
     * Returns a specified orientation
     *
     * @pre     {@code 0 <= index < numberOfOrientations()}
     * @param   index  index of the element to return
     * @return  {@code orientations[index]}
     */
    public Orientation getOrientation(int index) {
        return orientations[index];
    }
    
    /**
     * Returns the number of orientations stored in this piece
     * 
     * @return {@code orientations.length}
     */
    public int numberOfOrientations() {
        return orientations.length;
    }

    /**
     * Determines if {@code p} is equal to {@code this}
     *
     * @pre    {@code p} and {@code this} contain all orientations
     * @param  p  the piece to compare this to
     * @return {\forall i; 0 <= i < p.numberOfOrientations;
     *          {\exists j; 0 <= j < this.numberOfOrientations;
     *           p.getOrientation(i).toString().equals(
     *             this.getOrientation(j).toString)}}
     */
    public boolean equals(Piece p) {
        boolean found = false; //set to true if an orientation has been found in p

        for (Orientation i: this) {
            found = false; //an orientation has not been found yet
            for (Orientation j: p) {
                //if no correct orientation has been found yet, look for one
                if (!found && i.toString().equals(j.toString())) {
                    //an orientation has been found
                    found = true; //set found to true
                }
            }
            if (!found) {
                //no orientation was found; the pieces are not equal
                return false;
            }
        }
        //every orientation was found
        //return true if the name and the color is the same
        return ((p.getName()==this.getName())
                && p.getColor().equals(this.getColor()));
    }

    /**
     * Returns foreach iterator over {@code orientations}
     *
     * @return iterator
     */
    public Iterator<Orientation> iterator() {
        return new PieceIterator();
    }

    /**
     * Inner iteration class
     */
    public class PieceIterator implements Iterator<Orientation> {
        private int i; //index of next element
        private int sentinel; //index of the last element + 1

        PieceIterator() {
            i = 0;
            sentinel = numberOfOrientations();
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
        public Orientation next() throws NoSuchElementException{
            if (i==sentinel) {
                throw new NoSuchElementException("PieceIterator.next");
            }
            return getOrientation(i++);
        }

        /**
         * Removes the last element returned by the iterator
         */
        public void remove() {
            throw new UnsupportedOperationException("Not supported.");
        }

    }
}
