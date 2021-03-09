package blockchain;

import blockchain.blocks.MinerBlock;
import blockchain.cryptocurrency.cryptocurrencies.MishkaCoin;
import blockchain.user.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        var users = List.of(
            new User("Mishka", MishkaCoin.getInstance()),
            new User("Sanya", MishkaCoin.getInstance()),
            new User("UixQqos", MishkaCoin.getInstance()),
            new User("Vled", MishkaCoin.getInstance())
        );

        users.forEach(MishkaCoin.getInstance()::addUser);
        users.forEach(executor::submit);

        executor.awaitTermination(14, TimeUnit.SECONDS);

//        MishkaCoin
//            .getInstance()
//            .getBlockChain()
//            .getBlocks()
//            .forEach(System.out::println);
    }
}
