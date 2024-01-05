package edu.hw10.task2;

import edu.hw10.task2.annotations.Cache;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheInvocationHandlerImpl implements InvocationHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Path dumpDirectory;
    private final Object originalObject;
    private final Map<String, Object> cacheMap;

    public CacheInvocationHandlerImpl(Object originalObject, Path dumpDirectory) throws IOException {
        this.originalObject = originalObject;
        this.dumpDirectory = dumpDirectory;
        this.cacheMap = uploadCacheMapFromDisk();
    }

    private Map<String, Object> uploadCacheMapFromDisk() throws IOException {
        Map<String, Object> resultingMap = new HashMap<>();

        try (Stream<Path> paths = Files.list(dumpDirectory)) {
            paths.forEach(path -> {

                String filename = path.getFileName().toString();
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path.toFile()))) {
                    resultingMap.put(filename, inputStream.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        return resultingMap;
    }

    private void saveResultToDisk(String key, Object result) throws IOException {
        Path filename = dumpDirectory.resolve(key);

        if (!Files.exists(filename)) {
            Files.createFile(filename);
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename.toFile()))) {
            outputStream.writeObject(result);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws InvocationTargetException, IllegalAccessException, IOException {
        if (method.isAnnotationPresent(Cache.class)) {
            String key = method.getName() + Arrays.toString(args);

            if (cacheMap.containsKey(key)) {
                var result = cacheMap.get(key);
                LOGGER.info("Get value {} from cache, using key {}", result, key);
                return result;
            }

            var result = method.invoke(originalObject, args);
            cacheMap.put(key, result);

            if (method.getAnnotation(Cache.class).persist()) {
                saveResultToDisk(key, result);
            }

            return result;
        }

        return method.invoke(originalObject, args);
    }
}
