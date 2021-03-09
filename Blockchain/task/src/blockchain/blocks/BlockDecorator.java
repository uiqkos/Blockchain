package blockchain.blocks;

import blockchain.TestMessage;
import blockchain.cryptocurrency.Transaction;

import java.util.List;

public abstract class BlockDecorator extends AbstractBlock {
    private static final long serialVersionUID = 7766183767721928658L;
    protected final AbstractBlock block;

    public BlockDecorator(AbstractBlock block) {
        super(block.getPreviousBlock());
        this.block = block;
    }

    @Override
    public String getHash() {
        return block.getHash();
    }

    @Override
    public TestMessage getTestMessage() {
        return block.getTestMessage();
    }

    @Override
    public List<Transaction> getTransactions() {
        return block.getTransactions();
    }
}
