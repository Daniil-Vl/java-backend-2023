package edu.project3.logs;

import java.time.OffsetDateTime;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

public record LogRecord(
    @NotNull String remoteAddr,
    String remoteUser,
    @NotNull OffsetDateTime timeLocal,
    @NotBlank String request,
    int statusCode,
    int bytesSend,
    String httpReferer,
    @NotNull String httpUserAgent) {

    public String getRequestedResource() {
        return this.request.split(" ")[1];
    }

    @SuppressWarnings("MultipleStringLiterals")
    @Override public String toString() {
        return remoteAddr
            + " - "
            + remoteUser
            + " ["
            + timeLocal
            + "] "
            + "\""
            + request
            + "\" "
            + statusCode
            + " "
            + bytesSend
            + " \""
            + httpReferer
            + "\" "
            + "\""
            + httpUserAgent
            + "\"";
    }
}
