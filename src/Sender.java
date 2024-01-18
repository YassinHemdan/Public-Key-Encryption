import java.security.*;

public class Sender {
    private String message;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    public Sender(String message) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();

        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
