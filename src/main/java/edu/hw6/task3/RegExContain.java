package edu.hw6.task3;

import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * Checks, that file name contains substring of certain format (given regexp)
 */
public final class RegExContain extends AbstractFilterImpl {
    private final Pattern pattern;

    public RegExContain(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * Filter path using only current filter
     */
    @Override
    protected boolean filterPath(Path entry) {
        return this.pattern.matcher(entry.getFileName().toString()).find();
    }
}
