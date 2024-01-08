package edu.project3.formatting;

import java.nio.file.Path;

public class AdocLogReportFormatterTest extends LogReportFormatterTest {
    @Override
    public LogReportFormatter getInstance() {
        return new AdocLogReportFormatter();
    }

    @Override
    public Path getExpectedLogFile() {
        return TEMP_LOG_DIRECTORY.resolve("correct_adoc_report.adoc");
    }
}
