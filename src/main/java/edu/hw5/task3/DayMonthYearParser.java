package edu.hw5.task3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class DayMonthYearParser implements DateParser {
    /**
     * Parse date from string of this format "DD/MM/YYYY"
     *
     * @param string - string to parse
     * @return Optional with LocalDate, if parser managed to parse date from string, otherwise returns Optional.empty()
     */
    @SuppressWarnings("MagicNumber")
    @Override
    public Optional<LocalDate> parseDate(String string) {
        String[] parts = string.split("/");

        if (parts.length == 3) {
            ArrayList<Integer> dayMonthYear = new ArrayList<>(3);

            try {
                for (String part : parts) {
                    dayMonthYear.add(Integer.parseInt(part));
                }
            } catch (NumberFormatException exc) {
                return Optional.empty();
            }

            // Example:
            // If year is presented like 20
            // Then we should interpret it like 2020 year
            if (dayMonthYear.get(2) < 1000) {
                dayMonthYear.add(2, dayMonthYear.get(2) + 2000);
            }

            return Optional.of(
                LocalDate.of(
                    dayMonthYear.get(2),
                    dayMonthYear.get(1),
                    dayMonthYear.get(0)
                )
            );
        }

        return Optional.empty();
    }
}
