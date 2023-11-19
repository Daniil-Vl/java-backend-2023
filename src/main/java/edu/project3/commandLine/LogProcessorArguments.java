package edu.project3.commandLine;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record LogProcessorArguments(
    @NotNull List<String> paths,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    @NotNull OutputFormat format
) {
    // This formatter can process dates with and without offset
    private static final DateTimeFormatter ISO_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    private static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ISO_DATE;
    private static final String PATH_ARGUMENT_NAME = "--path";
    private static final String FROM_ARGUMENT_NAME = "--from";
    private static final String TO_ARGUMENT_NAME = "--to";
    private static final String FORMAT_ARGUMENT_NAME = "--format";

    @SuppressWarnings("MultipleStringLiterals")
    public static LogProcessorArguments parseArguments(String[] args) {
        int pos = 0;
        List<String> tempPaths = new ArrayList<>();
        OffsetDateTime tempFrom = null;
        OffsetDateTime tempTo = null;
        OutputFormat tempFormat = OutputFormat.MARKDOWN;

        while (pos < args.length) {
            switch (args[pos]) {
                case PATH_ARGUMENT_NAME -> {
                    pos++;
                    while (pos < args.length
                        && !args[pos].equals(FROM_ARGUMENT_NAME)
                        && !args[pos].equals(TO_ARGUMENT_NAME)
                        && !args[pos].equals(FORMAT_ARGUMENT_NAME)) {
                        tempPaths.add(args[pos++]);
                    }
                    pos--;
                }
                case FROM_ARGUMENT_NAME -> {
                    tempFrom = parseDate(args[++pos]);
                }
                case TO_ARGUMENT_NAME -> {
                    tempTo = parseDate(args[++pos]);
                }
                case FORMAT_ARGUMENT_NAME -> {
                    tempFormat = OutputFormat.parseFormat(args[++pos]);
                }
                default ->
                    throw new IllegalArgumentException("Unsupported command line argument: %s".formatted(args[pos]));
            }
            pos++;
        }

        if (tempPaths.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be empty");
        }

        return new LogProcessorArguments(tempPaths, tempFrom, tempTo, tempFormat);
    }

    /**
     * Parse date like this "2023-08-31", "2023-08-31+01:00", "2023-08-31T01:01:01", "2023-08-31T01:01:01+01:00"
     *
     * @param string - string, from which we parse date
     * @return OffsetDateTime object, storing parsed date
     */
    private static OffsetDateTime parseDate(String string) {
        TemporalAccessor parsed;
        ZoneOffset offset = ZoneOffset.ofHours(0);
        LocalDate date;
        LocalTime time = LocalTime.ofSecondOfDay(0);

        try {
            parsed = ISO_DATE_TIME_FORMATTER.parse(string);
        } catch (DateTimeParseException exc) {
            parsed = ISO_DATE_FORMATTER.parse(string);
        }

        date = LocalDate.of(
            parsed.get(ChronoField.YEAR),
            parsed.get(ChronoField.MONTH_OF_YEAR),
            parsed.get(ChronoField.DAY_OF_MONTH)
        );

        if (parsed.isSupported(ChronoField.HOUR_OF_DAY)) {
            time = LocalTime.of(
                parsed.get(ChronoField.HOUR_OF_DAY),
                parsed.get(ChronoField.MINUTE_OF_HOUR),
                parsed.get(ChronoField.SECOND_OF_MINUTE)
            );
        }

        if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
            offset = ZoneOffset.ofTotalSeconds(parsed.get(ChronoField.OFFSET_SECONDS));
        }

        return OffsetDateTime.of(date, time, offset);
    }
}
