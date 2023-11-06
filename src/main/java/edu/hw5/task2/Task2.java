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
            // While currentDate not friday 13
            LocalDate result = currentDate;
            while (!(result.getDayOfMonth() == 13 && result.getDayOfWeek().equals(DayOfWeek.FRIDAY))) {
                result = result.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            }
            return result;
        }));
    }
}
