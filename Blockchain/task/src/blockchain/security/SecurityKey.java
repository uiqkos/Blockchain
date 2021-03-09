package blockchain.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SecurityKey {
    private final KeyPair pair;

    public SecurityKey(KeyPair pair) {
        this.pair = pair;
    }

    public void writePublicKeyToFile(String path) throws IOException {
        writeKey(path, pair.getPublic().getEncoded());
    }

    public void writePrivateKeyToFile(String path) throws IOException {
        writeKey(path, pair.getPrivate().getEncoded());
    }

    private void writeKey(String path, byte[] key) throws IOException {
        var file = new File(path);
        file.getParentFile().mkdirs();

        FileOutputStream outputStream = new FileOutputStream(file);

        outputStream.write(key);
        outputStream.flush();
        outputStream.close();
    }

    public PublicKey getPublicKey() { return pair.getPublic(); }
    public PrivateKey getPrivateKey() { return pair.getPrivate(); }

}
