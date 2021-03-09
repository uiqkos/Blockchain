package blockchain.user;

import blockchain.Utils;
import blockchain.blocks.Block;
import blockchain.blocks.MinerBlock;
import java.util.Random;

public interface Miner extends CryptocurrencyMarketMember {

    default void mine() {

        Random random = new Random();
        int leadingZeros = getCryptocurrency().getLeadingZeros();

        MinerBlock block = new MinerBlock(new Block(getCryptocurrency().getLast()), this);

        while (!Utils.checkLeadingZeros(leadingZeros, block.getHash()))
            block.setMagicNumber(Math.abs(random.nextLong()));

        getCryptocurrency().addBlock(block);
    }
}
