package edu.hw10.task2;

import java.lang.reflect.Proxy;
import java.nio.file.Path;

public class CacheProxy {
    // TODO implement create method
    @SuppressWarnings("unchecked")
    public static <T> T create(T object, Class<T> cls, Path dumpFileDirectory) {
        return (T) Proxy.newProxyInstance(
            cls.getClassLoader(),
            new Class[] {cls},
            new CacheInvocationHandlerImpl(object, dumpFileDirectory)
        );
    }
}
