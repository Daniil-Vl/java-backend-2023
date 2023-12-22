package edu.hw8.task3;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultiThreadCracker implements MD5PasswordCracker {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int N_THREADS = Runtime.getRuntime().availableProcessors();
    private final PasswordGenerator passwordGenerator = new PasswordGenerator();
    private final ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

    @SuppressWarnings("MagicNumber")
    @Override
    public Map<String, String> crackPasswords(Map<String, String> hashPersonMap) {
        ConcurrentHashMap<String, String> tempHashPersonMap = new ConcurrentHashMap<>(hashPersonMap);
        ConcurrentHashMap<String, String> resultPersonPasswordMap = new ConcurrentHashMap<>();
        BlockingQueue<List<String>> passwordBatches = new ArrayBlockingQueue<>(N_THREADS * 4);

        final long startTime = System.nanoTime();

        // Start executing threads,
        // which will be taking password batches from blocking queue and process them
        for (int i = 0; i < N_THREADS; i++) {
            executorService.submit(() -> {
                while (true) {
                    List<String> passwordsBatch;

                    try {
                        passwordsBatch = passwordBatches.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (passwordsBatch == null || tempHashPersonMap.isEmpty()) {
                        return;
                    }

                    for (String password : passwordsBatch) {
                        String hash = MD5HashConverter.getHashHexString(password);
                        if (tempHashPersonMap.containsKey(hash)) {
                            resultPersonPasswordMap.put(tempHashPersonMap.get(hash), password);
                            tempHashPersonMap.remove(hash);
                            LOGGER.info(
                                "Found {} password with time - {} milliseconds",
                                password,
                                Duration.ofNanos(System.nanoTime() - startTime).toMillis()
                            );
                        }
                    }
                }
            });
        }

        while (!tempHashPersonMap.isEmpty()) {
            try {
                passwordBatches.put(passwordGenerator.getNextPasswordBatch());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();

        return new HashMap<>(resultPersonPasswordMap);
    }
}
