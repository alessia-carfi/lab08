package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
     * Method that setting the next string to print. 
     * Null values are not acceptable,
     * and an exception should be produced.
     * @param str
     */
    void setString(String str);

    /**
     * Method for getting the next string to print.
     * @return String
     */
    String getString();

    /**
     * Method for getting the history of the printed strings.
     * @return List<String>
     */
    List<String> getHistory();

    /**
     * Method that print the current string.
     * If the current string is unset,
     * an IllegalStateException should be thrown.
     */
    void printCurrent();
}
