package edu.hw8.task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashConverter {
    private MD5HashConverter() {
    }

    @SuppressWarnings("MagicNumber")
    private static String convertToHexString(byte[] arrayBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte arrayByte : arrayBytes) {
            stringBuilder.append(
                    Integer.toString((arrayByte & 0xff) + 0x100, 16).substring(1)
            );
        }
        return stringBuilder.toString();
    }

    /**
     * Return MD5 hash of the given password like hex string
     */
    public static String getHashHexString(String password) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return convertToHexString(
            md.digest(password.getBytes(StandardCharsets.UTF_8))
        );
    }
}
