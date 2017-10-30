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
        /*SquareSet<Square> squares = new SquareSet<>();
        squares.add(new Square("a4"));
        squares.add(new Square("a5"));
        squares.add(new Square("a5"));
        squares.add(new Square("a6"));*/
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

        try {
            System.out.println();
            System.out.println("[Now rudimentary requirements]");
            // Make sure it instantiates.
            SquareSet set = new SquareSet<>();
            System.out.println("SquareSet successfully created.");
            // Check instanceof.
            if (set instanceof Set) {
                System.out.println("SquareSet passed instanceof Set.");
            } else {
                System.out.println("SquareSet failed instanceof Set.");
            }

            System.out.println();
            System.out.println("[Now testing add method]");
            // Make sure it adds all valid squares.
            boolean failedToAddAll = false;
            for (char r = '1'; r < '9'; r++) {
                for (char f = 'a'; f < 'i'; f++) {
                    if (!set.add(new Square(f, r))) {
                        System.out.println("Square failed to add: " + f + r);
                        failedToAddAll = true;
                    }
                }
            }
            if (!failedToAddAll) {
                System.out.println("All squares added successfully.");
            }
            // Make sure it doesn't add duplicates
            boolean failedToAddAllDuplicates = true;
            for (char r = '1'; r < '9'; r++) {
                for (char f = 'a'; f < 'i'; f++) {
                    if (set.add(new Square(f, r))) {
                        System.out.println(
                                "Duplicate square was added: " + f + r);
                        failedToAddAllDuplicates = false;
                    }
                }
            }
            if (failedToAddAllDuplicates) {
                System.out.println("No duplicates were added.");
            }
            // Try adding a null.
            boolean nullPointerExceptionWhenNullAdded = false;
            try {
                if (set.add(null)) {
                    System.out.println(
                            "[WARNING] Null adds to set. Assignment not "
                                    + "clear about if this is O.K.");
                } else {
                    System.out.println(
                            "Null not added to set and no exception thrown.");
                }
            } catch (InvalidSquareException e) {
                System.out.println(
                        "Null not added to set and exception thrown.");
            } catch (NullPointerException n) {
                System.out.println(
                        "[ERROR] NullPointerException thrown adding null to set!");
                nullPointerExceptionWhenNullAdded = true;
            }

            // Test contains
            System.out.println();
            System.out.println("[Now testing contains method]");
            System.out.println("Creating new set {a1, b2, e4, h5, c8}.");
            set = new SquareSet<>();
            set.add(new Square("a1"));
            set.add(new Square("b2"));
            set.add(new Square("e4"));
            set.add(new Square("h5"));
            set.add(new Square("c8"));
            boolean passedContains = true;
            if (!set.contains(new Square("a1"))) {
                System.out.println(
                        "Set should contain a1, but says it doesn't.");
                passedContains = false;
            }
            if (!set.contains(new Square("b2"))) {
                System.out.println(
                        "Set should contain b2, but says it doesn't.");
                passedContains = false;
            }
            if (!set.contains(new Square("e4"))) {
                System.out.println(
                        "Set should contain e4, but says it doesn't.");
                passedContains = false;
            }
            if (!set.contains(new Square("h5"))) {
                System.out.println(
                        "Set should contain h5, but says it doesn't.");
                passedContains = false;
            }
            if (!set.contains(new Square("c8"))) {
                System.out.println(
                        "Set should contain c8, but says it doesn't.");
                passedContains = false;
            }
            if (set.contains(new Square("a2"))) {
                System.out.println(
                        "Set shouldn't contain a2, but says it does.");
                passedContains = false;
            }
            if (set.contains(new Square("b1"))) {
                System.out.println(
                        "Set shouldn't contain b1, but says it does.");
                passedContains = false;
            }
            if (set.contains(new Square("h8"))) {
                System.out.println(
                        "Set shouldn't contain h8, but says it does.");
                passedContains = false;
            }
            if (set.contains(new Square("e7"))) {
                System.out.println(
                        "Set shouldn't contain e7, but says it does.");
                passedContains = false;
            }
            if (passedContains) {
                System.out.println("Set passed all contains tests.");
            } else {
                System.out.println("Set failed one or more contains test.");
            }

            // Test containsAll
            System.out.println();
            System.out.println("[Now testing containsAll method]");
            System.out.println("Creating new set {a1, b2, e4, h5, c8}.");
            set = new SquareSet<>();
            set.add(new Square("a1"));
            set.add(new Square("b2"));
            set.add(new Square("e4"));
            set.add(new Square("h5"));
            set.add(new Square("c8"));
            boolean passedContainsAll = true;

            Set<Square> set1 = new HashSet<Square>();
            set1.add(new Square("a1"));
            set1.add(new Square("b2"));
            set1.add(new Square("e4"));
            set1.add(new Square("h5"));
            set1.add(new Square("c8"));
            if (!set.containsAll(set1)) {
                System.out.println(
                        "Set says it does not contain {a1, b2, e4, h5, c8}.");
                passedContainsAll = false;
            }

            Collection<Square> col1 = new ArrayList<Square>();
            col1.add(new Square("a1"));
            col1.add(new Square("b2"));
            col1.add(new Square("a1"));
            col1.add(new Square("e4"));
            col1.add(new Square("h5"));
            col1.add(new Square("c8"));
            if (!set.containsAll(col1)) {
                System.out.println(
                        "Set says it does not contain {a1, b2, a1, e4, h5, c8}.");
                System.out.println(
                        "\tAlthough Collection contains duplicates, "
                                + "Set still contains it all");
                passedContainsAll = false;
            }

            Set<Square> set2 = new HashSet<Square>();
            set2.add(new Square("b2"));
            set2.add(new Square("e4"));
            set2.add(new Square("h5"));
            if (!set.containsAll(set2)) {
                System.out.println(
                        "Set says it does not contain {b2, e4, h5}.");
                passedContainsAll = false;
            }

            Set<Square> set3 = new HashSet<Square>();
            set3.add(new Square("a1"));
            set3.add(new Square("b2"));
            set3.add(new Square("e4"));
            set3.add(new Square("h5"));
            set3.add(new Square("c8"));
            set3.add(new Square("a2"));
            if (set.containsAll(set3)) {
                System.out.println(
                        "Set says it does not contain {a1, b2, a1, e4, h5, c8, a2}.");
                passedContainsAll = false;
            }

            if (passedContainsAll) {
                System.out.println("Set passed all containsAll tests.");
            } else {
                System.out.println(
                        "Set failed one or more containsAll tests.");
            }

            // Check equals method
            System.out.println();
            System.out.println("[Now testing equals method]");
            System.out.println("Creating new set {a1, f5, g8}.");
            boolean passedEquals = true;
            set = new SquareSet<>();
            set.add(new Square("a1"));
            set.add(new Square("f5"));
            set.add(new Square("g8"));

            SquareSet sset2 = new SquareSet();
            sset2.add(new Square("g8"));
            sset2.add(new Square("a1"));
            sset2.add(new Square("f5"));

            SquareSet sset3 = new SquareSet();
            sset3.add(new Square("g8"));
            sset3.add(new Square("a2"));
            sset3.add(new Square("f5"));

            SquareSet set4 = new SquareSet();
            set4.add(new Square("g8"));
            set4.add(new Square("f5"));

            Set<Square> set5 = new HashSet<Square>();
            set5.add(new Square("a1"));
            set5.add(new Square("f5"));
            set5.add(new Square("g8"));

            SquareSet<Square> set6 = new SquareSet<>();
            set6.add(new Square("a1"));
            set6.add(new Square("f5"));
            set6.add(new Square("g8"));
            set6.add(new Square("g7"));
            try {
                if (set.equals(null)) {
                    System.out.println("Set says it equals null.");
                    passedEquals = false;
                }
            } catch (NullPointerException n) {
                System.out.println(
                        "[WARNING] Caught unexpected NullPointerException "
                                + "checking .equals(null)");
                passedEquals = false;
            }
            try {
                if (set.equals("{a1, f5, g8}")) {
                    System.out.println(
                            "Set says it equals the String {a1, f5, g8}.");
                    passedEquals = false;
                }
            } catch (Exception n) {
                System.out.println(
                        "[WARNING] Caught unexpected Exception "
                                + "checking .equals(String)");
                passedEquals = false;
            }

            if (!set.equals(set)) {
                System.out.println("Set says it doesn't equal itself.");
                passedEquals = false;
            }
            if (!set.equals(sset2)) {
                System.out.println(
                        "Set says it doesn't equal the set {g8, a1, f5}.");
                System.out.println("\tOrder doesn't matter.");
                passedEquals = false;
            }
            if (set.equals(sset3)) {
                System.out.println(
                        "Set says it does equal the set {g8, a2, f5}.");
                passedEquals = false;
            }
            if (set.equals(set4)) {
                System.out.println(
                        "Set says it does equal the set {g8, f5}.");
                passedEquals = false;
            }
            if (!set.equals(set5)) {
                System.out.println(
                        "SquareSet says it doesn't equal the normal Set {a1, f5, g8}."
                );
                System.out.println("Equals should work across implementations"
                        + " of Set.");
                passedEquals = false;
            }
            if (set.equals(set6)) {
                System.out.println(
                        "Set says it does equal the Set {a1, f5, g8, g7}.");
                passedEquals = false;
            }
            if (passedEquals) {
                System.out.println("Set passed all equals() tests.");
            } else {
                System.out.println("Set failed one or more equals() tests.");
            }

            // Check hashCode() method
            System.out.println();
            System.out.println("[Now testing hashCode() method]");
            System.out.println("Creating new set {a2, b1, g8}.");
            set = new SquareSet<>();
            Square s1 = new Square("a2");
            Square s2 = new Square("b1");
            Square s3 = new Square("g8");
            set.add(s1);
            set.add(s2);
            set.add(s3);
            boolean passedHashCode = true;
            if (s1.hashCode() == s2.hashCode()) {
                System.out.println(
                        "a2 hashCode equals b1 hashCode.");
                passedHashCode = false;
            }
            if (set.hashCode() != (s1.hashCode() + s2.hashCode()
                    + s3.hashCode())) {
                System.out.println(
                        "Hash code did not meet value required by Set.");
                passedHashCode = false;
            }
            if (passedHashCode) {
                System.out.println("Set passed all hashCode() tests.");
            } else {
                System.out.println("Set failed one or more hashCode() tests.");
            }

            // Check isEmpty() method
            System.out.println();
            System.out.println("[Now testing isEmpty() method]");
            set = new SquareSet<>();
            boolean passedIsEmptyTests = true;
            if (!set.isEmpty()) {
                System.out.println("Newly created set says it isn't empty.");
                passedIsEmptyTests = false;
            }
            set.add(new Square('a', '1'));
            if (set.isEmpty()) {
                System.out.println("Set with a1 says it is empty.");
                passedIsEmptyTests = false;
            }
            if (passedIsEmptyTests) {
                System.out.println("Set passed all isEmpty() tests.");
            } else {
                System.out.println("Set failed one or more isEmpty() tests.");
            }

            // Check iterator() method
            System.out.println();
            System.out.println("[Now testing iterator() method]");
            System.out.println("Creating new set {e2, f7, a6}.");
            set = new SquareSet<>();
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
                /*for (Square s : set) {
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
                }*/
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
            } catch (NullPointerException n) {
                System.out.println(
                        "[WARNING] NullPointerException thrown when testing"
                                + " iterator().");
                passedIteratorTests = false;
            }
            if (passedIteratorTests) {
                System.out.println("Set passed all iterator() tests.");
            } else {
                System.out.println("Set failed one or more iterator() tests.");
            }

            // Check size() method
            System.out.println();
            System.out.println("[Now testing size() method]");
            System.out.println("Creating new set {a1, a2, a3}.");
            set = new SquareSet<>();
            set.add(new Square("a1"));
            set.add(new Square("a2"));
            set.add(new Square("a3"));
            boolean passedSizeTests = true;
            if (set.size() != 3) {
                System.out.println("Set says its size is not 3.");
                passedSizeTests = false;
            }
            set.add(new Square("a4"));
            if (set.size() != 4) {
                System.out.println("Set size is not 4 after adding new"
                        + " element.");
                passedSizeTests = false;
            }
            set.add(new Square("a1"));
            if (set.size() != 4) {
                System.out.println("Set size is not 4 after attempting to add"
                        + " duplicate element. (Changed to " + set.size() + ").");
                passedSizeTests = false;
            }
            if (passedSizeTests) {
                System.out.println("Set passed all size() tests.");
            } else {
                System.out.println("Set failed one or more size() tests.");
            }

            // Check toArray() method
            System.out.println();
            System.out.println("[Now testing toArray() method]");
            System.out.println("Creating new set {a1, a2, a3}.");
            set = new SquareSet<>();
            set.add(new Square("a1"));
            set.add(new Square("a2"));
            set.add(new Square("a3"));
            boolean passedToArrayTests = true;
            try {
                Object[] setObjectArray = set.toArray();
                if (setObjectArray.length != set.size()) {
                    System.out.println("Object[] not size of set.");
                    passedToArrayTests = false;
                }
                int f = 1;
                for (Object o : setObjectArray) {
                    if (!(o instanceof Square)) {
                        System.out.println("Object from array not instanceof"
                                + " Square.");
                        passedToArrayTests = false;
                        continue;
                    }
                    if (!((Square) o).equals(new Square("a" + f))) {
                        System.out.println(f + "th square in array did not "
                                + "match expected a" + f + ". (Got " + o + ").");
                        passedToArrayTests = false;
                    }
                    f++;
                }
                set.add(new Square("h8"));
                if (setObjectArray.length == 4) {
                    System.out.println("Added element to set and Object[] "
                            + "changed; Java contract stipulates that you cannot just "
                            + "pass the set's backing array as a reference.");
                    passedToArrayTests = false;
                }
            } catch (ClassCastException cce) {
                System.out.println("Caught class cast exception testing "
                        + "toArray():");
                cce.printStackTrace();
                passedToArrayTests = false;
            }
            if (passedToArrayTests) {
                System.out.println("Set passed all toArray() tests.");
            } else {
                System.out.println("Set failed one or more toArray() tests.");
            }

            // Check toArray(T[] a) method
            System.out.println();
            System.out.println("[Now testing toArray(T[] a) method]");
            System.out.println("Creating new set {a1, a2, a3}.");
            set = new SquareSet<>();
            set.add(new Square("a1"));
            set.add(new Square("a2"));
            set.add(new Square("a3"));
            boolean passedGenericToArrayTests = true;
            try {
                Square[] squares = new Square[4];
                set.toArray(squares);
                if (!squares[0].equals(new Square("a1"))
                        || !squares[1].equals(new Square("a2"))
                        || !squares[2].equals(new Square("a3"))) {
                    System.out.println("Array contents in Square[] do not "
                            + "match the set contents (could be ordering).");
                    System.out.println(Arrays.toString(squares));
                    passedGenericToArrayTests = false;
                }
                Object[] objArray = new Object[]{null, null, null, "Hi"};
                set.toArray(objArray);
                if (!objArray[0].equals(new Square("a1"))
                        || !objArray[1].equals(new Square("a2"))
                        || !objArray[2].equals(new Square("a3"))
                        || objArray[3] != null) {
                    System.out.println("Array contents in Object[] do not "
                            + "match the set contents (could be ordering). Entry after"
                            + " set contents end should always be set to null.");
                    System.out.println(Arrays.toString(objArray));
                    passedGenericToArrayTests = false;
                }
                System.out.println("Testing w/ \"2nd Object[]\" that is too "
                        + "small to dump into.");
                objArray = new Object[]{"Hello!"};
                Object[] objArray2 = set.toArray(objArray);
                if (!objArray[0].equals("Hello!")) {
                    System.out.println("Given Object[] was too small, but the "
                            + "contents were still altered. This should not occur.");
                    passedGenericToArrayTests = false;
                }
                if (objArray2.length != 3) {
                    System.out.println("2nd Object[] length does not match "
                            + "set size.");
                }
                if (!objArray2[0].equals(new Square("a1"))
                        || !objArray2[1].equals(new Square("a2"))
                        || !objArray2[2].equals(new Square("a3"))) {
                    System.out.println("Array contents in 2nd Object[] do not "
                            + "match the set contents (could be ordering).");
                    System.out.println(Arrays.toString(objArray2));
                    passedGenericToArrayTests = false;
                }
                try {
                    Exception[] nothingGoesHere = new Exception[3];
                    set.toArray(nothingGoesHere);
                    System.out.println("Set did not throw exception when "
                            + "adding contents to Exception[] (expected "
                            + "ArrayStoreException).");
                    passedGenericToArrayTests = false;
                } catch (ArrayStoreException ase) {
                } catch (Exception e) {
                    System.out.println("[WARNING] Set threw exception "
                            + "when an invalid array type was given, but wasn't "
                            + "expected ArrayStoreException.");
                }
            } catch (ClassCastException cce) {
                System.out.println("Caught class cast exception testing "
                        + "toArray():");
                cce.printStackTrace();
                passedGenericToArrayTests = false;
            }
            if (passedGenericToArrayTests) {
                System.out.println("Set passed all toArray(T[] a) tests.");
            } else {
                System.out.println("Set failed one or more toArray(T[] a) "
                        + "tests.");
            }

            // Test if the add method checks for invalid squares.
            System.out.println();
            System.out.println("[Now testing if add() checks for invalid "
                    + "squares]");
            System.out.println("Creating new set {}.");
            set = new SquareSet<>();
            boolean checksForInvalid = true;
            try {
                set.add(new EvilSquare('z', '_'));
                System.out.println("Add method does not check for invalid "
                        + "squares");
                checksForInvalid = false;
            } catch (InvalidSquareException ise) {
            }
            if (checksForInvalid) {
                System.out.println("Set add() checks for invalid squares.");
            } else {
                System.out.println("Set add() doesn't check for invalid "
                        + "squares.");
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println(
                    "Supercatch caught an unanticipated Exception:");
            e.printStackTrace();
        }
    }
}