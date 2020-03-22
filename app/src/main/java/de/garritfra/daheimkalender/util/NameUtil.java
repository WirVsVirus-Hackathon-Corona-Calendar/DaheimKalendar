package de.garritfra.daheimkalender.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NameUtil {

    public static String getResourceImageName(final String url) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(url.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                StringBuilder h = new StringBuilder(Integer.toHexString(0xFF & aMessageDigest));
                while (h.length() < 2)
                    h.insert(0, "0");
                hexString.append(h);
            }
            return hexString.toString() + ".png";

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "default.png";
    }
}
