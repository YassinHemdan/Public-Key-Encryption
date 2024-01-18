package Crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class Utils {
    private static final String AES = "AES";

    public static SecretKey createAESKey() throws Exception {
        SecureRandom securerandom = new SecureRandom();
        KeyGenerator keygenerator = KeyGenerator.getInstance(AES);
        keygenerator.init(128  , securerandom);
        return keygenerator.generateKey();
    }

    public static byte[] createInitializationVector() {
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }

    public static long hash(String s) {
        long h = 1125899906842597L;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            h = 31 * h + s.charAt(i);
        }
        return h;
    }
}
