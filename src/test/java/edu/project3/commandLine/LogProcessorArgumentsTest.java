package edu.project3.commandLine;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LogProcessorArgumentsTest {

    private static Stream<Arguments> data() {
        // Case with --path, --startDate, --format, without --endDate
        return Stream.of(
            Arguments.of(
                new String[] {
                    "--path",
                    "logs/2023*",
                    "--from",
                    "2023-08-31",
                    "--format",
                    "markdown"
                },
                new LogProcessorArguments(
                    List.of("logs/2023*"),
                    OffsetDateTime.of(2023, 8, 31, 0, 0, 0, 0, ZoneOffset.ofTotalSeconds(0)),
                    null,
                    OutputFormat.MARKDOWN
                )
            ),

            // Case with --path, --format, without --from, --endDate
            Arguments.of(
                new String[] {
                    "--path",
                    "logs/2023*",
                    "--format",
                    "adoc"
                },
                new LogProcessorArguments(
                    List.of("logs/2023*"),
                    null,
                    null,
                    OutputFormat.ADOC
                )
            ),

            // Case with --path, --from, --to and without --format
            Arguments.of(
                new String[] {
                    "--path",
                    "logs/2023*",
                    "--from",
                    "2023-08-31",
                    "--to",
                    "2023-09-30"
                },
                new LogProcessorArguments(
                    List.of("logs/2023*"),
                    OffsetDateTime.of(2023, 8, 31, 0, 0, 0, 0, ZoneOffset.ofTotalSeconds(0)),
                    OffsetDateTime.of(2023, 9, 30, 0, 0, 0, 0, ZoneOffset.ofTotalSeconds(0)),
                    OutputFormat.MARKDOWN
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void parseArguments(String[] args, LogProcessorArguments expected) {
        assertThat(LogProcessorArguments.parseArguments(args)).isEqualTo(expected);
    }

    @Test
    void parseArgsWithEmptyPath() {
        String[] argsWithEmptyPath = new String[] {
            "--path",
            "--from",
            "2023-08-08",
            "--to",
            "2023-08-09"
        };

        assertThatThrownBy(
            () -> LogProcessorArguments.parseArguments(argsWithEmptyPath)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @Test
    void parseArgsWithInvalidFormat() {
        String[] args = new String[] {
            "--path",
            "/some/path",
            "--format",
            "invalid"
        };

        assertThatThrownBy(() -> LogProcessorArguments.parseArguments(args)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void parseArgsWithInvalidDate() {
        String[] args = new String[] {
            "--path",
            "/some/path",
            "--from",
            "20231010",
        };

        assertThatThrownBy(() -> LogProcessorArguments.parseArguments(args)).isInstanceOf(DateTimeParseException.class);
    }
}
