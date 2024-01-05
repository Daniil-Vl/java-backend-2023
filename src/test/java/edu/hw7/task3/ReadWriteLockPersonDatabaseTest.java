package edu.hw7.task3;

class ReadWriteLockPersonDatabaseTest extends PersonDatabaseAbstractTest {

    @Override
    protected PersonDatabase getInstance() {
        return new ReadWriteLockPersonDatabase();
    }
}
