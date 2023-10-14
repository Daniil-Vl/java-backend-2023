package edu.hw1.task1;

public class Task1 {

    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static int minutesToSeconds(String videoLength) {
        String[] hoursAndMinutes = videoLength.split(":");

        // Return -1 if we can't get hours and minutes from inputted string
        if (hoursAndMinutes.length != 2) {
            return -1;
        }

        int minutes;
        int seconds;

        // Return -1 if hours and minutes not int
        try {
            minutes = Integer.parseInt(hoursAndMinutes[0]);
            seconds = Integer.parseInt(hoursAndMinutes[1]);
        } catch (NumberFormatException e) {
            return -1;
        }

        // Return -1 if hours or minutes out of range
        if (seconds >= 60 || seconds < 0 || minutes < 0) {
            return -1;
        }

        return 60 * minutes + seconds;
    }
}
