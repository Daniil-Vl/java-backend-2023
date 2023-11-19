package edu.project3.processing;

import edu.project3.aggregators.LogAggregator;
import edu.project3.commandLine.LogProcessorArguments;
import edu.project3.logs.LogRecord;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LogProcessor {
    private LogProcessor() {
    }

    /**
     * Process logs gathered using given arguments
     *
     * @param arguments - arguments parsed from command line
     * @return LogReport for given nginx logs
     * @throws IOException if an I/O error is thrown when accessing the starting file on some path.
     */
    public static LogReport processLogs(LogProcessorArguments arguments) throws IOException {
        LogDataAccumulator logDataAccumulator = new LogDataAccumulator();

        // Set dates
        logDataAccumulator.setStartDate(arguments.startDate());
        logDataAccumulator.setEndDate(arguments.endDate());

        // Find all given text files with logs
        List<String> logFilesPaths = new ArrayList<>();

        for (String pathStr : arguments.paths()) {
            logFilesPaths.addAll(getPaths(pathStr));
        }
        logDataAccumulator.setFiles(logFilesPaths);

        LogAggregator logAggregator =
            new LogAggregator(Path.of("src", "main", "resources", "project3", "downloaded.txt"));

        // Process all log files
        Stream<LogRecord> logRecordStream;
        for (String logFilePath : logFilesPaths) {
            try {
                logRecordStream = logAggregator.getLogs(URI.create(logFilePath).toURL());
            } catch (MalformedURLException | IllegalArgumentException exc) {
                logRecordStream = logAggregator.getLogs(Path.of(logFilePath));
            }

            // Accumulate data from all logRecord in logReport
            logRecordStream.filter(logRecord ->
                    logRecord.timeLocal().isAfter(logDataAccumulator.getStartDate())
                        && logRecord.timeLocal().isBefore(logDataAccumulator.getEndDate()))
                .forEach(logRecord -> {

                    logDataAccumulator.increaseNumberOfRequests();
                    logDataAccumulator.addRequestedResource(logRecord.getRequestedResource());
                    logDataAccumulator.addStatusCode(logRecord.statusCode());
                    logDataAccumulator.addResponseSize(logRecord.bytesSend());
                });
        }

        return logDataAccumulator.toLogReport();
    }

    /**
     * Get all files on given path
     * If we get valid url, then return List.of(url)
     * Otherwise, try to find all files matching given path glob
     *
     * @param pathStr - valid url or path in glob format
     * @return list of files on this path
     */
    private static List<String> getPaths(String pathStr) throws IOException {
        try {
            URL url = URI.create(pathStr).toURL();
            return List.of(url.toString());
        } catch (MalformedURLException | IllegalArgumentException exc) {
            Path path;
            List<String> result = new ArrayList<>();

            // Parse path until first stars, to set initial directory, which we will be walking
            int firstStarPosition = pathStr.indexOf('*');
            if (firstStarPosition == -1) {
                path = Path.of(pathStr);
            } else {
                path = Path.of(pathStr.substring(0, firstStarPosition));
            }

            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + pathStr);
            Files.walk(path).forEach(currPath -> {
                if (pathMatcher.matches(currPath)) {
                    result.add(currPath.toString());
                }
            });

            return result;
        }
    }
}
