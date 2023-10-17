package edu.hw3.task5;

import org.jetbrains.annotations.NotNull;

public record Contact(String name, String surname) implements Comparable<Contact> {

    public Contact(@NotNull String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public int compareTo(@NotNull Contact o) {
        if (this.surname != null && o.surname != null) {
            return this.surname.compareTo(o.surname);

        } else if (this.surname != null && o.surname == null) {
            return this.surname.compareTo(o.name);

        } else if (this.surname == null && o.surname != null) {
            return this.name.compareTo(o.surname);

        } else {
            return this.name.compareTo(o.name);
        }
    }

    @Override
    public String toString() {
        return "(%s, %s)".formatted(this.name, this.surname);
    }
}
