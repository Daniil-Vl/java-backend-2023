package edu.project3.formatting;

import java.nio.file.Path;

public class MarkdownLogReportFormatterTest extends LogReportFormatterTest {
    @Override
    public LogReportFormatter getInstance() {
        return new MarkdownLogReportFormatter();
    }

    @Override
    public Path getExpectedLogFile() {
        return TEMP_LOG_DIRECTORY.resolve("correct_markdown_report.md");
    }
}
