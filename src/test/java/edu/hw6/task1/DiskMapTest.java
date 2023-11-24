package edu.hw6.task1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiskMapTest {

    private static final DiskMap diskmap = new DiskMap(Map.of(
        "First key", "First value",
        "Second key", "Second value",
        "Third key", "Third value"
    ));

    private static final Path BASE_TEST_PATH = Path.of("src", "test", "resources", "hw6", "task1");

    @AfterAll
    static void removeTempFiles() {
        BASE_TEST_PATH.resolve("nonexistent_file").toFile().delete();
        BASE_TEST_PATH.resolve("saveAndLoad.dat").toFile().delete();
    }

    @Test
    void saveToNonExistentFile() {
        Path path = BASE_TEST_PATH.resolve("nonexistent_file");

        assertDoesNotThrow(() -> diskmap.saveToFile(path.toFile()));
    }

    @Test
    void loadFromNonExistentFile() {
        Path path = BASE_TEST_PATH.resolve("FileNotFound.txt");
        assertThrows(FileNotFoundException.class, () -> diskmap.loadFromFile(path.toFile()));
    }

    @Test
    void saveAndLoad() throws IOException {
        Path path = BASE_TEST_PATH.resolve("saveAndLoad.dat");
        diskmap.saveToFile(path.toFile());

        DiskMap loadedDiskMap = new DiskMap();
        loadedDiskMap.loadFromFile(path.toFile());

        assertThat(loadedDiskMap).isEqualTo(diskmap);
    }

    @Test
    void loadFromPreparedDiskMapDump() throws IOException {
        Path path = BASE_TEST_PATH.resolve("prepared_diskmap_dump.dat");
        DiskMap loadedDiskMap = new DiskMap();
        loadedDiskMap.loadFromFile(path.toFile());
        assertThat(loadedDiskMap).isEqualTo(diskmap);
    }
}
