package edu.hw6.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileClonerTest {

    private static final Path BASE_PATH = Path.of("src", "test", "resources", "hw6", "task2");

    private static Stream<Arguments> provideFilenames() {
        return Stream.of(
            Arguments.of(
                "first.txt", "first — копия.txt"
            ),
            Arguments.of(
                "second.txt", "second — копия (2).txt"
            )
        );
    }

    @BeforeEach
    @AfterEach
    void removeOldTempFiles() throws IOException {
        // Remove files in arguments stream
        Stream<Arguments> filenames = provideFilenames();
        filenames.forEach(args -> {
            Object[] strings = args.get();
            String expectedCopiedFileName = (String) strings[1];
            try {
                Files.deleteIfExists(BASE_PATH.resolve(expectedCopiedFileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Remove nonexistent_file.txt
        Files.deleteIfExists(BASE_PATH.resolve("nonexistent_file.txt"));
    }

    @Test
    void cloneNonExistentFile() {
        Path path = BASE_PATH.resolve("nonexistent_file.txt");
        assertThrows(FileNotFoundException.class, () -> FileCloner.cloneFile(path));
    }

    @ParameterizedTest
    @MethodSource("provideFilenames")
    void cloneFile(String originalFileName, String expectedCopiedFileName) throws IOException {
        Path originalFilePath = BASE_PATH.resolve(originalFileName);
        FileCloner.cloneFile(originalFilePath);

        Path copiedFilePath = BASE_PATH.resolve(expectedCopiedFileName);
        assertThat(copiedFilePath.toFile().exists()).isTrue();

        checkFileEquality(originalFilePath.toFile(), copiedFilePath.toFile());
    }

    void checkFileEquality(File originalFile, File copiedFile) throws IOException {
        try (
            FileInputStream originalFileIn = new FileInputStream(originalFile);
            FileInputStream copiedFileIn = new FileInputStream(copiedFile)
        ) {
            assertThat(copiedFileIn.readAllBytes()).isEqualTo(originalFileIn.readAllBytes());
        }
    }
}
