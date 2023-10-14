package edu.project1.dictionary;

import java.util.Arrays;

public class Word {
    private final char[] entireString;
    private final int length;
    /**
     * Contains a string with all open and unopened positions at the moment
     */
    private final char[] stringCurrentState;
    private boolean isSolved = false;

    // TODO add here input string validation
    public Word(String value) {

        if (value.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be empty");
        }

        this.entireString = value.toCharArray();
        this.stringCurrentState = "*".repeat(this.entireString.length).toCharArray();
        this.length = value.length();
    }

    public boolean isSolved() {
        return isSolved;
    }

    /**
     * Open all letter occurrences in word
     *
     * @return true if at least one letter was opened, otherwise false
     */
    public boolean openLetter(char letter) {
        boolean isLetterOpened = false;

        for (int i = 0; i < this.length; i++) {
            if (this.entireString[i] == letter) {
                this.stringCurrentState[i] = letter;
                isLetterOpened = true;
            }
        }

        if (isLetterOpened) {
            this.isSolved = Arrays.equals(this.stringCurrentState, this.entireString);
        }

        return isLetterOpened;
    }

    @Override
    public String toString() {
        return new String(this.stringCurrentState);
    }
}
