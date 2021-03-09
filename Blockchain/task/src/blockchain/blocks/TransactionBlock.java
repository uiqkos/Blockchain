package blockchain.blocks;

import blockchain.TestMessage;
import blockchain.cryptocurrency.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionBlock extends BlockDecorator {

    private final List<Transaction> transactions;

    public TransactionBlock(AbstractBlock block, List<Transaction> transactions) {
        super(block);
        this.transactions = transactions;
    }

    @Override
    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    @Override
    public TestMessage getTestMessage() {
        if (transactions.isEmpty())
            return super
                .getTestMessage()
                .setBlockData("no messages");

        return super
                .getTestMessage()
                .setBlockData('\n' + transactions
                    .stream()
                    .map(Transaction::toString)
                    .collect(Collectors.joining("\n"))
                );
    }
}

