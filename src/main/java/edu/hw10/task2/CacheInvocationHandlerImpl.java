package edu.hw10.task2;

import edu.hw10.task2.annotations.Cache;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheInvocationHandlerImpl implements InvocationHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Path dumpDirectory;
    private Object originalObject;
    private Map<String, Object> cacheMap = new HashMap<>();

    public CacheInvocationHandlerImpl(Object originalObject, Path dumpDirectory) {
        this.originalObject = originalObject;
        this.dumpDirectory = dumpDirectory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws InvocationTargetException, IllegalAccessException {
        if (method.isAnnotationPresent(Cache.class)) {
            String key = method.getName() + Arrays.toString(args);

            LOGGER.warn(
                "Key - {} for method with name - {} and args - {}",
                key,
                method.getName(),
                Arrays.toString(args)
            );

            if (cacheMap.containsKey(key)) {
                var result = cacheMap.get(key);
                LOGGER.warn("Get value {} from cache", result);
                return result;
            }

            var result = method.invoke(originalObject, args);
            cacheMap.put(key, result);

            if (method.getAnnotation(Cache.class).persist()) {
                throw new UnsupportedOperationException("Cannot work with persistent cache yet :(");
            }

            return result;
        }

        return method.invoke(originalObject, args);
    }
}
