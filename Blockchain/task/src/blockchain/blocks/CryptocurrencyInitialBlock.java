package blockchain.blocks;
import blockchain.cryptocurrency.Transaction;
import java.util.ArrayList;
import java.util.List;

public class CryptocurrencyInitialBlock extends AbstractBlock {

    private final List<Transaction> transactions = new ArrayList<>();

    public CryptocurrencyInitialBlock() {
        super(null);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public ImmutableBlock getPreviousBlock() {
        return null;
    }

    @Override
    public String getHash() {
        return "0";
    }

    @Override
    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }
}
