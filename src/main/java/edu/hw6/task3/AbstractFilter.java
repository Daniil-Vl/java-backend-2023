package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    /**
     * Method for linking filters into a chain
     *
     * @param filter - next filter to add in chain
     * @return AbstractFilter, which skips the file based on its own conditions and the argument filter condition
     */
    default AbstractFilter and(AbstractFilter filter) {
        return path -> accept(path) && filter.accept(path);
    }
}
