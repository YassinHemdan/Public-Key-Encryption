package Crypto;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Base64;

public class MessageDecryptor {
    private PublicKey publicKey;
    private CBC cbc;
    private int original_plaintext_size;
    private String original_plain_text;
    private String hashString;
    private String original_hash_string;
    private byte[] cipher_text;
    private String plain_text_with_hashString;
    public MessageDecryptor(byte[] cipher_text, CBC cbc, PublicKey publicKey, int original_plaintext_size){
        this.original_plaintext_size = original_plaintext_size;
        this.publicKey = publicKey;
        this.cipher_text = cipher_text;
        this.cbc = cbc;
    }
    public void decryptCipherText() throws Exception {
        plain_text_with_hashString = cbc.AESDecryption(cipher_text);
        original_plain_text = plain_text_with_hashString.substring(0, original_plaintext_size);
        hashString = plain_text_with_hashString.substring(original_plaintext_size);
    }
    public void decryptHashString() throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, publicKey);

        byte[] encryptedHash = Base64.getDecoder().decode(hashString);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedHash);

        original_hash_string = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    }
    public String getOriginal_plain_text() {
        return original_plain_text;
    }

    public String getOriginal_hash_string() {
        return original_hash_string;
    }
}
