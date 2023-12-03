package edu.hw8.task3;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SingleThreadCracker implements MD5PasswordCracker {
    private static final Logger LOGGER = LogManager.getLogger();
    private final PasswordGenerator passwordGenerator = new PasswordGenerator();

    @Override public Map<String, String> crackPasswords(Map<String, String> hashPersonMap) {
        HashMap<String, String> tempHashPersonMap = new HashMap<>(hashPersonMap);
        HashMap<String, String> resultPersonPasswordMap = new HashMap<>();

        final long startTime = System.nanoTime();

        while (!tempHashPersonMap.isEmpty()) {
            List<String> passwordsBatch = passwordGenerator.getNextPasswordBatch();

            if (passwordsBatch == null) {
                break;
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

        return resultPersonPasswordMap;
    }
}
