package edu.hw8.task2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int TIMEOUT = 100;
    private final BlockingQueue<Runnable> tasksQueue = new ArrayBlockingQueue<>(8);
    private Thread[] threads;

    private FixedThreadPool(int nThreads) {
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
        LOGGER.info("FixedThreadPool starts working...");
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (tasksQueue) {
            try {
                tasksQueue.put(runnable);
                LOGGER.info("New task was putted to queue");
                tasksQueue.notify();
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

        LOGGER.info("FixedThreadPool closing...");
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = tasksQueue.take();
                    LOGGER.info("Worker %s starts executing new task".formatted(Thread.currentThread().getName()));
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
