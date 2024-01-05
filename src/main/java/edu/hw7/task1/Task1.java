package edu.hw7.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1 {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int THREADS_NUMBER = 100;

    /**
     * Return 100 * number, calculated with multithreading
     * Method call incrementation operator 'number' times in 100 threads
     *
     * @param number - input number
     * @return 100 * number
     */
    public int multiThreadCount(int number) {
        List<Thread> threads = new ArrayList<>();
        Counter counter = new Counter();

        for (int i = 0; i < THREADS_NUMBER; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < number; j++) {
                    counter.increment();
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }

        return counter.getCount();
    }

    static class Counter {
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            this.count.incrementAndGet();
        }

        public int getCount() {
            return this.count.get();
        }
    }
}
