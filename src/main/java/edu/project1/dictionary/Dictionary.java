package edu.project1.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

/**
 * Store dictionary of allowed words for hangman game
 * Provide method to retrieve random word from dictionary
 */
public class Dictionary {
    private static final Random RANDOMIZER = new Random();
    private static final ArrayList<Word> WORDS = new ArrayList<>(List.of(
        new Word("bank"),
        new Word("investment"),
        new Word("travel"),
        new Word("business"),
        new Word("career")
    ));

    private Dictionary() {
    }

    /**
     * Get random word from dictionary
     *
     * @return random word from dictionary
     */
    @NotNull public static Word getRandomWord() {
        return WORDS.get(
            RANDOMIZER.nextInt(WORDS.size())
        );
    }

    public static void addWord(String word) throws IllegalArgumentException {
        WORDS.add(new Word(word));
    }
}
