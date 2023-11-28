package edu.hw6.task3;

import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Set;
import java.util.regex.Pattern;

public class Filters {
    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;
    public static final AbstractFilter WRITABLE = Files::isWritable;

    private Filters() {
    }

    public static AbstractFilter largerThan(int size) {
        return path -> Files.size(path) > size;
    }

    public static AbstractFilter globMatches(String globString) {
        return path -> FileSystems.getDefault().getPathMatcher("glob:" + globString).matches(path);
    }

    public static AbstractFilter regexContains(String regexp) {
        return path -> Pattern.compile(regexp).matcher(path.getFileName().toString()).find();
    }

    public static AbstractFilter hasExtension(Set<String> extension) {
        return path -> {
            String[] parts = path.toString().split("\\.");
            return extension.contains(parts[parts.length - 1]);
        };
    }

    public static AbstractFilter magicNumber(int... bytes) {
        return path -> {
            try (
                FileInputStream in = new FileInputStream(path.toFile())
            ) {
                for (int b : bytes) {
                    if (b != in.read()) {
                        return false;
                    }
                }
            }
            return true;
        };
    }

}
