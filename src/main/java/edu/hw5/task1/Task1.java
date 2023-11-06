package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task1 {

    private Task1() {
    }

    private static final DateTimeFormatter DATE_TIME_PARSER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    /**
     * Calculate average session time
     * <p>
     * <p>
     * Sessions format:
     * 2022-03-12, 20:20 - 2022-03-12, 23:50
     * 2022-04-01, 21:30 - 2022-04-02, 01:20
     *
     * @return String of average time in format "nч mм"
     */
    public static String getAverageDuration(List<String> sessions) {

        if (sessions.isEmpty()) {
            return "0ч 0м";
        }

        Duration totalTimeSpent = Duration.ZERO;
        Duration duration;
        String[] startAndEnd;

        for (String session : sessions) {
            startAndEnd = session.split(" - ");
            duration = Duration.between(
                LocalDateTime.parse(startAndEnd[0], DATE_TIME_PARSER),
                LocalDateTime.parse(startAndEnd[1], DATE_TIME_PARSER)
            );
            totalTimeSpent = totalTimeSpent.plusSeconds(duration.getSeconds());
        }

        Duration averageTime = totalTimeSpent.dividedBy(sessions.size());

        return "%dч %dм".formatted(averageTime.toHours(), averageTime.toMinutesPart());
    }

}
