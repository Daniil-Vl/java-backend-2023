package edu.hw3.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task5Test {

    private static Stream<Arguments> contacts() {
        return Stream.of(
            Arguments.of(
                new String[] {
                    "John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"
                },
                "ASC",
                new ArrayList<>(List.of(
                    new Contact("Thomas", "Aquinas"),
                    new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"),
                    new Contact("John", "Locke")
                ))
            ),
            Arguments.of(
                new String[] {
                    "Paul Erdos", "Leonhard Euler", "Carl Gauss"
                },
                "DESC",
                new ArrayList<>(List.of(
                    new Contact("Carl", "Gauss"),
                    new Contact("Leonhard", "Euler"),
                    new Contact("Paul", "Erdos")
                ))
            ),
            Arguments.of(
                new String[] {},
                "DESC",
                new ArrayList<>()
            ),
            Arguments.of(
                null,
                "DESC",
                new ArrayList<>()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("contacts")
    void testParseContacts(String[] names, String order, ArrayList<Contact> expected) {
        assertThat(Task5.parseContacts(names, order)).isEqualTo(expected);
    }

    @Test
    void testIllegalOrders() {
        String order = "SDA";
        String[] names = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        assertThrows(IllegalArgumentException.class, () -> Task5.parseContacts(names, order));
    }
}
