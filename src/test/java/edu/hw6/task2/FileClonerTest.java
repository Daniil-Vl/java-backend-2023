package edu.hw6.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileClonerTest {

    private static final Path BASE_PATH = Path.of("src", "test", "resources", "task2");

    /**
     * Return stream of arguments (pairs like this (originalFileName, expectedCopiedFileName) )
     */
    private static Stream<Arguments> filenames() {
        return Stream.of(
            Arguments.of(
                "first.txt", "first — копия.txt"
            ),
            Arguments.of(
                "second.txt", "second — копия (2).txt"
            )
        );
    }

    @Test
    void testCloneNonExistentFile() {
        Path path = BASE_PATH.resolve("nonexistentfile.txt");
        assertThrows(FileNotFoundException.class, () -> FileCloner.cloneFile(path));
    }

    @ParameterizedTest
    @MethodSource("filenames")
    void testFirstClone(String originalFileName, String expectedCopiedFileName) throws IOException {
        // Remove old files
        BASE_PATH.resolve(expectedCopiedFileName).toFile().delete();

        // Check that file creation
        Path originalFilePath = BASE_PATH.resolve(originalFileName);
        FileCloner.cloneFile(originalFilePath);

        Path copiedFilePath = BASE_PATH.resolve(expectedCopiedFileName);
        assertThat(copiedFilePath.toFile().exists()).isTrue();

        checkFileEquality(originalFilePath.toFile(), copiedFilePath.toFile());
    }

    /**
     * Checks, that files content equality
     */
    void checkFileEquality(File originalFile, File copiedFile) throws IOException {
        FileInputStream originalFileIn = new FileInputStream(originalFile);
        FileInputStream copiedFileIn = new FileInputStream(copiedFile);

        assertThat(copiedFileIn.readAllBytes()).isEqualTo(originalFileIn.readAllBytes());

        originalFileIn.close();
        copiedFileIn.close();
    }
}
