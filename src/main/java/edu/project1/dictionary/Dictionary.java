package edu.project1.dictionary;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

/**
 * Store dictionary of allowed words for hangman game
 * Provide method to retrieve random word from dictionary
 */
public class Dictionary {
    private static final Random RANDOMIZER = new Random();
    private static final String[] WORDS = {
        "bank",
        "investment",
        "travel",
        "business",
        "career",
    };
    private static final int NUMBER_OF_WORDS = WORDS.length;

    private Dictionary() {
    }

    /**
     * Get random word from dictionary
     *
     * @return random word from dictionary
     */
    @NotNull public static Word getRandomWord() {
        return new Word(
            WORDS[RANDOMIZER.nextInt(NUMBER_OF_WORDS)]
        );
    }
}
