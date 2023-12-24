package edu.hw10.task2;

import edu.hw10.task2.annotations.Cache;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

class CacheProxyTest {
    @TempDir
    Path dumpDirectory;
    private int callsNumber = 0;

    @Test
    void inMemoryCachedIncrementorGetCachedValueFromMemory() throws IOException {
        InMemoryCachedIncrementor cachedIncrementor = new InMemoryCachedIncrementor() {
            @Override
            public Integer increment(int num) {
                callsNumber++;
                return num + 1;
            }
        };

        InMemoryCachedIncrementor proxy = CacheProxy.create(
            cachedIncrementor,
            InMemoryCachedIncrementor.class,
            dumpDirectory
        );

        assertThat(proxy.increment(10)).isEqualTo(11);
        assertThat(proxy.increment(10)).isEqualTo(11);
        assertThat(callsNumber).isEqualTo(1);
    }

    @Test
    void persistentCachedIncrementorGetCachedValueFromDisk() throws IOException {
        callsNumber = 0;

        PersistentCachedIncrementor cachedIncrementor = new PersistentCachedIncrementor() {
            @Override
            public Integer increment(int num) {
                callsNumber++;
                return num + 1;
            }
        };

        PersistentCachedIncrementor proxy = CacheProxy.create(
            cachedIncrementor,
            PersistentCachedIncrementor.class,
            dumpDirectory
        );

        assertThat(proxy.increment(10)).isEqualTo(11);

        PersistentCachedIncrementor uploadedProxy = CacheProxy.create(
            cachedIncrementor,
            PersistentCachedIncrementor.class,
            dumpDirectory
        );

        assertThat(uploadedProxy.increment(10)).isEqualTo(11);
        assertThat(callsNumber).isEqualTo(1);
    }

    public interface InMemoryCachedIncrementor {
        @Cache(persist = false)
        Integer increment(int num);
    }

    public interface PersistentCachedIncrementor {
        @Cache(persist = true)
        Integer increment(int num);
    }
}
