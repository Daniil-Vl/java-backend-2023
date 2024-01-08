package edu.project3.commandLine;

public enum OutputFormat {
    MARKDOWN,
    ADOC;

    public static OutputFormat parseFormat(String string) {
        return switch (string.toLowerCase()) {
            case "markdown" -> OutputFormat.MARKDOWN;
            case "adoc" -> OutputFormat.ADOC;
            default -> throw new IllegalArgumentException("Invalid format: %s".formatted(string.toLowerCase()));
        };
    }
}
