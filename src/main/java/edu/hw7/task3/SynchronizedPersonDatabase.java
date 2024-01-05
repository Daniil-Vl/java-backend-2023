package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SynchronizedPersonDatabase implements PersonDatabase {
    private final HashMap<Integer, Person> idMap = new HashMap<>();
    private final HashMap<String, List<Person>> nameMap = new HashMap<>();
    private final HashMap<String, List<Person>> addressMap = new HashMap<>();
    private final HashMap<String, List<Person>> phoneMap = new HashMap<>();

    @Override
    public synchronized void add(Person person) throws InterruptedException {
        idMap.put(person.id(), person);
        nameMap.computeIfAbsent(person.name(), key -> new ArrayList<>()).add(person);
        addressMap.computeIfAbsent(person.address(), key -> new ArrayList<>()).add(person);
        phoneMap.computeIfAbsent(person.phoneNumber(), key -> new ArrayList<>()).add(person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idMap.get(id);
        idMap.remove(id);
        nameMap.get(person.name()).remove(person);
        addressMap.get(person.address()).remove(person);
        phoneMap.get(person.phoneNumber()).remove(person);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameMap.get(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressMap.get(address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneMap.get(phone);
    }
}
