package edu.hw3.task7;

import java.util.Comparator;

public class NullTreeComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {

        // null < String for each string

        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null && o2 != null) {
            return -1;
        } else if (o1 != null && o2 == null) {
            return 1;
        } else {
            return o1.compareTo(o2);
        }

    }
}
