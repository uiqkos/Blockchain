package blockchain.cryptocurrency;

import blockchain.BlockChain;
import blockchain.blocks.*;
import blockchain.security.TransactionSigner;
import blockchain.user.CoinSender;
import blockchain.user.User;
import blockchain.Utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

public abstract class Cryptocurrency {
    public static long BLOCKS_LIMIT = 15;
    private static final int START_COINS = 100;
    private static final int COINS_PER_BLOCK = 100;

    protected final BlockChain blockChain = new BlockChain();
    protected final ArrayList<Transaction> currentBlockTransactions = new ArrayList<>();

    private final List<User> users = new ArrayList<>();
    private final CoinSender superUser = new CoinSender() {
        @Override public Cryptocurrency getCryptocurrency() { return null; }
        @Override public int getId() { return 0; }
        @Override public String getName() { return "Superuser"; }
        @Override public PublicKey getPublicKey() { return null; }
        @Override public void send(Transaction transaction) { currentBlockTransactions.add(transaction); }
    };

    private final String fullName;
    private final String shortName;

    protected Cryptocurrency(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
        blockChain.addBlock(new CryptocurrencyInitialBlock());
    }

    public synchronized boolean addBlock(MinerBlock block) {

        if (
            getBlockChainSize() <= BLOCKS_LIMIT &&
            blockChain.checkBlock(block)
        ) {
            String rewardMessage = String.format(
                "%s gets %s %s", block.getMiner().getName(),
                COINS_PER_BLOCK, getShortName()
            );

            currentBlockTransactions.add(
                new Transaction(
                    superUser, block.getMiner(),
                    this, COINS_PER_BLOCK,
                    superUser.getPublicKey()
                )
            );

            RewardBlock rewardBlock =
                new RewardBlock(
                    new TransactionBlock(
                        block, getCurrentBlockTransactions()
                    ), rewardMessage
                );

            blockChain.addBlock(rewardBlock);
            currentBlockTransactions.clear();

            System.out.println(blockChain.getLast());
            return true;
            
        } return false;
    }

    public synchronized boolean makeTransaction(Transaction transaction) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (TransactionSigner.verifyTransaction(transaction)) {
            try {
                var allTransactions = Utils
                    .nestedToList(
                        blockChain.getLast(),
                        ImmutableBlock::getPreviousBlock
                    ).stream()
                    .map(ImmutableBlock::getTransactions)
                    .flatMap(List::stream);

                var balance = allTransactions
                    .filter(tr ->
                        tr.getFrom().equals(transaction.getFrom()) ||
                        tr.getTo().equals(transaction.getFrom())
                    )
                    .mapToInt(tr ->
                        tr.getFrom().equals(transaction.getFrom())
                            ? -tr.getCount()
                            : tr.getCount())
                    .sum();

                if (
                    balance >= transaction.getCount() &&
                    !transaction.getFrom().equals(transaction.getTo())
                ) {
                    currentBlockTransactions.add(transaction);
                    return true;
                }

            } catch (Exception e) { e.printStackTrace(); }

        } return false;

    }

    public boolean addUser(User user) {
        if (!users.contains(user)){
            synchronized (this) {
                currentBlockTransactions.add(
                    new Transaction(
                        superUser, user, this,
                        START_COINS, superUser.getPublicKey()
                    )
                );
                return users.add(user);
            }

        } return false;

    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public int getBlockChainSize() {
        return blockChain.getBlocks().size();
    }

    public ImmutableBlock getLast() {
        return blockChain.getLast();
    }

    public int getLeadingZeros() {
        return blockChain.getLeadingZeros();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Transaction> getCurrentBlockTransactions() {
        return List.copyOf(currentBlockTransactions);
    }
}
