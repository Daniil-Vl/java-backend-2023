package edu.hw1.task0;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class Task0 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Task0() {
    }

    @SuppressWarnings("unused")
    public static void helloWorld() {
        LOGGER.info("Привет, мир!");
    }
}
