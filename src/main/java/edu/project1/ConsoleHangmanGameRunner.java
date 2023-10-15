package edu.project1;

import edu.project1.dictionary.Dictionary;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangmanGameRunner {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static int MAX_NUMBER_OF_MISTAKES = 6;
    private static String line;

    private ConsoleHangmanGameRunner() {
    }

    private static void startNewGame() throws NoSuchElementException {
        HangmanGame game = new HangmanGame(Dictionary.getRandomWord(), MAX_NUMBER_OF_MISTAKES);

        while (!game.isGameOver() && !game.isPlayerWin()) {
            LOGGER.info("Guess a letter:");

            line = SCANNER.nextLine().strip();

            if (line.length() > 1) {
                LOGGER.warn("You must enter just one character. Please try again.");
                continue;
            }

            game.guessLetter(line.charAt(0));

            LOGGER.info(game.getFeedbackMessage());

            LOGGER.info("The word: " + game.getWordCurrentState());

            if (game.isPlayerWin()) {
                LOGGER.info(game.getWinMessage());
                break;
            }

            if (game.isGameOver()) {
                LOGGER.fatal(game.getGameOverMessage());
                break;
            }
        }
    }

    // This is entry point to console game
//    public static void main(String[] args) {
//        LOGGER.info("Hangman game starts...");
//        LOGGER.info("You can stop game by pressing Ctrl + D");
//
//        do {
//            try {
//                LOGGER.info("A new word was made up. Try to find it.");
//                startNewGame();
//                LOGGER.warn("If you want to play again enter 'yes', otherwise press enter button: ");
//                line = SCANNER.nextLine();
//            } catch (NoSuchElementException exc) {
//                LOGGER.info("Hangman game termination...");
//                break;
//            }
//        } while (line.strip().equalsIgnoreCase("yes"));
//
//        LOGGER.info("Hangman game ends...");
//    }
}
