package it.unibo.mvc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationReader {

    public final int MINIMUM;
    public final int MAXIMUM;
    public final int ATTEMPTS;
    /**
     * Read the configuration file and put the options in their appropiate fields
     * @param file
     * @throws FileNotFoundException
     */
    ConfigurationReader(File file) throws FileNotFoundException {
        final Map<String, Integer> configuration = new HashMap<>();
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String option = parts[0].trim();
                String num = parts[1].trim();
                configuration.put(option, Integer.valueOf(num));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e); // NOPMD: allowed as this is just an exercise
        } catch (NumberFormatException e2) {
            System.out.println("Error: " + e2); // NOPMD
        } catch (IOException e3) {
            System.out.println("Error: " + e3); // NOPMD
        }
        MINIMUM = configuration.get("minimum");
        MAXIMUM = configuration.get("maximum");
        ATTEMPTS = configuration.get("attempts");
    }
}
