package edu.hw7.task3;

import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PersonDatabaseImplTest {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static Person PERSON = new Person(1, "Boris", "Saint-Petersburg", "79999999999");
    private static Thread addThread;
    private static PersonDatabase database = new PersonDatabaseImpl();

    /**
     * Refresh database and addThread before each test
     */
    @BeforeEach
    void refreshData() {
        database = new PersonDatabaseImpl();
        addThread = new Thread(() -> {
            try {
                database.add(PERSON);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "AddThread");
    }

    @Test
    void getPersonFromEmptyDatabase() {
        assertThat(database.findByName("Boris")).isNull();
    }

    @Test
    void testAddSynchronization() throws InterruptedException {
        AtomicReference<Person> foundedPerson = new AtomicReference<>();

        Thread searchThread = new Thread(() -> {
            foundedPerson.set(database.findByName("Boris").get(0));
        }, "SearchThread");

        addThread.start();
        searchThread.start();

        addThread.join();
        searchThread.join();

        assertThat(foundedPerson.get()).isEqualTo(PERSON);
        LOGGER.info("Founded person in testAddSynchronization - " + foundedPerson.get().toString());
    }

    /**
     *
     */
    @Test
    void testAvailabilityByAllFields() throws InterruptedException {
        LOGGER.info("testAvailabilityByAllFields =======================================");

        AtomicReference<Person> foundedByName = new AtomicReference<>();
        AtomicReference<Person> foundedByAddress = new AtomicReference<>();
        AtomicReference<Person> foundedByPhoneNumber = new AtomicReference<>();

        Thread searchThread = new Thread(() -> {
            foundedByName.set(database.findByName("Boris").get(0));
            foundedByAddress.set(database.findByAddress("Saint-Petersburg").get(0));
            foundedByPhoneNumber.set(database.findByPhone("79999999999").get(0));
        });

        addThread.start();
        searchThread.start();

        addThread.join();
        searchThread.join();

        LOGGER.info("Founded by: ");
        LOGGER.info("Name: " + foundedByName.get().toString());
        LOGGER.info("Address: " + foundedByAddress.get().toString());
        LOGGER.info("Phone: " + foundedByPhoneNumber.get().toString());

        assertThat(foundedByName.get()).isEqualTo(PERSON);
        assertThat(foundedByAddress.get()).isEqualTo(PERSON);
        assertThat(foundedByPhoneNumber.get()).isEqualTo(PERSON);
        LOGGER.info("===================================================================");
    }
}
