package edu.hw3.task2;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Task2 {

    private Task2() {
    }

    public static ArrayList<String> clusterize(String bracketSequence) {
        ArrayList<String> result = new ArrayList<>();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder currentCluster = new StringBuilder();
        Character lastBracket;

        for (char bracket : bracketSequence.toCharArray()) {
            switch (bracket) {
                case '(':
                    stack.addLast(bracket);
                    currentCluster.append(bracket);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Incorrect bracket sequence: no paired opening bracket");
                    }
                    lastBracket = stack.pollLast();

                    if (lastBracket.equals('(')) {
                        currentCluster.append(bracket);
                    } else {
                        throw new IllegalArgumentException("Incorrect bracket sequence: invalid opening bracket");
                    }

                    // If we completed current cluster, then add it to result and clear string builder for cluster
                    if (stack.isEmpty()) {
                        result.add(currentCluster.toString());
                        currentCluster = new StringBuilder();
                    }

                    break;
                default:
                    throw new IllegalArgumentException(
                        "Incorrect bracket sequence: Non bracket symbol appeared in bracket sequence");
            }
        }

        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Incorrect bracket sequence: some opening bracket remain without pair");
        }

        return result;
    }
}
