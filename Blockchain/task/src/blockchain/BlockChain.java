package blockchain;

import blockchain.blocks.AbstractBlock;
import blockchain.blocks.BlockChainBlock;
import blockchain.blocks.ImmutableBlock;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlockChain implements Serializable {
    private static final long serialVersionUID = -7336928944786400718L;

    private volatile ImmutableBlock last;
    private volatile int idCounter = 0;
    private volatile int leadingZeros = 1;
    private LocalTime lastBlockReceiptDate = LocalTime.now();

    public BlockChain() {
    }

    public boolean addBlock(AbstractBlock block) {
        if (!checkBlock(block)) return false;

        synchronized (this) {
            var timeElapsed = LocalTime.now().toSecondOfDay() - lastBlockReceiptDate.toSecondOfDay();

            if (timeElapsed > 0)
                leadingZeros = Math.max(leadingZeros - 1, 1);
            else // with ++ time limit
                leadingZeros ++;

            last =
                new ImmutableBlock(
                    new BlockChainBlock(
                        block, idCounter++, timeElapsed,
                        "N changed to " + leadingZeros
                    )
                );

            lastBlockReceiptDate = LocalTime.now();

        }

        return true;
    }

    public boolean checkBlock(AbstractBlock block) {
        return Utils.checkLeadingZeros(leadingZeros, block.getHash()) &&
               block.getPreviousBlock() == last;
    }

    public List<ImmutableBlock> getBlocks() {
        var list = new ArrayList<>(List.copyOf(Utils.nestedToList(last, ImmutableBlock::getPreviousBlock)));
        Collections.reverse(list);
        return list;
    }

    public ImmutableBlock getLast() {
        return last;
    }

    public int getLeadingZeros() {
        return leadingZeros;
    }
}
