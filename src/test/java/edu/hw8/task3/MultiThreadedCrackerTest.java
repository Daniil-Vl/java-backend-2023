package edu.hw8.task3;

public class MultiThreadedCrackerTest extends MD5PasswordCrackerTest {
    @Override
    MD5PasswordCracker getInstance() {
        return new MultiThreadCracker();
    }
}

