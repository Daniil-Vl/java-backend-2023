package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

class LargeDirectoriesFilterTest {

    @TempDir
    private Path tempDir;

    private List<Path> expectedDirectories = new ArrayList<>();

    private Path initialDirectory;

    /**
     * Generate temp directory structure:
     */
    @BeforeEach
    void init() throws IOException {
        initialDirectory = Files.createTempDirectory(tempDir, "init");

        var init_inner_1 = Files.createTempDirectory(initialDirectory, "init_inner_1");
        var init_inner_inner = Files.createTempDirectory(init_inner_1, "init_inner_inner");

        Files.createTempFile(init_inner_1, "", "");
        Files.createTempFile(init_inner_1, "", "");
        Files.createTempFile(init_inner_1, "", "");
        Files.createTempFile(init_inner_1, "", "");

        Files.createTempFile(init_inner_inner, "", "");
        Files.createTempFile(init_inner_inner, "", "");
        Files.createTempFile(init_inner_inner, "", "");
        Files.createTempFile(init_inner_inner, "", "");
        Files.createTempFile(init_inner_inner, "", "");

        expectedDirectories.add(init_inner_1);
        expectedDirectories.add(init_inner_inner);

        var second = Files.createTempDirectory(initialDirectory, "second");
        Files.createTempFile(second, "", "");

        var second_inner_1 = Files.createTempDirectory(second, "second_inner_1");
        Files.createTempFile(second_inner_1, "", "");
        Files.createTempFile(second_inner_1, "", "");

        var second_inner_2 = Files.createTempDirectory(second, "second_inner_2");
        var second_inner_3 = Files.createTempDirectory(second, "second_inner_3");

        // Create deeply nested directories
        var currDir = second;
        for (int i = 0; i < 10; i++) {
            currDir = Files.createTempDirectory(currDir, Integer.toString(i));
        }
    }

    @Test
    void findLargeDirs() {
        LargeDirectoriesFilter filter = new LargeDirectoriesFilter();
        var res = filter.findLargeDirs(initialDirectory, 3);

        assertThat(res).hasSameElementsAs(expectedDirectories);
    }
}
