package edu.hw8.task3;

import java.util.HashMap;
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
    //    private final PasswordGenerator passwordGenerator = new PasswordGenerator();
    private final NewPasswordGenerator newPasswordGenerator = new NewPasswordGenerator();
    private final ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
//    private final ExecutorService executorService = new ThreadPoolExecutor(
//        N_THREADS, N_THREADS,
//        0L, TimeUnit.MILLISECONDS,
//        new LinkedBlockingQueue<Runnable>(4*N_THREADS)
//    );

    @Override
    public Map<String, String> crackPasswords(Map<String, String> hashPersonMap) {
        ConcurrentHashMap<String, String> tempHashPersonMap = new ConcurrentHashMap<>(hashPersonMap);
        ConcurrentHashMap<String, String> resultPersonPasswordMap = new ConcurrentHashMap<>();

        while (!tempHashPersonMap.isEmpty()) {
//        while (resultPersonPasswordMap.size() < hashPersonMap.size()) {
            String password = newPasswordGenerator.getNextPassword();

            if (password == null) {
                continue;
            }

            CompletableFuture.supplyAsync(() -> {
                String hash = MD5HashConverter.getHexPasswordString(password);
                if (tempHashPersonMap.containsKey(hash)) {
//                    LOGGER.info("Found : " + hash);
                    resultPersonPasswordMap.put(tempHashPersonMap.get(hash), password);
                    tempHashPersonMap.remove(hash);
                }
                return null;
            });
        }

        // Version with old password generator
//        for (int i = 0; i < PasswordGenerator.MAX_PASSWORD_LENGTH; i++) {
//            List<String> passwordsBatch = passwordGenerator.generateNextPasswordsBatch();
//            for (String password : passwordsBatch) {
//                executorService.submit(() -> {
//                    String hash = MD5HashConverter.getHexPasswordString(password);
//
//                    if (tempHashPersonMap.containsKey(hash)) {
//                        resultPersonPasswordMap.put(tempHashPersonMap.get(hash), password);
//                        tempHashPersonMap.remove(hash);
//                    }
//
//                    if (tempHashPersonMap.isEmpty()) {
//                        notifyAll();
//                    }
//                });
//            }
//        }

//        while (!tempHashPersonMap.isEmpty()) {
//        }

        executorService.shutdown();

        return new HashMap<>(resultPersonPasswordMap);
    }
}
