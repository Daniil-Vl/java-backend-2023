package edu.hw9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class PredicateFilesFilter {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public List<Path> findFilesUsingPredicate(Path initialDirectory, Predicate<Path> predicate) {
        RecursiveTask<List<Path>> task = new FindFileTask(initialDirectory, predicate);
        return this.forkJoinPool.invoke(task);
    }

    private static class FindFileTask extends RecursiveTask<List<Path>> {

        private final Predicate<Path> predicate;
        private final Path directory;

        FindFileTask(Path initialDirectory, Predicate<Path> predicate) {
            if (!initialDirectory.toFile().isDirectory()) {
                throw new IllegalArgumentException("Initial directory doesn't exist");
            }
            this.directory = initialDirectory;
            this.predicate = predicate;
        }

        @Override
        protected List<Path> compute() {
            List<Path> result = new ArrayList<>();

            List<RecursiveTask<List<Path>>> tasks = new ArrayList<>();

            File[] innerFiles = directory.toFile().listFiles();
            if (innerFiles != null) {
                for (File innerFile : innerFiles) {
                    Path innerFilePath = innerFile.toPath();
                    if (innerFile.isDirectory()) {
                        var task = new FindFileTask(innerFilePath, predicate);
                        task.fork();
                        tasks.add(task);
                    } else if (innerFile.isFile() && predicate.test(innerFilePath)) {
                        result.add(innerFilePath);
                    }
                }
            }

            for (var task : tasks) {
                result.addAll(task.join());
            }

            return result;
        }
    }
}
