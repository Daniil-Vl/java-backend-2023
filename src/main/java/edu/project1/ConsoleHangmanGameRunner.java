package edu.project1;

import edu.project1.dictionary.Dictionary;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangmanGameRunner {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static HangmanGame GAME = new HangmanGame(Dictionary.getRandomWord(), 6);
    private final static Scanner SCANNER = new Scanner(System.in);

    private ConsoleHangmanGameRunner() {
    }

    private static void run() {
        String line;

        while (!GAME.isGameOver() && !GAME.isPlayerWin()) {
            LOGGER.info("Guess a letter:");

            try {
                line = SCANNER.nextLine().strip();
            } catch (NoSuchElementException exc) {
                LOGGER.info("Hangman game termination...");
                break;
            }

            if (line.length() > 1) {
                LOGGER.warn("You must enter just one character. Please try again.");
                continue;
            }

            GAME.guessLetter(line.charAt(0));

            LOGGER.info(GAME.getFeedbackMessage());

            LOGGER.info("The word: " + GAME.getWordCurrentState());

            if (GAME.isPlayerWin()) {
                LOGGER.info(GAME.getWinMessage());
                break;
            }

            if (GAME.isGameOver()) {
                LOGGER.info(GAME.getGameOverMessage());
                break;
            }
        }
    }

    // This is entry point to console game
//    public static void main(String[] args) {
//        LOGGER.info("Hangman game starts...");
//        LOGGER.info("You can stop game by pressing Ctrl + D");
//        run();
//        LOGGER.info("Hangman game ends...");
//    }
}
