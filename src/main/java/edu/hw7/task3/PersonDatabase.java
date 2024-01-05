package edu.hw7.task3;

import java.util.List;

public interface PersonDatabase {
    /**
     * Add person to database
     *
     * @param person - person to add
     */
    void add(Person person) throws InterruptedException;

    /**
     * Remove user with given id from database
     *
     * @param id - if of user to remove
     */
    void delete(int id);

    /**
     * Find users by name in database
     * Return persons, only if them can be founded by name, address and phone
     *
     * @param name - person's name
     * @return list of persons
     */
    List<Person> findByName(String name);

    /**
     * Find users by address in database
     * Return persons, only if them can be founded by name, address and phone
     *
     * @param address - person's address
     * @return list of persons
     */
    List<Person> findByAddress(String address);

    /**
     * Find users by phone in database
     * Return persons, only if them can be founded by name, address and phone
     *
     * @param phone - person's phone
     * @return list of persons
     */
    List<Person> findByPhone(String phone);
}
