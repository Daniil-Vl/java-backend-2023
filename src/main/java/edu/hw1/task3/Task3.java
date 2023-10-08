package edu.hw1.task3;

import java.util.Arrays;

public class Task3 {

    private Task3() {
    }

    public static boolean isNestable(int[] inner, int[] outer) {
        // Return false if any array is empty
        if (inner.length < 1 || outer.length < 1) {
            return false;
        }

        // Return true if min(inner) > min(outer) and max(inner) < max(outer) like in task condition
        return Arrays.stream(inner).min().getAsInt() > Arrays.stream(outer).min().getAsInt()
            && Arrays.stream(inner).max().getAsInt() < Arrays.stream(outer).max().getAsInt();
    }
}
