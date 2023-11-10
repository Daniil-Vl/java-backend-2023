package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFilterImpl implements AbstractFilter {

    /**
     * list of filters in chain before current filter
     */
    List<AbstractFilter> filters = new LinkedList<>();

    /**
     * Method for chains
     */
    @Override
    public AbstractFilter and(AbstractFilter nextFilter) {
        this.filters.add(nextFilter);
        return this;
    }

    /**
     * Decides if the given directory entry should be accepted or filtered.
     *
     * @param entry the directory entry to be tested
     * @return {@code true} if the directory entry should be accepted
     * @throws IOException If an I/O error occurs
     */
    @Override
    public boolean accept(Path entry) throws IOException {
        boolean result = true;
        for (AbstractFilter filter : this.filters) {
            result &= filter.accept(entry);
        }
        return result & this.filterPath(entry);
    }

    /**
     * Filter path using only current filter
     */
    protected abstract boolean filterPath(Path entry) throws IOException;
}
