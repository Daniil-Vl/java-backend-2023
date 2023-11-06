package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class OneWordParser implements DateParser {
    /**
     * Parse date from string of this format "yesterday" or "today" or "tomorrow"
     *
     * @param string - string to parse
     * @return Optional with LocalDate, if parser managed to parse date from string, otherwise returns Optional.empty()
     */
    @Override
    public Optional<LocalDate> parseDate(String string) {
        return switch (string) {
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            case "today" -> Optional.of(LocalDate.now());
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            default -> Optional.empty();
        };
    }
}
