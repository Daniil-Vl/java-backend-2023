package edu.hw8.task3;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
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

    @Override
    public Map<String, String> crackPasswords(Map<String, String> hashPersonMap) {
        ConcurrentHashMap<String, String> tempHashPersonMap = new ConcurrentHashMap<>(hashPersonMap);
        ConcurrentHashMap<String, String> resultPersonPasswordMap = new ConcurrentHashMap<>();

        final long startTime = System.nanoTime();

        while (!tempHashPersonMap.isEmpty()) {
            List<String> passwordsBatch = passwordGenerator.getNextPasswordBatch();

            if (passwordsBatch == null) {
                break;
            }

            CompletableFuture.runAsync(() -> {
                if (tempHashPersonMap.isEmpty()) {
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
            }, executorService);
        }

        executorService.shutdown();

        return new HashMap<>(resultPersonPasswordMap);
    }
}
