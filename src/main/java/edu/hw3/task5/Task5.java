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

        ArrayList<Contact> resultContacts = new ArrayList<>(names.length);
        String[] nameAndSurname;

        for (String person : names) {
            nameAndSurname = person.trim().split(" ");

            switch (nameAndSurname.length) {
                case 2:
                    resultContacts.add(new Contact(nameAndSurname[0], nameAndSurname[1]));
                    break;
                case 1:
                    resultContacts.add(new Contact(nameAndSurname[0]));
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Input strings must contain name and surname or name without surname");
            }
        }

        switch (order) {
            case ASCENDING -> resultContacts.sort(COMPARATOR);
            case DESCENDING -> resultContacts.sort(COMPARATOR.reversed());
            default -> throw new IllegalArgumentException("Order must be 'ASC' or 'DESC'");
        }

        return resultContacts;
    }

}
