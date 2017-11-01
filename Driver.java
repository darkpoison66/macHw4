public class Driver {
    public static void main(String[] args) {
        FileIterator iter = new FileIterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}