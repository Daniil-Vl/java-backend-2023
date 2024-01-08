package edu.project3.logs;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MultipleStringLiterals")
public class LogParser {
    private static final String IPV_4_PATTERN = "(\\d{1,3}\\.){3}\\d{1,3}";
    private static final String IPV_6_PATTERN = "([a-f0-9]{0,4}:){2,7}([a-f0-9]{0,4})?";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    private LogParser() {
    }

    /**
     * Parses nginx log string to LogRecord object
     *
     * @param nginxLog - raw string containing one nginx log
     * @return parsed LogRecord
     */
    public static LogRecord parseLog(String nginxLog) {
        Map<String, String> attrs = new HashMap<>();

        for (PatternEnum pattern : PatternEnum.values()) {
            String substring = pattern.getSubstring(nginxLog);

            // If pattern parsing returned null, then log string is invalid,
            // because some pattern didn't manage to parse substring
            if (substring == null) {
                throw new IllegalArgumentException(
                    "Invalid nginx log: %s cannot be parsed from %s"
                        .formatted(pattern.partName, nginxLog));
            }

            attrs.put(pattern.partName, substring);
        }

        return new LogRecord(
            attrs.get("remoteAddr"),
            attrs.get("remoteUser").equals("-") ? null : attrs.get("remoteUser"),
            OffsetDateTime.parse(attrs.get("timeLocal"), DATE_TIME_FORMATTER),
            attrs.get("request"),
            Integer.parseInt(attrs.get("statusCode")),
            Integer.parseInt(attrs.get("bytesSend")),
            attrs.get("httpReferer").equals("-") ? null : attrs.get("httpReferer"),
            attrs.get("httpUserAgent")
        );
    }

    /**
     * Enumeration of regexp patterns, used to parse all parts from nginx log
     */
    public enum PatternEnum {
        remoteAddrPattern("(%s)|(%s)".formatted(IPV_4_PATTERN, IPV_6_PATTERN), "remoteAddr"),
        remoteUserPattern("- .* \\[", "remoteUser"),
        timeLocalPattern("\\[\\d{1,2}/\\w+/\\d{4}:\\d{2}:\\d{2}:\\d{2} \\+\\d{4}]", "timeLocal"),
        requestPattern("\"[A-Z]+ [\\/a-zA-Z0-9_%-]+(.[a-zA-Z]+|) HTTP\\/\\d\\.\\d\"", "request"),
        statusCodePattern("\" \\d{3} \\d", "statusCode"),
        bytesSendPattern("\\d \\d+ \"", "bytesSend"),
        httpRefererPattern("\\d \".*\" \"", "httpReferer"),
        httpUserAgentPattern("\" \".*\"$", "httpUserAgent");
        private final Pattern pattern;
        private final String partName;

        PatternEnum(String pattern, String name) {
            this.pattern = Pattern.compile(pattern);
            this.partName = name;
        }

        /**
         * Return parsed substring, if nginxLog contains valid substring
         * Otherwise returns null
         *
         * @param nginxLog - log, from which substring will be parsed
         * @return substring
         */
        @SuppressWarnings("MagicNumber")
        public String getSubstring(String nginxLog) {
            Matcher matcher = this.pattern.matcher(nginxLog);

            if (matcher.find()) {
                String subString = matcher.group();
                return switch (this) {
                    case remoteUserPattern, statusCodePattern, bytesSendPattern ->
                        subString.substring(2, subString.length() - 2);
                    case timeLocalPattern, requestPattern -> subString.substring(1, subString.length() - 1);
                    case httpRefererPattern -> subString.substring(3, subString.length() - 3);
                    case httpUserAgentPattern -> subString.substring(3, subString.length() - 1);
                    case remoteAddrPattern -> subString;
                };
            }

            return null;
        }
    }
}
