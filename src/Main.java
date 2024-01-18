import Crypto.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender("hi, this is the plain text");
        CBC cbc = new CBC();
        MessageEncryptor encryptor = new MessageEncryptor(sender.getMessage(), sender.getPrivateKey(), cbc);
        encryptor.EncryptHashValue();
        encryptor.encryptPlainText();

        MessageDecryptor decryptor = new MessageDecryptor(encryptor.getCipher_text(), cbc, sender.getPublicKey()
        , sender.getMessage().length());
        decryptor.decryptCipherText();
        decryptor.decryptHashString();

        if(decryptor.getOriginal_plain_text().equals(encryptor.getPlain_text())
                && decryptor.getOriginal_hash_string().equals(Long.toString(encryptor.getHash_value())))
            System.out.println("yes");
        else System.out.println("no");

        System.out.println("message before:\n " + encryptor.getPlain_text());
        System.out.println("message after:\n " + decryptor.getOriginal_plain_text());
        System.out.println("hash before:\n " + encryptor.getHash_value());
        System.out.println("hash after:\n " + decryptor.getOriginal_hash_string());
    }
}