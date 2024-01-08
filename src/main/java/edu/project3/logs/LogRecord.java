package edu.project3.logs;

import java.time.OffsetDateTime;

public record LogRecord(
    String remoteAddr,
    String remoteUser,
    OffsetDateTime timeLocal,
    String request,
    int statusCode,
    int bytesSend,
    String httpReferer,
    String httpUserAgent) {

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
