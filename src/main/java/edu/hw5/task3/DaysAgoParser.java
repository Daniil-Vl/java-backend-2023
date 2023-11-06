package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DaysAgoParser implements DateParser {
    /**
     * Parse date from string of this format "n days ago"
     *
     * @param string - string to parse
     * @return Optional with LocalDate, if parser managed to parse date from string, otherwise returns Optional.empty()
     */
    @SuppressWarnings("MagicNumber")
    @Override
    public Optional<LocalDate> parseDate(String string) {
        String[] parts = string.split(" ");

        if (parts.length == 3) {
            int days;

            try {
                days = Integer.parseInt(parts[0]);
            } catch (NumberFormatException exc) {
                return Optional.empty();
            }

            if ((parts[1].equals("day") || parts[1].equals("days")) && parts[2].equals("ago")) {
                return Optional.of(
                    LocalDate.now().minusDays(days)
                );
            } else {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }
}
