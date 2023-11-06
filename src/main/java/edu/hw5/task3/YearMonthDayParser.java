package edu.hw5.task3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class YearMonthDayParser implements DateParser {
    /**
     * Parse date from string of this format "YYYY-MM-DD"
     *
     * @param string - string to parse
     * @return Optional with LocalDate, if parser managed to parse date from string, otherwise returns Optional.empty()
     */
    @SuppressWarnings("MagicNumber")
    @Override
    public Optional<LocalDate> parseDate(String string) {
        String[] parts = string.split("-");

        if (parts.length == 3) {
            ArrayList<Integer> yearMonthDay = new ArrayList<>(3);

            try {
                for (String part : parts) {
                    yearMonthDay.add(Integer.parseInt(part));
                }
            } catch (NumberFormatException exc) {
                return Optional.empty();
            }

            return Optional.of(
                LocalDate.of(
                    yearMonthDay.get(0),
                    yearMonthDay.get(1),
                    yearMonthDay.get(2)
                )
            );
        }

        return Optional.empty();
    }
}
