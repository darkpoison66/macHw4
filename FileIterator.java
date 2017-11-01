import java.io.File;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class FileIterator implements Iterator<File> {
    private File[] file;
    private int pointer;

    public FileIterator() {
        File files = new File(".");
        file = files.listFiles();
    }

    @Override
    public File next() {
        if (pointer < file.length) {
            return file[pointer++];
        } else {
            throw new NoSuchElementException("No More Files");
        }
    }

    @Override
    public boolean hasNext() {
        return pointer < file.length;
    }
}