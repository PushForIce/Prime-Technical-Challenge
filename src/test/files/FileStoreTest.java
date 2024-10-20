package files;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileStoreTest {
    FileStore fileStore = new FileStore();

    @Test
    public void testWriteToFile() {
        int valueToWrite = 991;
        fileStore.writeToFile(valueToWrite);
        int lastEntry = readLastEntryInFile();

        assertEquals(valueToWrite, lastEntry);
    }

    @Test
    public void testCreateFile() {
        File file = new File("primenumbers.txt");
        file.delete();

        assertFalse(file.isFile());

        fileStore.createFile();

        assertTrue(file.isFile());
    }

    private int readLastEntryInFile() {
        String line = null;
        String nextLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("primeNumbers.txt"))) {
            while ((nextLine = bufferedReader.readLine()) != null) {
                line = nextLine;
            }
        } catch (IOException e) {
            fail("Error reading file");
        }
        if (line != null) {
            return Integer.parseInt(line);
        } else {
            return -1;
        }
    }

    @AfterAll
    public static void afterAll() {
        FileStore.closeWriter();
    }
}