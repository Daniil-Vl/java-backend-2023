package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    /**
     * Find all Fridays that fall on the 13th day of the month
     *
     * @param year - year, where we try to find fridays
     * @return list of all 13th fridays in given year
     */
    @SuppressWarnings("MagicNumber")
    public static List<LocalDate> getFridays13(int year) {
        List<LocalDate> result = new LinkedList<>();

        // Get first friday of the year
        // If 1st january is friday, we can skip this here (because we need only 13th fridays
        LocalDate date = LocalDate
            .ofYearDay(year, 1)
            .with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        while (date.getYear() == year) {
            if (date.getDayOfMonth() == 13) {
                result.add(date);
            }
            date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return result;
    }

    /**
     * Finds nearest friday 13 to given date
     */
    @SuppressWarnings("MagicNumber")
    public static LocalDate findNearestFriday13(LocalDate date) {
        return date.with(TemporalAdjusters.ofDateAdjuster(currentDate -> {
            // Try to find nearest friday 13 in current year
            List<LocalDate> fridays = getFridays13(date.getYear());
            for (LocalDate friday13 : fridays) {
                if (date.isBefore(friday13) || date.isEqual(friday13)) {
                    return friday13;
                }
            }

            // If not found this year, then return first friday 13 of the next year
            return getFridays13(date.getYear() + 1).get(0);
        }));
    }
}
