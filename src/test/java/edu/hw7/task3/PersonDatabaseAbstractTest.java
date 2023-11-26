package edu.hw7.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

abstract class PersonDatabaseAbstractTest {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static Person TARGET_PERSON = new Person(1, "Boris", "Saint-Petersburg", "79999999999");
    private static Thread addThread;
    private static PersonDatabase database;

    abstract protected PersonDatabase getInstance();

    @BeforeEach
    void refreshData() {
        database = getInstance();
        addThread = new Thread(() -> {
            List<Person> personList = List.of(
                TARGET_PERSON,
                new Person(2, "Denis", "Moscow", "78888888888"),
                new Person(3, "Igor", "Moscow", "78888888887"),
                new Person(4, "a", "a", "a")
            );
            try {
                for (Person person : personList) {
                    database.add(person);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "AddThread");
    }

    @RepeatedTest(100)
    void availabilityByAllFields() throws InterruptedException {
        List<Person> foundedByName = new ArrayList<>();
        List<Person> foundedByAddress = new ArrayList<>();
        List<Person> foundedByPhone = new ArrayList<>();

        // Shuffle search attributes, to search by attributes in random order
        Thread searchThread = new Thread(() -> {
            List<SearchAttribute> searchAttributes = new ArrayList<>(List.of(SearchAttribute.values()));
            Collections.shuffle(searchAttributes);
            for (SearchAttribute attribute : searchAttributes) {
                switch (attribute) {
                    case name -> foundedByName.addAll(database.findByName(TARGET_PERSON.name()));
                    case address -> foundedByAddress.addAll(database.findByAddress(TARGET_PERSON.address()));
                    case phone -> foundedByPhone.addAll(database.findByPhone(TARGET_PERSON.phoneNumber()));
                }
            }
        }, "SearchThread");

        addThread.start();
        searchThread.start();

        addThread.join();
        searchThread.join();

        if (foundedByName.isEmpty()) {
            assertThat(foundedByAddress).isEmpty();
            assertThat(foundedByPhone).isEmpty();
            LOGGER.info("Each list empty");
        } else if (foundedByName.contains(TARGET_PERSON)) {
            assertThat(foundedByAddress).contains(TARGET_PERSON);
            assertThat(foundedByPhone).contains(TARGET_PERSON);
            LOGGER.info("Each list contains target person");
        } else {
            LOGGER.error("Test failed");
            assertThat(false).isTrue();
        }
    }

    private enum SearchAttribute {
        name, address, phone;
    }
}
