package Crypto;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Base64;

public class MessageEncryptor {
    private PrivateKey privateKey;
    private String plain_text;
    private long hash_value ;
    private String encrypted_hash_value;
    private String new_concatenated_message;
    private byte[] cipher_text;
    private CBC cbc;
    public MessageEncryptor(String plain_text, PrivateKey privateKey, CBC cbc){
        this.plain_text = plain_text;
        this.hash_value = Utils.hash(plain_text);
        this.privateKey = privateKey;
        this.cbc = cbc;
    }
    // this function encrypts the hash value of the plain text
    // with the private key of the sender;
    // then it will concatenate the encrypted hash value with the plain text
    public void EncryptHashValue() throws Exception {
        String HashString = Long.toString(hash_value);
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);

        byte[] secretMessageBytes = HashString.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        encrypted_hash_value = Base64.getEncoder().encodeToString(encryptedMessageBytes);

        concatenate();
    }

    private void concatenate(){
        new_concatenated_message = plain_text + encrypted_hash_value;
    }

    // this function encrypts our new plain text to generate the cipher text
    public void encryptPlainText() throws Exception {
        cipher_text = cbc.AESEncryption(new_concatenated_message);
    }
    public byte[] getCipher_text() {
        return cipher_text;
    }

    public String getPlain_text() {
        return plain_text;
    }

    public long getHash_value() {
        return hash_value;
    }
}
