package edu.hw1.task0;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Solution0 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Solution0() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void helloWorld() {
        LOGGER.info("Привет, мир!");
    }
}
