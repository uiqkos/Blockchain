package blockchain.user;

import blockchain.cryptocurrency.Transaction;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Random;

public interface CoinSender extends CryptocurrencyMarketMember {
    void send(Transaction transaction) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException;
}
