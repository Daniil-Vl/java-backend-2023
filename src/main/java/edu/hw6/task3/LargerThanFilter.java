package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Checks that the file's size larger than passed value
 */
public final class LargerThanFilter extends AbstractFilterImpl {

    private final long size;

    public LargerThanFilter(long size) {
        this.size = size;
    }

    /**
     * Filter path using only current filter
     */
    @Override
    protected boolean filterPath(Path entry) throws IOException {
        return Files.size(entry) > this.size;
    }
}
