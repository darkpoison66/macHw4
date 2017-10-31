import java.util.*;
public class Tester {

    static class EvilSquare extends Square {
        private char r;
        private char f;

        public EvilSquare(char rank, char file) {
            super('a', '1');
            r = rank;
            f = file;
        }

        @Override
        public char getRank() {
            return r;
        }

        @Override
        public char getFile() {
            return f;
        }

        @Override
        public String toString() {
            return "" + f + r;
        }
    }

    public static void main(String[] args) throws InvalidSquareException {
        SquareSet<Square> squares = new SquareSet<>();
        squares.add(new Square("a4"));
        squares.add(new Square("a5"));
        squares.add(new Square("a5"));
        squares.add(new Square("a6"));
        squares.add(new Square("a8"));
        squares.add(new Square("a3"));
        SquareSet<Square> squares2 = new SquareSet<>();
        squares2.add(new Square("a5"));
        squares2.add(new Square("a6"));
        squares2.add(new Square("a4"));
        //squares.addAll(squares2);
        SquareSet<Square> squares3 = new SquareSet<>();
        // System.out.println(squares);
        // System.out.println(squares.contains(new Square("a8")));
        // System.out.println(squares.contains(new Square("a7")));
        //  System.out.println(squares.containsAll(squares2));
        //  System.out.println(squares.equals(squares2));
        // System.out.println(squares2.isEmpty());
        // System.out.println(squares3.isEmpty());
        // System.out.println(squares.size());
        //System.out.println(squares2.size());
        //Object[] array = squares.toArray();
        //SquareSet<Square> set = new SquareSet<>();
        //System.out.println(squares);
        System.out.println();
        System.out.println("[Now testing iterator() method]");
        System.out.println("Creating new set {e2, f7, a6}.");
        SquareSet<Square> set = new SquareSet<>();
        set.add(new Square("e2"));
        set.add(new Square("f7"));
        set.add(new Square("a6"));
        boolean passedIteratorTests = true;

        try {
            if (set.iterator() == null) {
                System.out.println("Iterator is null.");
                passedIteratorTests = false;
            }
            Iterator<Square> iterator = set.iterator();
            if (!iterator.hasNext()) {
                System.out.println(
                        "Iterator says it does not have a next element when it"
                                + " was just created.");
                passedIteratorTests = false;
            }
            if (iterator.next() == null) {
                System.out.println(
                        "Iterator gave a null element when it was just created.");
                passedIteratorTests = false;
            }
            if (!iterator.next().equals(new Square("f7"))) {
                System.out.println(
                        "Iterator didn't return the expected second element, f7.");
                passedIteratorTests = false;
            }
            int c = 0;
                for (Square s : set) {
                    if (c == 0 && !s.equals(new Square("e2"))) {
                        System.out.println(
                        "Iterator didn't return the expected first element, "
                        + "e2.");
                        passedIteratorTests = false;
                    }
                    if (c == 1 && !s.equals(new Square("f7"))) {
                        System.out.println(
                        "Iterator didn't return the expected second element, "
                        + "f7.");
                        passedIteratorTests = false;
                    }
                    if (c == 2 && !s.equals(new Square("a6"))) {
                        System.out.println(
                        "Iterator didn't return the expected third element, "
                        + "a6.");
                        passedIteratorTests = false;
                    }
                    c++;
                }
            iterator.next();
            try {
                if (iterator.hasNext()) {
                    System.out.println(
                            "Iterator says it has a next element but shouldn't.");
                    passedIteratorTests = false;
                }
                iterator.next();
                System.out.println("Iterator did not throw exception when"
                        + " no next element existed but next() was called.");
                passedIteratorTests = false;
            } catch (NoSuchElementException nsee) {
            } catch (Exception e) {
                System.out.println("[WARNING] Iterator threw exception "
                        + "when no next element existed, but wasn't expected "
                        + "NoSuchElementException.");
            }
        } catch(NullPointerException n) {
            System.out.println(
                    "[WARNING] NullPointerException thrown when testing"
                            + " iterator().");
            passedIteratorTests = false;
        }
        if (passedIteratorTests) {
            System.out.println("Set passed all iterator() tests.");
        } else{
            System.out.println("Set failed one or more iterator() tests.");
        }



    }
}