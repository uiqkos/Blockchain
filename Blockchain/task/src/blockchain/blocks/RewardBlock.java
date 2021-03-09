package blockchain.blocks;

import blockchain.TestMessage;

public class RewardBlock extends BlockDecorator {

    private final String rewardMessage;

    public RewardBlock(AbstractBlock block, String rewardMessage) {
        super(block);
        this.rewardMessage = rewardMessage;
    }

    public String getRewardMessage() {
        return rewardMessage;
    }

    @Override
    public TestMessage getTestMessage() {
        return super
            .getTestMessage()
            .setRewardMessage(rewardMessage);
    }
}
