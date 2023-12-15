package edu.hw10.task2;

import edu.hw10.task2.annotations.Cache;
import java.nio.file.Path;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

class CacheProxyTest {
    @TempDir
    Path dumpDirectory;
    private int callsNumber = 0;

    @Test
    void inMemoryCachedIncrementorGetCachedValueFromMemory() {
        // * Test here, that with in-memory caching method will not be called two times for same parameters
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
    @Disabled
    void second() {
        // * Test here, that cache with persist will create files and can load from this files
        // * Also add test, that proxy test can upload data from predefined file
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

        // TODO implement here uploading from already created dump directory

    }

    public interface InMemoryCachedIncrementor {
        @Cache
        Integer increment(int num);
    }

    public interface PersistentCachedIncrementor {
        @Cache(persist = true)
        Integer increment(int num);
    }
}
