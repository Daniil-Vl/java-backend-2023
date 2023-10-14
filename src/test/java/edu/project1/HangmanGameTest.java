package edu.project1;

import edu.project1.dictionary.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HangmanGameTest {

    @Test
    void testGameWithInvalidWord() {
        assertThrows(IllegalArgumentException.class, () -> new HangmanGame(new Word(""), 2));
    }

    @Test
    void testGameWithInvalidAttemptNumber() {
        assertThrows(IllegalArgumentException.class, () -> new HangmanGame(new Word("asd"), 0));
        assertThrows(IllegalArgumentException.class, () -> new HangmanGame(new Word("asd"), -10));
    }

    @Test
    void testFirstFeedbackMessage() {
        HangmanGame game = new HangmanGame(new Word("this"), 2);
        String expected = "You should guess first letter, before getting feedback message";
        assertThat(game.getFeedbackMessage()).isEqualTo(expected);
    }

    @Test
    void testFeedbackMessage() {
        HangmanGame game = new HangmanGame(new Word("this"), 6);

        String expected = "Hit!\n";
        game.guessLetter('t');
        assertThat(game.getFeedbackMessage()).isEqualTo(expected);

        expected = "Missed, mistake 1 out of 6.\n";
        game.guessLetter('a');
        assertThat(game.getFeedbackMessage()).isEqualTo(expected);
    }

    /**
     * Checks, that game stops, when no attempts left
     */
    @Test
    void testGameStopsWhenNoAttemptsLeft() {
        HangmanGame game = new HangmanGame(new Word("this"), 1);

        // Game over, because this was last attempt
        assertThat(game.guessLetter('a')).isFalse();
    }

    @Test
    @DisplayName("Test, that game state changes correctly")
    void testGameState() {
        HangmanGame game = new HangmanGame(new Word("this"), 2);
        Word wordState;

        // Check, that game doesn't stop at first try
        assertThat(game.guessLetter('a')).isTrue();

        // Check, that word state remains the same, when we input wrong letter
        wordState = game.getWordToFind();
        assertThat(wordState.toString()).isEqualTo("****");

        game.guessLetter('h');
        wordState = game.getWordToFind();
        assertThat(wordState.toString()).isEqualTo("*h**");

        game.guessLetter('t');
        game.guessLetter('s');
        wordState = game.getWordToFind();
        assertThat(wordState.toString()).isEqualTo("th*s");
    }

}