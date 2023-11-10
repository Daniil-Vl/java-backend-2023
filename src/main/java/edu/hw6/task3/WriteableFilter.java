package edu.hw6.task3;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Checks if file is writeable
 */
public final class WriteableFilter extends AbstractFilterImpl {
    /**
     * Filter path using only current filter
     */
    @Override
    protected boolean filterPath(Path entry) {
        return Files.isWritable(entry);
    }
}
