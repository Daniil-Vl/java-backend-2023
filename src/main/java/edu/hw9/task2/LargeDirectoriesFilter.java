package edu.hw9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class LargeDirectoriesFilter {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public LargeDirectoriesFilter() {

    }

    public List<Path> findLargeDirs(Path initialDirectory, int minFilesNumber) {
        RecursiveTask<List<Path>> task = new FindLargeDirTask(initialDirectory, minFilesNumber);
        return this.forkJoinPool.invoke(task);
    }

    private static class FindLargeDirTask extends RecursiveTask<List<Path>> {
        private final int minFilesNumber;
        private final Path directory;

        public FindLargeDirTask(Path initialDirectory, int minFilesNumber) {
            if (!initialDirectory.toFile().isDirectory()) {
                throw new IllegalArgumentException("Initial directory doesn't exist");
            }
            this.directory = initialDirectory;
            this.minFilesNumber = minFilesNumber;
        }

        @Override
        protected List<Path> compute() {
            List<Path> result = new ArrayList<>();

            File[] innerFiles = directory.toFile().listFiles();
            List<RecursiveTask<List<Path>>> tasks = new ArrayList<>();
            int innerFilesCount = 0;

            if (innerFiles != null) {
                for (File file : innerFiles) {
                    if (file.isDirectory()) {
                        RecursiveTask<List<Path>> task = new FindLargeDirTask(file.toPath(), minFilesNumber);
                        task.fork();
                        tasks.add(task);
                    } else if (file.isFile()) {
                        innerFilesCount++;
                    }
                }
            }

            if (innerFilesCount > minFilesNumber) {
                result.add(directory);
            }

            for (var task : tasks) {
                result.addAll(task.join());
            }

            return result;
        }
    }
}
