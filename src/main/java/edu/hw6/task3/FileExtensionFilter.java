package edu.hw6.task3;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Checks if the file has a valid extension
 */
public class FileExtensionFilter extends AbstractFilterImpl {

    private final List<String> allowedExtensions;

    public FileExtensionFilter(List<String> allowedExtensions) {
        this.allowedExtensions = new ArrayList<>(allowedExtensions);
    }

    /**
     * Filter path using only current filter
     */
    @Override
    protected boolean filterPath(Path entry) {
        var parts = entry.getFileName().toFile().toString().split("\\.");
        String extension = parts[parts.length - 1];
        return this.allowedExtensions.contains(extension);
    }
}
