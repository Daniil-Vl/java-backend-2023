package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int TIMEOUT = 100;
    private final BlockingQueue<Runnable> tasksQueue = new LinkedBlockingQueue<>(8);
    private final Thread[] threads;

    private FixedThreadPool(int nThreads) {
        if (nThreads <= 0) {
            throw new IllegalArgumentException("Number of thread must be positive");
        }

        this.threads = new Thread[nThreads];

        for (int i = 0; i < nThreads; i++) {
            this.threads[i] = new Worker();
        }
    }

    public static ThreadPool create(int nThreads) {
        return new FixedThreadPool(nThreads);
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (tasksQueue) {
            try {
                tasksQueue.put(runnable);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() throws Exception {
        // Wait until there no tasks left
        while (!tasksQueue.isEmpty()) {
        }

        for (Thread thread : threads) {
            thread.join(TIMEOUT);
            thread.interrupt();
        }

        LOGGER.info("FixedThreadPool stops...");
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = tasksQueue.take();
                    LOGGER.info("Worker starts executing task");
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
