package gps.tracker.backend.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtils {
    public static String hashPass(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
