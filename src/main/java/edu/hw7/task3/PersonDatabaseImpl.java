package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonDatabaseImpl implements PersonDatabase {
    // This field is needed for thread sleep in add method
    private final static int SLEEP_TIME = 1000;
    private final static Logger LOGGER = LogManager.getLogger();
    private final HashMap<Integer, Person> idMap = new HashMap<>();
    private final HashMap<String, List<Person>> nameMap = new HashMap<>();
    private final HashMap<String, List<Person>> addressMap = new HashMap<>();
    private final HashMap<String, List<Person>> phoneMap = new HashMap<>();

    /**
     * Add person to database
     *
     * @param person - person to add
     */
    @Override
    public synchronized void add(Person person) throws InterruptedException {
        idMap.put(person.id(), person);

        List<Person> temp;
        temp = nameMap.get(person.name());
        if (temp != null) {
            temp.add(person);
        } else {
            nameMap.put(person.name(), new ArrayList<>(List.of(person)));
        }

        // Stop thread to demonstrate threads synchronization
        Thread.sleep(SLEEP_TIME);

        temp = addressMap.get(person.address());
        if (temp != null) {
            temp.add(person);
        } else {
            addressMap.put(person.address(), new ArrayList<>(List.of(person)));
        }

        // Stop thread to demonstrate threads synchronization
        Thread.sleep(SLEEP_TIME);

        temp = phoneMap.get(person.address());
        if (temp != null) {
            temp.add(person);
        } else {
            phoneMap.put(person.phoneNumber(), new ArrayList<>(List.of(person)));
        }

        LOGGER.info("Added - " + person);
    }

    /**
     * Remove user with given id from database
     *
     * @param id - if of user to remove
     */
    @Override
    public synchronized void delete(int id) {
        Person person = idMap.get(id);
        nameMap.get(person.name()).remove(person);
        addressMap.get(person.address()).remove(person);
        phoneMap.get(person.phoneNumber()).remove(person);
    }

    /**
     * Find users by name in database
     * Return persons, only if them can be founded by name, address and phone
     *
     * @param name - person's name
     * @return list of persons
     */
    @Override
    public synchronized List<Person> findByName(String name) {
        return nameMap.get(name);
    }

    /**
     * Find users by address in database
     * Return persons, only if them can be founded by name, address and phone
     *
     * @param address - person's address
     * @return list of persons
     */
    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressMap.get(address);
    }

    /**
     * Find users by phone in database
     * Return persons, only if them can be founded by name, address and phone
     *
     * @param phone - person's phone
     * @return list of persons
     */
    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneMap.get(phone);
    }
}
