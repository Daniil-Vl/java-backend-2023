package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    /**
     * Method for chains
     */
    AbstractFilter and(AbstractFilter nextFilter);
}
