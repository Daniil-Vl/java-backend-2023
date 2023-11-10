package edu.hw6.task3;

import java.nio.file.Path;
import java.util.regex.Pattern;

public class MagicInitialIdentifiers extends AbstractFilterImpl {
    private final Pattern antiPattern = Pattern.compile("\\d+.*");

    /**
     * Filter path using only current filter
     */
    @Override
    protected boolean filterPath(Path entry) {
        return !this.antiPattern.matcher(entry.getFileName().toString()).matches();
    }
}
