package blockchain.cryptocurrency;

import blockchain.user.CryptocurrencyMarketMember;

import java.io.Serializable;
import java.security.PublicKey;

public class Transaction implements Serializable {
    private static final long serialVersionUID = -8524317533941052345L;
    private static int idCounter = 0;

    private final int id = idCounter++;
    private final CryptocurrencyMarketMember from;
    private final CryptocurrencyMarketMember to;
    private final Cryptocurrency cryptocurrency;
    private final int count;
    private final PublicKey key;
    private byte[] signature;

    public Transaction(
        CryptocurrencyMarketMember from,
        CryptocurrencyMarketMember to,
        Cryptocurrency cryptocurrency,
        int count,
        PublicKey key
    ) {
        this.from = from;
        this.to = to;
        this.key = key;
        this.cryptocurrency = cryptocurrency;
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format(
            "%s: %s sent %s %s to %s",
            id, from.getName(), count,
            cryptocurrency.getShortName(), to.getName()
        );
    }

    public CryptocurrencyMarketMember getFrom() {
        return from;
    }

    public CryptocurrencyMarketMember getTo() {
        return to;
    }

    public int getCount() {
        return count;
    }

    public PublicKey getKey() {
        return key;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }


}
