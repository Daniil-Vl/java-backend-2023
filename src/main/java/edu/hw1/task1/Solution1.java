package edu.hw1.task1;

public class Solution1 {
    public static int minutesToSeconds(String videoLength) {
        String[] hours_and_minutes = videoLength.split(":");

        if (hours_and_minutes.length != 2) {
            return -1;
        }

        int hours = Integer.parseInt(hours_and_minutes[0]);
        int minutes = Integer.parseInt(hours_and_minutes[1]);

        if (minutes >= 60 || minutes < 0 || hours < 0) {
            return -1;
        }

        return 60 * hours + minutes;
    }
}
