package edu.project3.formatting;

import edu.project3.processing.LogReport;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public interface LogReportFormatter {

    /**
     * Saves LogReport in file using certain format
     *
     * @param logReport - LogReport to save
     * @param file      - file, where to save log report
     */
    void saveLogReport(LogReport logReport, Path file) throws FileNotFoundException;
}
