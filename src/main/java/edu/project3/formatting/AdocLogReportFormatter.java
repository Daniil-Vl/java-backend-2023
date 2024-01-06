package edu.project3.formatting;

import java.util.List;

public class AdocLogReportFormatter extends LogReportFormatter {
    private final String tableLimiter = "|====";

    public AdocLogReportFormatter() {
        headerSymbols = "====";
    }

    @Override
    protected String renderTable(List<String> headers, List<List<String>> rows) {
        StringBuilder tableStringBuilder = new StringBuilder();

        // Add headers
        tableStringBuilder.append(tableLimiter).append(lineSeparator);
        for (String header : headers) {
            tableStringBuilder.append("|").append(header);
        }
        tableStringBuilder.append(lineSeparator);

        // Add table rows
        for (List<String> row : rows) {
            for (String cell : row) {
                tableStringBuilder.append("|").append(cell).append(lineSeparator);
            }
            tableStringBuilder.append(lineSeparator);
        }
        tableStringBuilder.append(tableLimiter).append(lineSeparator).append(lineSeparator);

        return tableStringBuilder.toString();
    }
}
