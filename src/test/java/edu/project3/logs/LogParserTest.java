package edu.project3.logs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LogParserTest {

    private static Stream<Arguments> nginxLogs() {
        return Stream.of(
            // Case for request with specific file
            Arguments.of(
                // 78.148.94.102 - - [15/Nov/2023:19:12:45 +0000] "PATCH /middleware_projection-systematic.svg HTTP/1.1" 200 2536 "-" "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_9_8) AppleWebKit/5361 (KHTML, like Gecko) Chrome/40.0.842.0 Mobile Safari/5361"
                "78.148.94.102 - - [15/Nov/2023:19:12:45 +0000] \"PATCH /middleware_projection-systematic.svg HTTP/1.1\" 200 2536 \"-\" \"Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_9_8) AppleWebKit/5361 (KHTML, like Gecko) Chrome/40.0.842.0 Mobile Safari/5361\"",
                new LogRecord(
                    "78.148.94.102",
                    null,
                    OffsetDateTime.of(2023, 11, 15, 19, 12, 45, 0, ZoneOffset.ofHoursMinutesSeconds(0, 0, 0)),
                    "PATCH /middleware_projection-systematic.svg HTTP/1.1",
                    200,
                    2536,
                    null,
                    "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_9_8) AppleWebKit/5361 (KHTML, like Gecko) Chrome/40.0.842.0 Mobile Safari/5361"
                )
            ),

            // Case for request with file with specific symbols
            Arguments.of(
                // 18.49.129.84 - - [15/Nov/2023:19:12:45 +0000] "PUT /didactic_Self-enabling%20incremental.svg HTTP/1.1" 200 2038 "-" "Mozilla/5.0 (X11; Linux x86_64; rv:7.0) Gecko/2016-01-01 Firefox/36.0"
                "18.49.129.84 - - [15/Nov/2023:19:12:45 +0000] \"PUT /didactic_Self-enabling%20incremental.svg HTTP/1.1\" 200 2038 \"-\" \"Mozilla/5.0 (X11; Linux x86_64; rv:7.0) Gecko/2016-01-01 Firefox/36.0\"",
                new LogRecord(
                    "18.49.129.84",
                    null,
                    OffsetDateTime.of(2023, 11, 15, 19, 12, 45, 0, ZoneOffset.ofHoursMinutesSeconds(0, 0, 0)),
                    "PUT /didactic_Self-enabling%20incremental.svg HTTP/1.1",
                    200,
                    2038,
                    null,
                    "Mozilla/5.0 (X11; Linux x86_64; rv:7.0) Gecko/2016-01-01 Firefox/36.0"
                )
            ),

            // Case for log with IPv6
            Arguments.of(
                // 2001:4801:7824:102:8bee:6e66:ff10:6aa2 - - [18/May/2015:16:05:29 +0000] "GET /downloads/product_1 HTTP/1.1" 200 85619205 "-" "Chef Client/12.0.3 (ruby-2.1.4-p265; ohai-8.0.1; x86_64-linux; +http://opscode.com)"
                "2001:4801:7824:102:8bee:6e66:ff10:6aa2 - - [18/May/2015:16:05:29 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 85619205 \"-\" \"Chef Client/12.0.3 (ruby-2.1.4-p265; ohai-8.0.1; x86_64-linux; +http://opscode.com)\"",
                new LogRecord(
                    "2001:4801:7824:102:8bee:6e66:ff10:6aa2",
                    null,
                    OffsetDateTime.of(2015, 5, 18, 16, 5, 29, 0, ZoneOffset.ofHoursMinutesSeconds(0, 0, 0)),
                    "GET /downloads/product_1 HTTP/1.1",
                    200,
                    85619205,
                    null,
                    "Chef Client/12.0.3 (ruby-2.1.4-p265; ohai-8.0.1; x86_64-linux; +http://opscode.com)"
                )
            ),

            // Case for log with IPv6 with skipped sections
            Arguments.of(
                // 2a01:7e00::f03c:91ff:fe70:a4cc - - [18/May/2015:16:05:29 +0000] "GET /downloads/product_1 HTTP/1.1" 200 86166026 "-" "Wget/1.15 (linux-gnu)"
                "2a01:7e00::f03c:91ff:fe70:a4cc - - [18/May/2015:16:05:29 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 86166026 \"-\" \"Wget/1.15 (linux-gnu)\"",
                new LogRecord(
                    "2a01:7e00::f03c:91ff:fe70:a4cc",
                    null,
                    OffsetDateTime.of(2015, 5, 18, 16, 5, 29, 0, ZoneOffset.ofHoursMinutesSeconds(0, 0, 0)),
                    "GET /downloads/product_1 HTTP/1.1",
                    200,
                    86166026,
                    null,
                    "Wget/1.15 (linux-gnu)"
                )
            ),

            // Case for log with shortened IPv6
            Arguments.of(
                // 1050:0:0:0:5:600:300c:326b - - [18/May/2015:16:05:29 +0000] "GET /downloads/product_1 HTTP/1.1" 200 86166026 "-" "Wget/1.15 (linux-gnu)"
                "1050:0:0:0:5:600:300c:326b - - [18/May/2015:16:05:29 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 86166026 \"-\" \"Wget/1.15 (linux-gnu)\"",
                new LogRecord(
                    "1050:0:0:0:5:600:300c:326b",
                    null,
                    OffsetDateTime.of(2015, 5, 18, 16, 5, 29, 0, ZoneOffset.ofHoursMinutesSeconds(0, 0, 0)),
                    "GET /downloads/product_1 HTTP/1.1",
                    200,
                    86166026,
                    null,
                    "Wget/1.15 (linux-gnu)"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("nginxLogs")
    void testParseLog(String nginxLog, LogRecord expected) {
        assertThat(LogParser.parseLog(nginxLog)).isEqualTo(expected);
    }
}
