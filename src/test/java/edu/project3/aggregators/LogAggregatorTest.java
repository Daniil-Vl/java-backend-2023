package edu.project3.aggregators;

import edu.project3.logs.LogRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LogAggregatorTest {

    private static final LogAggregator logAggregator = new LogAggregator(
        Path.of("src", "test", "")
    );

    @Test
    void testGetLogsByPath() throws IOException {
        Path path = Path.of("src", "test", "resources", "project3", "logs", "logs_example.txt");

        Stream<LogRecord> actual = logAggregator.getLogs(path);
        Stream<LogRecord> expected = Stream.of(
            new LogRecord(
                "93.180.71.3",
                null,
                OffsetDateTime.of(2015, 5, 17, 8, 5, 58, 0, ZoneOffset.ofTotalSeconds(0)),
                "GET /downloads/product_1 HTTP/1.1",
                404,
                337,
                null,
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
            ),

            new LogRecord(
                "62.75.198.179",
                null,
                OffsetDateTime.of(2015, 5, 17, 8, 5, 39, 0, ZoneOffset.ofTotalSeconds(0)),
                "GET /downloads/product_2 HTTP/1.1",
                304,
                0,
                null,
                "Debian APT-HTTP/1.3 (0.9.7.9)"
            ),

            new LogRecord(
                "50.57.209.92",
                null,
                OffsetDateTime.of(2015, 5, 17, 8, 5, 41, 0, ZoneOffset.ofTotalSeconds(0)),
                "GET /downloads/product_2 HTTP/1.1",
                304,
                0,
                null,
                "Debian APT-HTTP/1.3 (0.9.7.9)"
            )
        );

        assertThat(actual.toList()).isEqualTo(expected.toList());
    }
}
