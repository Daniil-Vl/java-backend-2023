package edu.project3.formatting;

import edu.project3.processing.LogReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

abstract class LogReportFormatterTest {
    protected static final Path TEMP_LOG_DIRECTORY = Path.of("src", "test", "resources", "project3", "formatting");
    private static LogReport logReport;
    private LogReportFormatter formatter;
    private Path tempLogFile;
    private Path expectedLogFile;

    public static void createLogReport() {
        PriorityQueue<Map.Entry<String, Integer>> requestedResources =
            new PriorityQueue<>((first, second) -> Integer.compare(second.getValue(), first.getValue()));

        requestedResources.addAll(new HashMap<>(Map.of(
            "first.txt", 3,
            "second.txt", 2
        )).entrySet());

        PriorityQueue<Map.Entry<Integer, Integer>> statusCodes =
            new PriorityQueue<>((first, second) -> Integer.compare(second.getValue(), first.getValue()));

        statusCodes.addAll(new HashMap<>(Map.of(
            200, 3,
            404, 2
        )).entrySet());

        logReport = new LogReport(
            List.of("logs.txt"),
            OffsetDateTime.MIN,
            OffsetDateTime.MAX,
            10,
            100,
            requestedResources,
            statusCodes
        );
    }

    @BeforeEach
    void init() throws IOException {
        formatter = getInstance();
        createLogReport();
        saveLogReportToFile();
        expectedLogFile = getExpectedLogFile();
    }

    public abstract LogReportFormatter getInstance();

    public abstract Path getExpectedLogFile();

    /**
     * Dumps log report to file using certain instance of LogReportFormatter
     */
    void saveLogReportToFile() throws IOException {
        tempLogFile = Files.createTempFile(TEMP_LOG_DIRECTORY, "tempLog", "_temp");
        tempLogFile.toFile().deleteOnExit();
        formatter.saveLogReport(logReport, tempLogFile);
    }

    @Test
    void isFormatValid() throws IOException {
        assertThat(
            Files.readAllLines(tempLogFile)
        ).isEqualTo(
            Files.readAllLines(expectedLogFile)
        );
    }
}
