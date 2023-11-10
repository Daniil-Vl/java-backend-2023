package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AbstractFilterTest {

    /**
     * Filter, which accept readable, writeable files without magic initial identifiers with extension .txt or .dat
     * corresponding to the format "*.txt" and containing digits in filename with size more than 1 byte
     */
    private static final DirectoryStream.Filter<Path> filter = new ReadableFilter()
        .and(new WriteableFilter())
        .and(new LargerThanFilter(1))
        .and(new MagicInitialIdentifiers())
        .and(new GlobMatchFilter("*.txt"))
        .and(new FileExtensionFilter(List.of("txt", "dat")))
        .and(new RegExContain("\\d+"));

    @Test
    void testFilterChain() {
        Path path = Path.of("src", "test", "resources", "task3");
        List<String> expectedFilteredFilesList = new ArrayList<>(List.of("numeric_file_123.txt"));

        List<String> actualFilteredFilesList = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(path, filter)) {
            for (Path p : entries) {
                actualFilteredFilesList.add(p.getFileName().toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(actualFilteredFilesList).isEqualTo(expectedFilteredFilesList);
    }
}
