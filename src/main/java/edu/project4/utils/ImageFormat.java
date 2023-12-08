package edu.project4.utils;

public enum ImageFormat {
    JPEG("jpeg"),
    BMP("bmp"),
    PNG("png");

    public String getStringFormat() {
        return stringFormat;
    }

    private final String stringFormat;

    ImageFormat(String stringFormat) {
        this.stringFormat = stringFormat;
    }

}
