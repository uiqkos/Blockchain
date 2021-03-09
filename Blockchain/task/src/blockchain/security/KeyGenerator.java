package blockchain.security;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGenerator {

    public static SecurityKey generateKey(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(keySize);
        var pair = generator.generateKeyPair();
        return new SecurityKey(pair);
    }
}
