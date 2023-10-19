package edu.hw3.task3;

import java.util.HashMap;

public class Task3 {
    private Task3() {
    }

    public static <T> HashMap<T, Integer> freqDict(T[] objects) {
        HashMap<T, Integer> resultFrequencyDict = new HashMap<>();

        for (T obj : objects) {
            if (resultFrequencyDict.containsKey(obj)) {
                resultFrequencyDict.put(obj, resultFrequencyDict.get(obj) + 1);
            } else {
                resultFrequencyDict.put(obj, 1);
            }
        }

        return resultFrequencyDict;
    }
}
