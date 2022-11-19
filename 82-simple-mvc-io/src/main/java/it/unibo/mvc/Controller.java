package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private File currentFile;

    /**
     * Method that set a new file as current file.
     * @param file
     */

    public void setFile(final File file) {
        this.currentFile = file;
    }

    /**
     * Method that return the current file.
     * @return File
     */

    public File getFile() {
        Objects.requireNonNull(this.currentFile);
        return this.currentFile;
    }

    /**
     * Method that return the path of the current file.
     * @return String
     */
    public String getPath() {
        Objects.requireNonNull(this.currentFile);
        return this.currentFile.toPath().toString();
    }

    /**
     * Static method that read the content of a file and return content as String.
     * @param file
     * @return String
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static String getFileContent(final File file) throws IOException, FileNotFoundException {

        final BufferedReader reader = new BufferedReader(new FileReader(file));
        final StringBuilder stringBuilder = new StringBuilder();
        String line;
        final String ls = System.getProperty("line.separator");
        try {
            do {
                line = reader.readLine();
                stringBuilder.append(line);
                stringBuilder.append(ls);
            } while (line != null);
            reader.close();

            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e); // NOPMD: allowed as this is just an exercise
        }
        return null;
    }

    /**
     * Method that save content on a file.
     * @param str
     * @throws IOException
     */
    public void saveOnCurrentFile(final String str) throws IOException {

        try (PrintStream ps = new PrintStream(this.getPath(), StandardCharsets.UTF_8)) {
            ps.print(str);
        } catch (IOException e1) {
            System.out.println("Filed to insert string"); // NOPMD: allowed as this is just an exercise
            e1.printStackTrace(); // NOPMD: allowed as this is just an exercise
        }
    }
}
