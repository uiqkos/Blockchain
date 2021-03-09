package blockchain.cryptocurrency.cryptocurrencies;

import blockchain.cryptocurrency.Cryptocurrency;

import java.security.NoSuchAlgorithmException;

//cryptomishka
public class MishkaCoin extends Cryptocurrency {

    private static MishkaCoin Instance;

    private MishkaCoin() {
        super("MishkaCoin", "VC");
        BLOCKS_LIMIT = 15;
    }

    public static Cryptocurrency getInstance() throws NoSuchAlgorithmException {
        if (Instance == null) {
            Instance = new MishkaCoin();

        } return Instance;
    }

}
