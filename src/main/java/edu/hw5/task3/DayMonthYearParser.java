package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DayMonthYearParser implements DateParser {
    private static final DateTimeFormatter FULL_YEAR_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter TRUNCATED_YEAR_FORMATTER = DateTimeFormatter.ofPattern("d/M/yy");

    /**
     * Parse date from string of this format "DD/MM/YYYY"
     *
     * @param string - string to parse
     * @return Optional with LocalDate, if parser managed to parse date from string, otherwise returns Optional.empty()
     */
    @SuppressWarnings("MagicNumber")
    @Override
    public Optional<LocalDate> parseDate(String string) {
        try {
            return Optional.of(
                LocalDate.parse(string, FULL_YEAR_FORMATTER)
            );
        } catch (DateTimeParseException firstExc) {
            try {
                return Optional.of(
                    LocalDate.parse(string, TRUNCATED_YEAR_FORMATTER)
                );
            } catch (DateTimeParseException secondExc) {
                return Optional.empty();
            }
        }
    }
}
