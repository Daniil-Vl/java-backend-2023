package edu.hw6.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class FileCloner {
    private FileCloner() {
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static void cloneFile(Path path) throws IOException {
        File originalFile = path.toFile();
        if (!originalFile.exists()) {
            throw new FileNotFoundException("Cannot clone file, that doesn't exist");
        }

        // Check if file haven't first copy, then create it
        File copiedFile;
        String filenameWithoutExtension = path.getFileName().toString().split("\\.")[0];
        String extension = path.getFileName().toString().split("\\.")[1];

        Path pathOfCopied = path.resolveSibling(
            filenameWithoutExtension
                + " — копия"
                + "."
                + extension
        );
        copiedFile = pathOfCopied.toFile();

        if (!copiedFile.createNewFile()) {
            // If file already have first clone, then we should find version to create
            int copyNumber = 2;
            do {
                pathOfCopied = path.resolveSibling(
                    filenameWithoutExtension
                        + " — копия (%d)".formatted(copyNumber)
                        + "."
                        + extension
                );
                copyNumber++;
            } while (pathOfCopied.toFile().exists());
            copiedFile = pathOfCopied.toFile();
        }

        // Copy content from original file to copied file
        FileInputStream in = new FileInputStream(originalFile);
        FileOutputStream out = new FileOutputStream(copiedFile);
        out.write(in.readAllBytes());
        in.close();
        out.close();
    }
}
