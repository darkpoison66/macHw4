import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Arrays;



/**
 * Represents a Set of squares on a chess board.
 *
 * @version 1.0
 * @author mabdi3
 * @see Square
 */

public class SquareSet<Square> implements Set<Square> {

    private Square[] bArray;
    private int numOfElements;


    /**
     * Creates a SquareSet with an Object array as a backing store.
     */
    public SquareSet() {
        bArray = (Square[])new Object[10];
        numOfElements = 0;
    }

    @Override

    /**
     * Adds the square element to this square set if it is not already present
     *
     * @param e square to be added to this set
     * @return true if the set did not already contain the specified element
     * @throws InvalidSquareException if invalid square is passed into the
     * the method.
     */
    public boolean add(Square e) {
        for (Object x: bArray) {
            if (e.equals(x)) {
                return false;
            } else if (e == null) {
                return false;
            }
        }
        if (e.toString().length() !=  2) {
            throw new InvalidSquareException(e.toString());
        }
        char file = e.toString().charAt(0);
        char rank = e.toString().charAt(1);

        if (!validFile(file) || !validRank(rank)) {
            throw new InvalidSquareException(e.toString());
        }

        if (numOfElements == bArray.length) {
            Square[] copy = (Square[])new Object[bArray.length * 3];
            for (int x = 0; x < bArray.length; x++) {
                copy[x] = bArray[x];
            }
            copy[numOfElements] = e;
            numOfElements++;
            bArray = (Square[]) copy;
            return true;
        } else {
            bArray[numOfElements] = e;
            numOfElements++;
            return true;
        }
    }

    /**
     * Adds all of the elements in the specified collection
     * to this set if they're not already present.
     *
     * @param c collection cotaining squares to be added to this set
     * @return true if this set changed as a result of this call
     */
    public boolean addAll(Collection<? extends Square> c) {
        for (Square x: c) {
            if (x == null) {
                this.add(x);
            }
            if (x.toString().length() !=  2) {
                throw new InvalidSquareException(x.toString());
            }
            char file = x.toString().charAt(0);
            char rank = x.toString().charAt(1);
            if (!validFile(file) || !validRank(rank)) {
                throw new InvalidSquareException(x.toString());
            }
        }
        for (Square y: c) {
            this.add(y);
        }
        return true;
    }

    /**
     * Checks to see if file is between 'a' and 'h'
     * @param file the file in which the square presides
     * @return true if file is between 'a' and 'h', and false
     * otherwise
     */
    public static boolean validFile(char file) {
        boolean valid = false;
        for (int x = 97; x <= 104; x++) {
            if (file == x) {
                valid = true;
            }
        }
        return valid;
    }

    /**
     * Checks to see if rank is between '1' and '8'
     * @param rank the rank in which the square presides
     * @return true if rank is between '1' and '8' and false
     * otherwise
     */
    public static boolean validRank(char rank) {
        boolean valid = false;
        for (int x = 49; x <= 56; x++) {
            if (rank == x) {
                valid = true;
            }
        }
        return valid;
    }
    /**
     * @return true if this set contains the specified elemnent
     * @param o element whose presence in this set is to be tested
     */
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        for (Square x: bArray) {
            if (o != null && o.equals(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param c collection to be checked for containment in this set
     * @return true if this set contains all of the elements of the
     * specified collection
     */
    public boolean containsAll(Collection<?> c) {
        for (Square x: (Collection<Square>) c) {
            if (!(this.contains(x))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return the hash code value for this set, defined
     * by the sum of the hash codes of the elements in the set
     */
    public int hashCode() {
        int sum = 0;
        for (Square x:bArray) {
            if (x != null) {
                sum += x.hashCode();
            }
        }
        return sum;
    }

    /**
     * @return true if this set contains no elements
     */
    public boolean isEmpty() {
        return numOfElements == 0;
    }


    /**
     * @return the number of elements in this set
     */
    public int size() {
        return numOfElements;
    }

    /**
     * @return true if the specified object is also a set, the two sets have the
     * same size, and every member of the specified set is contained in this
     * set (or equivalently, every member of this set is contained
     * in the specified set)
     * @param o object to tested for equality
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Set)) {
            return false;
        }
        if ((((Set) o).size()) != this.size()) {
            return false;
        }
        return this.containsAll((Set) o);
    }

    /**
     * @return array containing all the elements in this set
     */
    public Object[] toArray() {
        Object[] copy = new Object[numOfElements];
        for (int x = 0; x < numOfElements; x++) {
            copy[x] = bArray[x];
        }
        return ((Square[]) copy);
    }


    /**
     * @return array containing all of the elements in this set;
     * the runtime type of the returned array is that of the specified array
     * @param a the array into wich the elements of this set are to be stored
     * @param <Square> runtime type of the array to contain the collection
     */
    public <T> T[] toArray(T[] a) {
        if (a.length < numOfElements) {
            Object[] b = new Object[numOfElements];
            for (int x = 0; x < numOfElements; x++) {
                b[x] = bArray[x];
            }
            return ((T[]) b);
        } else {
            for (int x = 0; x < numOfElements; x++) {
                a[x] = ((T) bArray[x]);
            }
            if(a.length > numOfElements) {
                a[numOfElements] = null;
            }
            return a;
        }
    }


    /**
     * Removes the specified element from this set if it is present
     *
     * @param o object to be removed if present
     * @return true if this set contained the specified element
     */
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
       /* for (Object x:bArray) {
            if ((o == null ? x == null : o.equals(x)) {
                Object store = x;
                numOfElements--;
                Object[] copy = new Object[numOfElements];
                for (int x = 0; i < numOfElements; i++) {

                }


            }
       }*/
       // System.out.println(Arrays.toString(bArray));
        boolean found = false;
        for (int x = 0; x < numOfElements; x++) {
            if (bArray[x] != null && bArray[x].equals(o)) {
                found = true;
            }
        }
        if (found && numOfElements >= 1) {
            Object[] copy = new Object[numOfElements];
            for (int i = 0; i < numOfElements; i++) {
                if (!(bArray[i].equals(o)) && bArray[i] != null) {
                    copy[i] = bArray[i];
                }
            }
           // System.out.println(Arrays.toString(copy));
            Object[] copy2 = new Object[numOfElements - 1];
            for (int j = 0, y = 0; j < numOfElements; j++) {
                if (copy[j] != null) {
                   // System.out.print(copy[j] + " ");
                    copy2[y++] = copy[j];
                    //System.out.print("   " + copy2[j] + "  ");
                }
            }
            numOfElements--;
            //System.out.println();
            //System.out.println(Arrays.toString(copy2));
            bArray = (Square[]) copy2;
            //System.out.println(Arrays.toString(bArray));
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return string representation of the set
     */
    public String toString() {
      String result = "";

      for (int index = 0; index < bArray.length; index++) {
          if (bArray[index] != null) {
              result = result + bArray[index].toString() + "\n";
          }
      }

      return result;
    }

    /**
     * @return iterator over the elements in this set
     * @see SquareIterator
     */
     public Iterator<Square> iterator() {
        return new SquareIterator();
     }


    /**
     * @throws UnsupportedOperationExcetion
     */
    public void clear() {
        throw new UnsupportedOperationException();
    }
    /**
     * @throws UnsupportedOperationExcetion
     * @param c collection to be removed
     * @return an UnsupportedOperationException
     */
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     * @throws UnsupportedOperationExcetion
     * @param c collection to be removed
     * @return an UnsupportedOperationException
     */
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     * Represents an iterator over a Square collection.
     *
     * @version 1.0
     * @author mabdi3
     * @see Square
     */
    private class SquareIterator implements Iterator<Square> {
        private int cursor = 0;

        /**
         * Creates a SquareIterator
         */
        public SquareIterator() {
            cursor = 0;
        }

       /**
        * @return true if the iteration has more elements
        */
        public boolean hasNext() {
            return cursor <= (numOfElements - 1);
        }
       /**
        *@return the next element in the iteration
        *@throws NoSuchElementException if the iteration has no more elements
        */
        public Square next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Square next = bArray[cursor];
            cursor++;
            return next;
        }
    }
}