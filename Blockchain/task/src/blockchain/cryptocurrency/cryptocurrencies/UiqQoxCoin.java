package blockchain.cryptocurrency.cryptocurrencies;

import blockchain.cryptocurrency.Cryptocurrency;

public class UiqQoxCoin extends Cryptocurrency {

    private static UiqQoxCoin Instance;

    private UiqQoxCoin() {
        super("UiqQoxCoin", "quQoin");
    }

    public static Cryptocurrency getInstance() {
        if (Instance == null) {
            Instance = new UiqQoxCoin();

        } return Instance;
    }

}
