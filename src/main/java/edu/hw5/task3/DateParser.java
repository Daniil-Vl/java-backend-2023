package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public interface DateParser {
    /**
     * Parse date from string of this format "YYYY-MM-DD"
     *
     * @param string - string to parse
     * @return Optional with LocalDate, if parser managed to parse date from string, otherwise returns Optional.empty()
     */
    Optional<LocalDate> parseDate(String string);
}
