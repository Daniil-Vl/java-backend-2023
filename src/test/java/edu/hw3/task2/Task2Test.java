package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import edu.hw3.task2.Task2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {

    private static Stream<Arguments> correctBracketSequences() {
        return Stream.of(
            Arguments.of(
                "",
                new ArrayList<>()
            ),
            Arguments.of(
                "()()()",
                new ArrayList<>(List.of("()", "()", "()"))
            ),
            Arguments.of(
                "((()))",
                new ArrayList<>(List.of("((()))"))
            ),
            Arguments.of(
                "((()))(())()()(()())",
                new ArrayList<>(List.of("((()))", "(())", "()", "()", "(()())"))
            ),
            Arguments.of(
                "((())())(()(()()))",
                new ArrayList<>(List.of("((())())", "(()(()()))"))
            )
        );
    }

    private static Stream<Arguments> incorrectBracketSequences() {
        return Stream.of(
            Arguments.of("()())"),
            Arguments.of("(()"),
            Arguments.of("(()"),
            Arguments.of("(()#)"),
            Arguments.of("()(}")
        );
    }

    @ParameterizedTest
    @MethodSource("correctBracketSequences")
    void correctBracketSequences(String bracketSequence, ArrayList<String> expectedClusters) {
        assertThat(Task2.clusterize(bracketSequence)).isEqualTo(expectedClusters);
    }

    @ParameterizedTest
    @MethodSource("incorrectBracketSequences")
    void testIncorrectBracketSequences(String bracketSequence) {
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(bracketSequence));
    }
}
