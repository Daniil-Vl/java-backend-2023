package edu.project3.formatting;

import java.util.List;

public class MarkdownLogReportFormatter extends LogReportFormatter {

    public MarkdownLogReportFormatter() {
        headerSymbols = "####";
        separator = "|";
    }

    @Override
    protected String renderTable(List<String> headers, List<List<String>> rows) {
        StringBuilder tableStringBuilder = new StringBuilder();

        // Render headers of the table
        tableStringBuilder
            .append(separator)
            .append(String.join(separator, headers))
            .append(separator)
            .append("\n");

        // Add string after headers
        for (int i = 0; i < headers.size() - 1; i++) {
            tableStringBuilder.append(separator).append(":-:");
        }
        tableStringBuilder.append(separator).append("-:").append(separator).append(lineSeparator);

        // Add table rows
        for (List<String> row : rows) {
            for (String cell : row) {
                tableStringBuilder.append(separator).append(cell);
            }
            tableStringBuilder.append(separator).append(lineSeparator);
        }

        tableStringBuilder.append(lineSeparator);

        return tableStringBuilder.toString();
    }
}
