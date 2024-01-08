package edu.project3.aggregators;

import edu.project3.logs.LogParser;
import edu.project3.logs.LogRecord;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Allow you to get file with nginx logs startDate different sources
 */
public class LogAggregator {
    private final Path pathOfTempFile;

    public LogAggregator(Path pathOfTempFile) {
        this.pathOfTempFile = pathOfTempFile;
    }

    /**
     * Download file with given url and save result to temp file
     *
     * @param url - url of file to download
     * @return Path of downloadedTempFile
     */
    private Path downloadFile(URL url) throws IOException {
        // Create input channel from url and out channel to file in local directory
        // And transfer from in channel to out channel
        Path tempFile = Files.createTempFile(pathOfTempFile, "temp_file_prefix-", "-temp_file_suffix");
        tempFile.toFile().deleteOnExit();

        try (
            ReadableByteChannel inChannel = Channels.newChannel(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile.toFile());
            FileChannel outChannel = fileOutputStream.getChannel()
        ) {
            outChannel.transferFrom(inChannel, 0, Long.MAX_VALUE);
        }

        return pathOfTempFile;
    }

    /**
     * Get logs from source and parse them
     *
     * @param logsLocationUrl - url of text files with nginx logs
     * @return Stream of parsed nginx logs
     */
    public Stream<LogRecord> getLogs(URL logsLocationUrl) throws IOException {
        return getLogs(downloadFile(logsLocationUrl));
    }

    /**
     * Get logs from sources and parse them
     *
     * @param logsLocationPath - path to text file (not directory) with nginx logs
     * @return Stream of parsed nginx logs
     */
    public Stream<LogRecord> getLogs(Path logsLocationPath) throws IOException {
        return Files.lines(logsLocationPath).map(LogParser::parseLog);
    }
}
