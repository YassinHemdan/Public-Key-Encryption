package Crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public class CBC {
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";
    private final byte[] initializationVector;
    private final SecretKey secretKey;
    public CBC() throws Exception {
        this.initializationVector = Utils.createInitializationVector();
        this.secretKey = Utils.createAESKey();
    }
    public byte[] AESEncryption(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(plainText.getBytes());
    }
    public String AESDecryption(byte[] cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] result = cipher.doFinal(cipherText);

        return new String(result);
    }
}
