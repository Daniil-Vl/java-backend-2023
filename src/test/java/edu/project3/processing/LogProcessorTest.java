package edu.project3.processing;

import edu.project3.commandLine.LogProcessorArguments;
import edu.project3.commandLine.OutputFormat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import static org.assertj.core.api.Assertions.assertThat;

class LogProcessorTest {

    @Test
    void processLogs() throws IOException {
        LogProcessorArguments arguments = new LogProcessorArguments(
            List.of("src/test/resources/project3/logs/logs_example.txt"),
            null,
            null,
            OutputFormat.MARKDOWN
        );

        LogReport actual = LogProcessor.processLogs(arguments);

        PriorityQueue<Map.Entry<String, Integer>> expectedRequestedResources =
            new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        expectedRequestedResources.addAll(Map.of(
            "/downloads/product_1", 1,
            "/downloads/product_2", 2
        ).entrySet());

        PriorityQueue<Map.Entry<Integer, Integer>> expectedStatusCodes =
            new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        expectedStatusCodes.addAll(Map.of(
            404, 1,
            304, 2
        ).entrySet());

        LogReport expected = new LogReport(
            List.of("src\\test\\resources\\project3\\logs\\logs_example.txt"),
            OffsetDateTime.MIN,
            OffsetDateTime.MAX,
            3,
            112,
            expectedRequestedResources,
            expectedStatusCodes
        );

        assertThat(actual).isEqualTo(expected);
    }
}
