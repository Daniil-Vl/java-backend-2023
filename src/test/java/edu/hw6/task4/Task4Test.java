package edu.hw6.task4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {

    @Test
    void testStreamCompositionWrite() {
        String expectedText = "Programming is learned by writing programs. ― Brian Kernighan";
        Path path = Path.of("src", "test", "resources", "task4", "task4.txt");

        Task4.streamCompositionWrite(path);

        File file = path.toFile();
        assertThat(file.exists()).isTrue();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            assertThat(fileInputStream.readAllBytes()).isEqualTo(
                expectedText.getBytes(StandardCharsets.UTF_8)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
