package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class YearMonthDayParser implements DateParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d");

    /**
     * Parse date from string of this format "YYYY-MM-DD"
     *
     * @param string - string to parse
     * @return Optional with LocalDate, if parser managed to parse date from string, otherwise returns Optional.empty()
     */
    @SuppressWarnings("MagicNumber")
    @Override
    public Optional<LocalDate> parseDate(String string) {
        try {
            return Optional.of(
                LocalDate.parse(string, FORMATTER)
            );
        } catch (DateTimeParseException exc) {
            return Optional.empty();
        }
    }
}
