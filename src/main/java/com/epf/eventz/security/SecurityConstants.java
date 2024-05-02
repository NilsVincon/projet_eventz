package com.epf.eventz.security;

import java.security.SecureRandom;
import java.util.Base64;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 1000*60*60*1;
    public static final String JWT_SECRET = generateSecret();

    private static String generateSecret() {
        byte[] secretBytes = new byte[64];
        new SecureRandom().nextBytes(secretBytes);
        return Base64.getEncoder().encodeToString(secretBytes);
    }
}
