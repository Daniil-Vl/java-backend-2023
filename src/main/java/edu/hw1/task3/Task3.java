package edu.hw1.task3;

public class Task3 {

    private Task3() {
    }

    public static boolean isNestable(int[] inner, int[] outer) {
        // Return false if any array is empty
        if (inner.length < 1 || outer.length < 1) {
            return false;
        }

        // Return true if min(inner) > min(outer) and max(inner) < max(outer) like in task condition
        return getMin(inner) > getMin(outer) && getMax(inner) < getMax(outer);
    }

    private static int getMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
