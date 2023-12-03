package edu.hw8.task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashConverter {
    private MD5HashConverter() {
    }

    @SuppressWarnings("MagicNumber")
    public static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(
                Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1)
            );
        }
        return stringBuffer.toString();
    }

    public static String getHexPasswordString(String password) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return convertByteArrayToHexString(
            md.digest(password.getBytes(StandardCharsets.UTF_8))
        );
    }
}
