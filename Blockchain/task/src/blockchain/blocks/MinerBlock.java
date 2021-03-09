package blockchain.blocks;

import blockchain.TestMessage;
import blockchain.Utils;
import blockchain.user.Miner;

public class MinerBlock extends BlockDecorator {

    private long magicNumber = 0;
    private final Miner miner;

    public MinerBlock(AbstractBlock block, Miner miner) {
        super(block);
        this.miner = miner;
    }

    @Override
    public String getHash() {
        return Utils.applySha256(block.getHash() + magicNumber + miner.getId());
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(long magicNumber) {
        this.magicNumber = magicNumber;
    }

    public Miner getMiner() {
        return miner;
    }

    @Override
    public TestMessage getTestMessage() {
        return super
            .getTestMessage()
            .setHash(getHash())
            .setMagicNumber(magicNumber)
            .setMinerId(miner.getId());
    }
}
