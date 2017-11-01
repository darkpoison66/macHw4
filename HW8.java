import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class HW8 {

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

    public static void main(String[] args) {
        try{
            System.out.println();
            System.out.println("[Now rudimentary requirements]");
            // Make sure it instantiates.
            SquareSet set = new SquareSet();
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
            set = new SquareSet();
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
            set = new SquareSet();
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

            if(passedContainsAll) {
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
            set = new SquareSet();
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

            SquareSet set6 = new SquareSet();
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
            set = new SquareSet();
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
            } else{
                System.out.println("Set failed one or more hashCode() tests.");
            }

            // Check isEmpty() method
            System.out.println();
            System.out.println("[Now testing isEmpty() method]");
            set = new SquareSet();
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
            } else{
                System.out.println("Set failed one or more isEmpty() tests.");
            }

            // Check iterator() method
            System.out.println();
            System.out.println("[Now testing iterator() method]");
            System.out.println("Creating new set {e2, f7, a6}.");
            set = new SquareSet();
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
                ArrayList<Square> alreadyRemoved = new ArrayList<Square>();
                /*for (Square s : set) {
                    if ((!s.equals(new Square("e2"))
                            && !s.equals(new Square("a6"))
                            && !s.equals(new Square("f7")))
                            || alreadyRemoved.contains(s)) {
                        System.out.println(
                        "Iterator didn't return an element in the set.");
                        passedIteratorTests = false;
                    }
                    if ((!s.equals(new Square("e2"))
                            && !s.equals(new Square("a6"))
                            && !s.equals(new Square("f7")))
                            || alreadyRemoved.contains(s)) {
                        System.out.println(
                        "Iterator didn't return an element in the set.");
                        passedIteratorTests = false;
                    }
                    if ((!s.equals(new Square("e2"))
                            && !s.equals(new Square("a6"))
                            && !s.equals(new Square("f7")))
                            || alreadyRemoved.contains(s)) {
                        System.out.println(
                        "Iterator didn't return an element in the set.");
                        passedIteratorTests = false;
                    }
                    alreadyRemoved.add(s);
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

            // Check size() method
            System.out.println();
            System.out.println("[Now testing size() method]");
            System.out.println("Creating new set {a1, a2, a3}.");
            set = new SquareSet();
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
            } else{
                System.out.println("Set failed one or more size() tests.");
            }

            // Check toArray() method
            System.out.println();
            System.out.println("[Now testing toArray() method]");
            System.out.println("Creating new set {a1, a2, a3}.");
            set = new SquareSet();
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
                    if (!((Square) o).equals(new Square("a"+f))) {
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
            } catch(ClassCastException cce) {
                System.out.println("Caught class cast exception testing "
                + "toArray():");
                cce.printStackTrace();
                passedToArrayTests = false;
            }
            if (passedToArrayTests) {
                System.out.println("Set passed all toArray() tests.");
            } else{
                System.out.println("Set failed one or more toArray() tests.");
            }

            // Check toArray(T[] a) method
            System.out.println();
            System.out.println("[Now testing toArray(T[] a) method]");
            System.out.println("Creating new set {a1, a2, a3}.");
            set = new SquareSet();
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
            } catch(ClassCastException cce) {
                System.out.println("Caught class cast exception testing "
                + "toArray():");
                cce.printStackTrace();
                passedGenericToArrayTests = false;
            }
            if (passedGenericToArrayTests) {
                System.out.println("Set passed all toArray(T[] a) tests.");
            } else{
                System.out.println("Set failed one or more toArray(T[] a) "
                + "tests.");
            }

            // Test if the add method checks for invalid squares.
            System.out.println();
            System.out.println("[Now testing if add() checks for invalid "
            + "squares]");
            System.out.println("Creating new set {}.");
            set = new SquareSet();
            boolean checksForInvalid = true;
            try {
                set.add(new EvilSquare('z','_'));
                System.out.println("Add method does not check for invalid "
                + "squares");
                checksForInvalid = false;
            } catch (InvalidSquareException ise) {
            }
            if (checksForInvalid) {
                System.out.println("Set add() checks for invalid squares.");
            } else{
                System.out.println("Set add() doesn't check for invalid "
                + "squares.");
            }

            // Test if the add method checks for invalid squares.
            System.out.println();
            System.out.println("[Now testing addAll()]");
            System.out.println("Creating new set {}.");
            set = new SquareSet();
            boolean addAllWorks = true;
            try {
                if(!set.addAll(sset2)){
                    System.out.println("Add all failed to add all elements to"
                    + " empty set.");
                    addAllWorks = false;
                }
                if(set.addAll(sset2)){
                    System.out.println("Add all returned true when adding a "
                    + "completely duplicate collection.");
                    addAllWorks = false;
                }
                if(set.addAll(set)){
                    System.out.println("Add all returned true when adding the "
                    + "current set to itself.");
                    addAllWorks = false;
                }
                if(!set.addAll(sset3)) {
                    System.out.println("Add all returned false when adding a "
                    + "set containing some but not all duplicates.");
                    addAllWorks = false;
                }
                Collection<Square> c = new ArrayList<Square>();
                c.add(new Square("a3"));
                c.add(new Square("a3"));
                if(!set.addAll(c)) {
                    System.out.println("Collection containing duplicates that "
                    + "were not in the current set.");
                    addAllWorks = false;
                }
                Collection c2 = new ArrayList();
                c2.add(new Square("a4"));
                c2.add("Hellooooooo!");
                c2.add(new Square("a5"));
                try{
                    if(!set.addAll(c2)) {
                        System.out.println("Collection<Object> containing non-"
                        + "duplicate Squares and 1 String did not return true "
                        + "and did not throw ClassCastException.");
                        addAllWorks = false;
                    }
                } catch(ClassCastException e) {
                    System.out.println("Caught ClassCastException testing "
                    + "addAll; this is ok.");
                }
                System.out.println("Attempting to addAll({b8, ko}), should add"
                + " none and throw InvalidSquareException.");
                Collection<Square> c3 = new ArrayList<Square>();
                c3.add(new Square("b8"));
                c3.add(new EvilSquare('k','o'));
                try {
                    set.addAll(c3);
                    System.out.println("addAll() added collection containing "
                    + "an invalid square but did not throw exception (possibly"
                    + " partially added).");
                    addAllWorks = false;
                } catch(InvalidSquareException ise) {
                }
            } catch (UnsupportedOperationException uoe) {
                System.out.println("addAll() method not supported.");
                addAllWorks = false;
            } catch (Exception e) {
                System.out.println("[WARNING] Caught unexpected Exception "
                + "testing addAll()");
                e.printStackTrace();
                addAllWorks = false;
            }
            if (addAllWorks) {
                System.out.println("Set passed all addAll() tests.");
            } else{
                System.out.println("Set failed one or more addAll() tests.");
            }

            // Test if the add method checks for invalid squares.
            System.out.println();
            System.out.println("[Now testing remove()]");
            System.out.println("Creating new set {a1, a2, a4}.");
            set = new SquareSet();
            set.add(new Square("a1"));
            set.add(new Square("a2"));
            set.add(new Square("a4"));
            boolean passedRemove = true;
            try {
                if (set.remove(new RuntimeException("Wheeeeeeeeeeeeeee"))) {
                    System.out.println("Set removed a RuntimeException, but set "
                    + "cannot contain RuntimeException.");
                    passedRemove = false;
                }
                if (set.remove("a1")) {
                    System.out.println("Set removed the String a1, but Strings "
                    + "cannot be in the set (And are not in the given set by"
                    + " design).");
                    passedRemove = false;
                }
                if (set.remove(new Square("b1"))) {
                    System.out.println("Set removed b1 when b1 was not added.");
                    passedRemove = false;
                }
                if (!set.remove(new Square("a1"))) {
                    System.out.println("Set did not remove a1 but a1 was present."
                    );
                    passedRemove = false;
                }
                if (passedRemove && set.remove(new Square("a1"))) {
                    System.out.println("Set removed a1 after a1 should have been "
                    + "removed. (Didn't successfully delete in backing array?)");
                    passedRemove = false;
                }
                if (set.size() != 2) {
                    System.out.println("Set did not update size after removal.");
                    passedRemove = false;
                }
                try {
                    Iterator<Square> iter = set.iterator();
                    iter.next();
                    set.remove(new Square("a4"));
                    try {
                        Iterator<Square> iter2 = set.iterator();
                        iter2.next();
                    } catch(Exception e2) {
                        System.out.println("Removed element during initial "
                        + "iteration but got new iterator; new iterator threw "
                        + "Exception.");
                        passedRemove = false;
                    }
                    iter.next();
                    System.out.println("[WARNING] Iterator continued iteration "
                    + "after removing during iteration. (No pts. lost but "
                    + "dangerous behavior.)");
                } catch (Exception e) {
                    System.out.println("Iterator threw exception when removing"
                    + " during iteration.");
                }
            } catch(UnsupportedOperationException uoe) {
                System.out.println("remove() method not supported.");
                passedRemove = false;
            } catch(Exception e) {
                System.out.println("[WARNING] Caught unexpected exception "
                + "testing remove().");
                passedRemove = false;
            }
            if (passedRemove) {
                System.out.println("Set passed all remove() tests.");
            } else{
                System.out.println("Set failed one or more remove() tests.");
            }

            System.out.println();
            int finalScore = 5;
            finalScore = (!failedToAddAll) ? finalScore + 5 : finalScore;
            finalScore = (checksForInvalid) ? finalScore + 5 : finalScore;
            finalScore = (failedToAddAllDuplicates) ? finalScore + 5
                : finalScore;
            finalScore = (passedContains) ? finalScore + 10 : finalScore;
            finalScore = (passedContainsAll) ? finalScore + 10 : finalScore;
            finalScore = (passedEquals) ? finalScore + 10 : finalScore;
            finalScore = (passedHashCode) ? finalScore + 10 : finalScore;
            finalScore = (passedIsEmptyTests) ? finalScore + 10 : finalScore;
            finalScore = (passedIteratorTests) ? finalScore + 10 : finalScore;
            finalScore = (passedSizeTests) ? finalScore + 5 : finalScore;
            finalScore = (passedToArrayTests) ? finalScore + 5 : finalScore;
            finalScore = (passedGenericToArrayTests) ? finalScore + 10
                : finalScore;
            finalScore = (addAllWorks) ? finalScore + 30 : finalScore;
            finalScore = (passedRemove) ? finalScore + 10 : finalScore;
            System.out.println("Minimum possible score (no bonus or partial "
                + "credit): " + finalScore + "/100");
            System.out.println("[NOTE] addAll() is scored as a group; "
            + "individual requirements are not scored individually in this "
            + "tester.");

        } catch(Exception e) {
            System.out.println(
            "Supercatch caught an unanticipated Exception:");
            e.printStackTrace();
        }
    }

}