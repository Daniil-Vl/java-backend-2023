package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw6.task3.Filters.globMatches;
import static edu.hw6.task3.Filters.hasExtension;
import static edu.hw6.task3.Filters.largerThan;
import static edu.hw6.task3.Filters.magicNumber;
import static edu.hw6.task3.Filters.regexContains;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FiltersTest {

    private static final List<Path> tempFiles = new ArrayList<>();
    @TempDir
    private static Path tempDir;

    @BeforeAll
    static void prepareTempFiles() throws IOException {
        // 0) empty.txt size == 0
        tempFiles.add(
            Files.createTempFile(tempDir, "empty", ".txt")
        );

        // 1) Png file with magic numbers, size > 0
        tempFiles.add(Files.write(
            Files.createTempFile(tempDir, "picture", ".png"),
            new byte[] {(byte) 0x89, 'P', 'N', 'G'}
        ));

        // 2) Png file without magic numbers, size > 0
        tempFiles.add(Files.write(
            Files.createTempFile(tempDir, "invalid_png", ".png"),
            new byte[] {'a', 'b', 'c', 'd', 'e'}
        ));
    }

    private static Stream<Arguments> provideFiltersAndListsOfFiles() {
        return Stream.of(
            arguments(
                named("largerThan test", largerThan(1)),
                List.of(tempFiles.get(1), tempFiles.get(2))
            ),
            arguments(
                named("globMatches test", globMatches("**.txt")),
                List.of(tempFiles.get(0))
            ),
            arguments(
                named("hasExtension test", hasExtension(Set.of("png"))),
                List.of(tempFiles.get(1), tempFiles.get(2))
            ),
            arguments(
                named("Magic Number test", magicNumber(0x89, 'P', 'N', 'G')),
                List.of(tempFiles.get(1))
            ),
            arguments(
                named("Regex Contains test", regexContains("[i]+")),
                List.of(tempFiles.get(1), tempFiles.get(2))
            )
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideFiltersAndListsOfFiles")
    void filterTest(AbstractFilter filter, List<Path> expectedFilteredFiles) throws IOException {
        List<Path> filteredFiles = new ArrayList<>();

        try (
            DirectoryStream<Path> entries = Files.newDirectoryStream(tempDir, filter)
        ) {
            entries.forEach(filteredFiles::add);
        }

        assertThat(filteredFiles).containsExactlyInAnyOrderElementsOf(expectedFilteredFiles);
    }

    @Test
    void multipleFiltersInChain() throws IOException {
        List<Path> filteredFiles = new ArrayList<>();
        AbstractFilter filter = largerThan(1)
            .and(hasExtension(Set.of("png")))
            .and(magicNumber(0x89, 'P', 'N', 'G'));

        try (
            DirectoryStream<Path> entries = Files.newDirectoryStream(tempDir, filter)
        ) {
            entries.forEach(filteredFiles::add);
        }

        assertThat(filteredFiles).containsExactlyInAnyOrderElementsOf(List.of(tempFiles.get(1)));
    }
}
