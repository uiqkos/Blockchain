package blockchain.blocks;

import blockchain.TestMessage;
import blockchain.cryptocurrency.Transaction;
import blockchain.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block extends AbstractBlock {
    private final long timeStamp = new Date().getTime();

    public Block(ImmutableBlock previousBlock) {
        super(previousBlock);
    }

    public String getHash() {
        return Utils.applySha256(getPreviousBlock().getHash() + timeStamp);
    }

    @Override
    public String getPreviousBlockHash() {
        if (getPreviousBlock() == null)
            return "0";
        return super.getPreviousBlockHash();
    }

    @Override
    public TestMessage getTestMessage() {
        return new TestMessage()
            .setHash(getHash())
            .setPrevHash(getPreviousBlockHash())
            .setTimeStamp(timeStamp);
    }

    @Override
    public List<Transaction> getTransactions() {
        return new ArrayList<>();
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}


