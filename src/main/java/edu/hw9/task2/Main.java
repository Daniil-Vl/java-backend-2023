package edu.hw9.task2;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        LargeDirectoriesFilter filter = new LargeDirectoriesFilter();
        var res = filter.findLargeDirs(Path.of("src/main/resources/hw9"), 2);
        System.out.println(res);
    }
}
