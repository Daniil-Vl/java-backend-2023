package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Comparator;
import org.jetbrains.annotations.NotNull;

public class Task5 {
    private static final Comparator<Contact> COMPARATOR = Contact::compareTo;

    private static final String ASCENDING = "ASC";
    private static final String DESCENDING = "DESC";

    private Task5() {
    }

    public static ArrayList<Contact> parseContacts(String[] names, @NotNull String order) {

        if (names == null || names.length == 0) {
            return new ArrayList<>();
        }

        ArrayList<Contact> result = new ArrayList<>(names.length);
        String[] nameAndSurname;
        for (String person : names) {
            nameAndSurname = person.split(" ");
            result.add(new Contact(nameAndSurname[0], nameAndSurname[1]));
        }

        switch (order) {
            case ASCENDING -> result.sort(COMPARATOR);
            case DESCENDING -> result.sort(COMPARATOR.reversed());
            default -> throw new IllegalArgumentException("Order must be 'ASC' or 'DESC'");
        }

        return result;
    }

}
