package edu.project1;

import edu.project1.dictionary.Word;

public class HangmanGame {
    private final int maxNumberOfMistakes;
    private final Word wordToFind;
    private int numberOfMistakes = 0;
    private String feedbackMessage = null;

    HangmanGame(Word wordToFind, int maxNumberOfMistakes) throws IllegalArgumentException {
        this.wordToFind = wordToFind;

        if (maxNumberOfMistakes < 1) {
            throw new IllegalArgumentException("Player must have at least one attempt");
        }

        this.maxNumberOfMistakes = maxNumberOfMistakes;
    }

    public String getGameOverMessage() {
        return "You lost!";
    }

    public String getWinMessage() {
        return "You won!";
    }

    public String getFeedbackMessage() {
        if (feedbackMessage == null) {
            return "You should guess first letter, before getting feedback message";
        }
        return feedbackMessage;
    }

    /**
     * Try to open letter in word
     *
     * @param letter - letter to open
     * @return false, if no attempts left, otherwise true
     */
    public boolean guessLetter(char letter) throws EndOfTheGameException {

        if (this.isGameOver() || this.isPlayerWin()) {
            throw new EndOfTheGameException("You cannot play after game end");
        }

        if (!this.wordToFind.openLetter(letter)) {
            this.numberOfMistakes++;
            this.feedbackMessage = "Missed, mistake %d out of %d.\n".formatted(
                this.numberOfMistakes,
                this.maxNumberOfMistakes
            );
        } else {
            this.feedbackMessage = "Hit!\n";
        }

        return this.numberOfMistakes < this.maxNumberOfMistakes;
    }

    public Word getWordToFind() {
        return this.wordToFind;
    }

    public String getWordCurrentState() {
        return this.wordToFind.toString();
    }

    public boolean isGameOver() {
        return this.numberOfMistakes == this.maxNumberOfMistakes;
    }

    public boolean isPlayerWin() {
        return this.wordToFind.isSolved();
    }

}
