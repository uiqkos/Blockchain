package blockchain;

public class TestMessage {
    private int minerId = -1;
    private String rewardMessage;
    private int Id = -1;
    private long timeStamp = -1;
    private long magicNumber = -1;
    private String prevHash;
    private String hash;
    private String blockData;
    private int timeElapsed;
    private String leadingZerosModification;

    @Override
    public String toString() {
        return String.format(
                "Block:\n" +
                "Created by miner # %s\n" +
                "%s\n" +
                "Id: %s\n" +
                "Timestamp: %s\n" +
                "Magic number: %s\n" +
                "Hash of the previous block: \n" +
                "%s\n" +
                "Hash of the block: \n" +
                "%s\n" +
                "Block data: %s\n" +
                "Block was generating for %s seconds\n" +
                "%s\n",
                minerId, rewardMessage, Id, timeStamp,
                magicNumber, prevHash, hash, blockData,
                timeElapsed, leadingZerosModification
        );
    }

    public TestMessage setMinerId(int minerId) {
        this.minerId = minerId;
        return this;
    }

    public TestMessage setId(int id) {
        Id = id;
        return this;
    }

    public TestMessage setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public TestMessage setMagicNumber(long magicNumber) {
        this.magicNumber = magicNumber;
        return this;
    }

    public TestMessage setPrevHash(String prevHash) {
        this.prevHash = prevHash;
        return this;
    }

    public TestMessage setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public TestMessage setBlockData(String blockData) {
        this.blockData = blockData;
        return this;
    }

    public TestMessage setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
        return this;
    }

    public TestMessage setLeadingZerosModification(String leadingZerosModification) {
        this.leadingZerosModification = leadingZerosModification;
        return this;
    }

    public TestMessage setRewardMessage(String rewardMessage) {
        this.rewardMessage = rewardMessage;
        return this;
    }
}
