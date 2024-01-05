package edu.hw7.task3;

class SynchronizedPersonDatabaseTest extends PersonDatabaseAbstractTest {

    @Override
    protected PersonDatabase getInstance() {
        return new SynchronizedPersonDatabase();
    }
}
