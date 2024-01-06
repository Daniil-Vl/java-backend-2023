package edu.project3.logs;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    private LogParser() {
    }

    private static final String IPV_4_PATTERN = "(\\d{1,3}\\.){3}\\d{1,3}";
    private static final String IPV_6_PATTERN = "([a-f0-9]{0,4}:){2,7}([a-f0-9]{0,4})?";

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    /**
     * Parses log string to LogRecord object
     *
     * @param nginxLog - raw string containing one nginx lgo
     * @return parsed LogRecord
     */
    @SuppressWarnings("MagicNumber")
    public static LogRecord parseLog(String nginxLog) {
        // ip, remoteUser, timeLocal, request, statusCode, bytesSend, httpReferer, httpUserAgent
        String[] options = new String[8];
        int position = 0;
        for (PatternEnum patternENum : PatternEnum.values()) {
            options[position] = patternENum.getSubstring(nginxLog);
            position++;
        }

        return new LogRecord(
            options[0],
            options[1].equals("-") ? null : options[1],
            OffsetDateTime.parse(options[2], DATE_TIME_FORMATTER),
            options[3],
            Integer.parseInt(options[4]),
            Integer.parseInt(options[5]),
            options[6].equals("-") ? null : options[6],
            options[7]
        );
    }

    public enum PatternEnum {
        ipPattern("(%s)|(%s)".formatted(IPV_4_PATTERN, IPV_6_PATTERN)),
        remoteUserPattern("- .* \\["),
        timeLocalPattern("\\[\\d{1,2}/\\w+/\\d{4}:\\d{2}:\\d{2}:\\d{2} \\+\\d{4}]"),
        requestPattern("\"[A-Z]+ [\\/a-zA-Z0-9_%-]+(.[a-zA-Z]+|) HTTP\\/\\d\\.\\d\""),
        statusCodePattern("\" \\d{3} \\d"),
        bytesSendPattern("\\d \\d+ \""),
        httpRefererPattern("\\d \".*\" \""),
        httpUserAgentPattern("\" \".*\"$");
        private final Pattern pattern;

        PatternEnum(String pattern) {
            this.pattern = Pattern.compile(pattern);
        }

        @SuppressWarnings("MagicNumber")
        public String getSubstring(String nginxLog) {
            Matcher matcher = this.pattern.matcher(nginxLog);
            String subString;
            if (matcher.find()) {
                subString = matcher.group();
                return switch (this) {
                    case remoteUserPattern, statusCodePattern, bytesSendPattern ->
                        subString.substring(2, subString.length() - 2);
                    case timeLocalPattern, requestPattern -> subString.substring(1, subString.length() - 1);
                    case httpRefererPattern -> subString.substring(3, subString.length() - 3);
                    case httpUserAgentPattern -> subString.substring(3, subString.length() - 1);
                    case ipPattern -> subString;
                };
            }
            return null;
        }
    }
}
