package edu.hw6.task2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public class FileCloner {
    private FileCloner() {
    }

    public static void cloneFile(Path originalFile) throws IOException {
        if (!originalFile.toFile().exists()) {
            throw new FileNotFoundException("Cannot clone file, that doesn't exist");
        }

        Path copiedFile = createEmptyFileCopy(originalFile);
        copyFileContent(originalFile, copiedFile);
    }

    private static void copyFileContent(Path originalFile, Path copiedFile) throws IOException {
        try (
            FileInputStream in = new FileInputStream(originalFile.toFile());
            FileOutputStream out = new FileOutputStream(copiedFile.toFile())
        ) {
            out.write(in.readAllBytes());
        }
    }

    /**
     * Create new empty file with appropriate name (like copy of original file)
     * Examples:
     * 1) First copy of file "data.txt" - return file with name "data - копия.txt"
     * 2) Second copy of file "data.txt" - return file with name "data - копия (2).txt"
     *
     * @param path - path of file to be copied
     * @return path of empty file with appropriate name
     */
    @NotNull
    @SuppressWarnings("MultipleStringLiterals")
    private static Path createEmptyFileCopy(Path path) throws IOException {
        String fullFileName = path.getFileName().toString();
        String filenameWithoutExtension = fullFileName.substring(0, fullFileName.lastIndexOf("."));
        String extension = fullFileName.substring(fullFileName.lastIndexOf(".") + 1);

        Path pathOfCopiedFile = path.resolveSibling(
            filenameWithoutExtension
                + " — копия"
                + "."
                + extension
        );

        // If file already has first copy, then we try to find correct version number
        if (!pathOfCopiedFile.toFile().createNewFile()) {
            int copyNumber = 2;
            do {
                pathOfCopiedFile = path.resolveSibling(
                    filenameWithoutExtension
                        + " — копия (%d)".formatted(copyNumber)
                        + "."
                        + extension
                );
                copyNumber++;
            } while (pathOfCopiedFile.toFile().exists());
        }

        return pathOfCopiedFile;
    }
}
