package edu.hw6.task1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
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

    private static final Path BASE_TEST_PATH = Path.of("src", "test", "resources");

    @Test
    void saveToNonExistentFile() {
        Path path = BASE_TEST_PATH.resolve("nonexistent_file");
        assertDoesNotThrow(() -> diskmap.saveToFile(path.toFile()));
    }

    @Test
    void loadFromFile() {
        Path path = BASE_TEST_PATH.resolve("FileNotFound.txt");
        assertThrows(FileNotFoundException.class, () -> diskmap.loadFromFile(path.toFile()));
    }

    @Test
    void testAndLoadEmpty() throws IOException {
        Path path = BASE_TEST_PATH.resolve("empty_diskMap.dat");
        DiskMap empty = new DiskMap();
        empty.saveToFile(path.toFile());

        DiskMap loadedDiskMap = new DiskMap();
        loadedDiskMap.loadFromFile(path.toFile());

        assertThat(loadedDiskMap).isEqualTo(empty);
    }

    @Test
    void testSaveAndLoad() throws IOException {
        Path path = BASE_TEST_PATH.resolve("common_dump.dat");
        diskmap.saveToFile(path.toFile());

        DiskMap loadedDiskMap = new DiskMap();
        loadedDiskMap.loadFromFile(path.toFile());

        assertThat(loadedDiskMap).isEqualTo(diskmap);
    }
}
