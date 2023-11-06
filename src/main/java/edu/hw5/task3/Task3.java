package edu.hw5.task3;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Task3 {
    private static final List<DateParser> DATE_PARSERS = List.of(
        new YearMonthDayParser(),
        new DayMonthYearParser(),
        new OneWordParser(),
        new DaysAgoParser()
    );

    private Task3() {
    }

    /**
     * Try to parse date from given string using parsers in chain of responsibility
     *
     * @param string - string to parse
     * @return Optional with LocalDate, if some parser manage with parsing, otherwise returns Optional.empty()
     */
    public static Optional<LocalDate> parseDate(String string) {
        return DATE_PARSERS
            .stream()
            .map(el -> el.parseDate(string))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst();
    }
}
