package blockchain.blocks;

import blockchain.TestMessage;
import blockchain.cryptocurrency.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractBlock implements Serializable {
    private final ImmutableBlock previousBlock;

    protected AbstractBlock(ImmutableBlock previousBlock) {
        this.previousBlock = previousBlock;
    }

    abstract public String getHash();
    abstract public List<Transaction> getTransactions();

    public TestMessage getTestMessage() {
        return new TestMessage();
    }

    public ImmutableBlock getPreviousBlock() {
        return previousBlock;
    }
    public String getPreviousBlockHash() {
        return getPreviousBlock().getHash();
    }

    @Override
    public String toString() { return getTestMessage().toString(); }
}
