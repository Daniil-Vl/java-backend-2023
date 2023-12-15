package edu.hw10.task2;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.nio.file.Path;

public class CacheProxy {
    private CacheProxy() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T object, Class<T> cls, Path dumpFileDirectory) throws IOException {
        return (T) Proxy.newProxyInstance(
            cls.getClassLoader(),
            new Class[] {cls},
            new CacheInvocationHandlerImpl(object, dumpFileDirectory)
        );
    }
}
