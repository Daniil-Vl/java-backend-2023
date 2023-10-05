package edu.hw1.task1;

public class Solution1 {

    private Solution1() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @SuppressWarnings("MagicNumber")
    public static int minutesToSeconds(String videoLength) {
        String[] hoursAndMinutes = videoLength.split(":");

        if (hoursAndMinutes.length != 2) {
            return -1;
        }

        int hours = Integer.parseInt(hoursAndMinutes[0]);
        int minutes = Integer.parseInt(hoursAndMinutes[1]);

        if (minutes >= 60 || minutes < 0 || hours < 0) {
            return -1;
        }

        return 60 * hours + minutes;
    }
}
