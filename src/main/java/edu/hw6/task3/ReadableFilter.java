package edu.hw6.task3;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Checks if file is readable
 */
public final class ReadableFilter extends AbstractFilterImpl {
    /**
     * Filter path using only current filter
     */
    @Override
    protected boolean filterPath(Path entry) {
        return Files.isReadable(entry);
    }
}
