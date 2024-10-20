package files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class FileStore {
    private final static Logger logger = LogManager.getLogger(FileStore.class);
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    private static PrintWriter printWriter;
    private final File file = new File("primenumbers.txt");

    public void writeToFile(int valueToWrite) {
        if (printWriter == null) {
            createWriter();
        }
        if (!file.isFile()) {
            createFile();
        }
        printWriter.println(valueToWrite);
        logger.debug("Wrote {} to file {}", valueToWrite, file.getName());
    }

    public void createFile() {
        try {
            if (file.createNewFile()) {
                logger.info("File {} created", file.getName());
            }
        } catch (IOException e) {
            logger.error("Failed to create file {} due to exception {}", file.getName(), e);
        }
    }

    private void createWriter() {
        logger.info("Creating writers");
        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter, true);
        } catch (IOException e) {
            logger.error("Failed to create writer due to exception {}", String.valueOf(e));
        }
    }

    public static void closeWriter() {
        logger.info("Closing writers");
        try {
            if (printWriter != null) {
                printWriter.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            logger.error("Failed to close writer due to exception {}", String.valueOf(e));
        }
    }
}
