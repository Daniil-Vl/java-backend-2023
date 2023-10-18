package edu.hw3.task1;

import java.util.stream.Stream;
import edu.hw3.task1.Task1;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task1Test {
    private static Stream<Arguments> strings() {
        return Stream.of(
            Arguments.of("", ""),
            Arguments.of("a", "z"),
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
            ),
            Arguments.of("another test", "zmlgsvi gvhg"),
            Arguments.of("direct", "wrivxg"),
            Arguments.of("ivevihv", "reverse")
        );
    }

    private static Stream<Arguments> stringsWithSpecialCases() {
        return Stream.of(
            Arguments.of(
                "Алгоритм работает только с буквами из латинского алфавита",
                "Алгоритм работает только с буквами из латинского алфавита"
            ),
            Arguments.of("This Is caSE inSenSITIVE", "Gsrh Rh xzHV rmHvmHRGREV"),
            Arguments.of("@#$%^&*()_+{}", "@#$%^&*()_+{}")
        );
    }

    @ParameterizedTest
    @MethodSource({"strings", "stringsWithSpecialCases"})
    void testAtBash(String original, String expected) {
        assertThat(Task1.atbash(original)).isEqualTo(expected);
    }
}
