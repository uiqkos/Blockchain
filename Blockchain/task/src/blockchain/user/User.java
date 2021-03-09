package blockchain.user;

import blockchain.blocks.Block;
import blockchain.blocks.MinerBlock;
import blockchain.cryptocurrency.Cryptocurrency;
import blockchain.cryptocurrency.Transaction;
import blockchain.security.KeyGenerator;
import blockchain.security.SecurityKey;
import blockchain.security.TransactionSigner;
import blockchain.Utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Objects;
import java.util.Random;

public class User implements Miner, CoinSender, Runnable {
    private static int idCounter = 0;

    private final String name;
    private final int id = idCounter++;
    private final Cryptocurrency cryptocurrency;
    private final SecurityKey key;

    public User(String name, Cryptocurrency cryptocurrency) throws NoSuchAlgorithmException {
        this.name = name;
        this.cryptocurrency = cryptocurrency;
        key = KeyGenerator.generateKey(1024);
    }

    public String getName() {
        return name;
    }

    @Override
    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public int getId() {
        return id;
    }

    @Override
    public PublicKey getPublicKey() {
        return key.getPublicKey();
    }


    @Override
    public void run() {
        while (true) {
            try {

                if (new Random().nextBoolean())
                    send(generateTransaction());
                else
                    mine();

            } catch (Exception e) { break; }
        }
    }

    @Override
    public void send(Transaction transaction) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        TransactionSigner.signTransaction(transaction, key.getPrivateKey());
        getCryptocurrency().makeTransaction(transaction);
    }

    private Transaction generateTransaction() {
        Random random = new Random();

        int index = random.nextInt(getCryptocurrency().getUsers().size());
        User to = getCryptocurrency().getUsers().get(index);

        return new Transaction(
            this, to, getCryptocurrency(), random.nextInt(100), key.getPublicKey()
        );
    }

}
