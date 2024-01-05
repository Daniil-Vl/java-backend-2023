package edu.hw9.task2;

import edu.hw9.task2.filepredicates.ExtensionPredicate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

class PredicateFilesFilterTest {

    private final List<Path> expectedFiles = new ArrayList<>();
    @TempDir
    private Path tempDir;
    private Path initialDirectory;

    @BeforeEach
    void init() throws IOException {
        initialDirectory = Files.createTempDirectory(tempDir, "init");

        var innerDir = Files.createTempDirectory(initialDirectory, "innerDir");

        expectedFiles.add(Files.createTempFile(initialDirectory, "first", ".txt"));
        expectedFiles.add(Files.createTempFile(initialDirectory, "second", ".txt"));
        expectedFiles.add(Files.createTempFile(innerDir, "third", ".txt"));

        Files.createTempFile(initialDirectory, "fourth", ".bat");
        Files.createTempFile(initialDirectory, "fifth", ".dat");
        Files.createTempFile(innerDir, "sixth", ".dat");
        Files.createTempFile(innerDir, "seventh", ".bat");
    }

    @Test
    void findFilesUsingExtensionPredicate() {
        Predicate<Path> predicate = new ExtensionPredicate("txt");
        PredicateFilesFilter filesFilter = new PredicateFilesFilter();

        var res = filesFilter.findFilesUsingPredicate(initialDirectory, predicate);

        assertThat(res).hasSameElementsAs(expectedFiles);
    }
}
