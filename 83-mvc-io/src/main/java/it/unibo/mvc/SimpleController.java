package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String currentString;
    private final List<String> history = new ArrayList<>();

    @Override
    public void setString(final String str) {
        if (str == null) {
            throw new IllegalStateException("Null string is not accetable");
        }
        this.currentString = str;
    }

    @Override
    public String getString() {
        return this.currentString;
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(this.history);
    }

    @Override
    public void printCurrent() {
        if (currentString == null || currentString.isEmpty()) {
            throw new IllegalStateException("Empty string is not accettable");
        }
        System.out.println(currentString); // NOPMD
        this.history.add(currentString);
    }
}
