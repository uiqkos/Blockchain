package blockchain.blocks;

import blockchain.TestMessage;
import blockchain.cryptocurrency.Transaction;

import java.util.List;

public class ImmutableBlock {
    private final AbstractBlock block;

    public ImmutableBlock(AbstractBlock block) {
        this.block = block;
    }

    public String getHash() {
        return block.getHash();
    }

    public List<Transaction> getTransactions() {
        return block.getTransactions();
    }

    public TestMessage getTestMessage() {
        return block.getTestMessage();
    }

    public ImmutableBlock getPreviousBlock() {
        return block.getPreviousBlock();
    }

    public String getPreviousBlockHash() {
        return block.getPreviousBlockHash();
    }

    public String toString() {
        return block.toString();
    }

}
