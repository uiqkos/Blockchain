package blockchain.user;

import blockchain.cryptocurrency.Cryptocurrency;

import java.security.PublicKey;

public interface CryptocurrencyMarketMember {
    Cryptocurrency getCryptocurrency();
    int getId();
    String getName();
    PublicKey getPublicKey();
}
