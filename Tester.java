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
        squares2.add(new Square("a8"));
        squares2.add(new Square("a6"));
        System.out.println(squares.addAll(squares2));
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
        SquareSet<String> hm = new SquareSet<>();

    }
}