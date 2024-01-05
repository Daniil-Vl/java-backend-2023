package edu.hw9.task2.filepredicates;

import java.nio.file.Path;
import java.util.function.Predicate;

public class ExtensionPredicate implements Predicate<Path> {

    private final String expectedExtension;

    public ExtensionPredicate(String extension) {
        this.expectedExtension = extension;
    }

    @Override
    public boolean test(Path path) {
        if (!path.toFile().isFile()) {
            return false;
        }

        return getExtension(path).equals(expectedExtension);
    }

    private String getExtension(Path path) {
        String pathStr = path.toString();
        return pathStr.substring(pathStr.lastIndexOf(".") + 1);
    }
}
