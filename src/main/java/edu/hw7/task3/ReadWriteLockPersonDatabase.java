package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPersonDatabase implements PersonDatabase {
    private final HashMap<Integer, Person> idMap = new HashMap<>();
    private final HashMap<String, List<Person>> nameMap = new HashMap<>();
    private final HashMap<String, List<Person>> addressMap = new HashMap<>();
    private final HashMap<String, List<Person>> phoneMap = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    @Override
    public void add(Person person) throws InterruptedException {
        lock.writeLock().lock();
        try {
            idMap.put(person.id(), person);
            nameMap.computeIfAbsent(person.name(), key -> new ArrayList<>()).add(person);
            addressMap.computeIfAbsent(person.address(), key -> new ArrayList<>()).add(person);
            phoneMap.computeIfAbsent(person.phoneNumber(), key -> new ArrayList<>()).add(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = idMap.get(id);
            idMap.remove(id);
            nameMap.get(person.name()).remove(person);
            addressMap.get(person.address()).remove(person);
            phoneMap.get(person.phoneNumber()).remove(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        List<Person> searchResult;
        lock.readLock().lock();
        try {
            searchResult = nameMap.get(name);
        } finally {
            lock.readLock().unlock();
        }
        return searchResult;
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<Person> searchResult;
        lock.readLock().lock();
        try {
            searchResult = addressMap.get(address);
        } finally {
            lock.readLock().unlock();
        }
        return searchResult;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        List<Person> searchResult;
        lock.readLock().lock();
        try {
            searchResult = phoneMap.get(phone);
        } finally {
            lock.readLock().unlock();
        }
        return searchResult;
    }
}
