package blockchain.blocks;

import blockchain.TestMessage;

public class BlockChainBlock extends BlockDecorator {
    private final int id;
    private final int timeElapsed;
    private final String leadingZerosModification;

    public BlockChainBlock(AbstractBlock block, int id, int timeElapsed, String leadingZerosModification) {
        super(block);
        this.id = id;
        this.timeElapsed = timeElapsed;
        this.leadingZerosModification = leadingZerosModification;
    }

    public int getId() {
        return id;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    @Override
    public TestMessage getTestMessage() {
        return super
            .getTestMessage()
            .setId(id)
            .setLeadingZerosModification(leadingZerosModification)
            .setTimeElapsed(timeElapsed);
    }

    public String getLeadingZerosModification() {
        return leadingZerosModification;
    }
}
