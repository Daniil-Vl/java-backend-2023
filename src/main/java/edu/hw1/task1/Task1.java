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

        int hours = 0;
        int minutes = 0;

        // Return -1 if hours and minutes not int
        try {
            hours = Integer.parseInt(hoursAndMinutes[0]);
            minutes = Integer.parseInt(hoursAndMinutes[1]);
        } catch (NumberFormatException e) {
            return -1;
        }

        // Return -1 if hours or minutes out of range
        if (minutes >= 60 || minutes < 0 || hours < 0) {
            return -1;
        }

        return 60 * hours + minutes;
    }
}
