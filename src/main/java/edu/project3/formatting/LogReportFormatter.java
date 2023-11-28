package edu.project3.formatting;

import edu.project3.processing.LogReport;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@SuppressWarnings("MultipleStringLiterals")
public abstract class LogReportFormatter {
    private static final Map<Integer, String> STATUS_CODE_NAMES = Map.of(
        200, "OK",
        404, "Not Found",
        500, "Internal Server Error"
    );
    protected final String lineSeparator = System.lineSeparator();
    protected String separator;
    protected String headerSymbols;

    private String renderHeader(String header) {
        return headerSymbols + " " + header + lineSeparator;
    }

    protected abstract String renderTable(List<String> headers, List<List<String>> rows);

    private String renderGeneralInformation(LogReport logReport) {
        return renderHeader("Общая информация")
            + renderTable(
            List.of("Метрика", "Значение"),
            List.of(
                List.of("Файл(-ы)", String.join(", ", logReport.files())),
                List.of("Начальная дата", logReport.startDate() != null ? logReport.startDate().toString() : "-"),
                List.of("Конечная дата", logReport.endDate() != null ? logReport.endDate().toString() : "-"),
                List.of("Количество запросов", logReport.numberOfRequests().toString()),
                List.of("Средний размер ответа", logReport.averageResponseSize().toString())
            )
        );
    }

    private String renderRequestedResources(LogReport logReport) {
        List<List<String>> rows = new ArrayList<>();

        var requestedSources = logReport.requestedSources();

        while (!requestedSources.isEmpty()) {
            var entry = requestedSources.poll();
            rows.add(List.of(entry.getKey(), entry.getValue().toString()));
        }

        return renderHeader("Запрашиваемые ресурсы")
            + renderTable(
            List.of("Ресурс", "Количество"),
            rows
        );
    }

    private String renderStatusCodes(LogReport logReport) {
        List<List<String>> rows = new ArrayList<>();

        PriorityQueue<Map.Entry<Integer, Integer>> statusCodes = logReport.statusCodes();

        while (!statusCodes.isEmpty()) {
            Map.Entry<Integer, Integer> entry = statusCodes.poll();
            rows.add(
                List.of(
                    entry.getKey().toString(),
                    STATUS_CODE_NAMES.getOrDefault(entry.getKey(), "unknown"),
                    entry.getValue().toString()
                )
            );
        }

        return renderHeader("Коды ответа")
            + renderTable(
            List.of("Код", "Имя", "Количество"),
            rows
        );
    }

    public void saveLogReport(LogReport logReport, Path file) throws IOException {
        try (
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))
        ) {
            writer.write(renderGeneralInformation(logReport));
            writer.write(renderRequestedResources(logReport));
            writer.write(renderStatusCodes(logReport));
        }
    }
}
