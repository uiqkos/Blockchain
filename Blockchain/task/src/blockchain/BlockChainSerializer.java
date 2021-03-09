package blockchain;

import java.io.*;

public class BlockChainSerializer {
    public static String defaultFilePath = "path/to/blockChainFile";

    public static BlockChain fromStream(InputStream stream) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(stream))) {
            return (BlockChain) objectInputStream.readObject();
        }
    }

    public static BlockChain fromFile(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            return fromStream(fileInputStream);
        }
    }

    public static void toStream(BlockChain blockChain, OutputStream stream) throws IOException {
        try (ObjectOutputStream objectStream = new ObjectOutputStream(new BufferedOutputStream(stream))){
            objectStream.writeObject(blockChain);
        }
    }

    public static void toFile(BlockChain blockChain, String fileName) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
            toStream(blockChain, fileOutputStream);
        }
    }

    public static void toDefaultFile(BlockChain blockChain) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(defaultFilePath)){
            BlockChainSerializer.toStream(blockChain, fileOutputStream);
        }
    }

    public static BlockChain fromDefaultFile() throws IOException, ClassNotFoundException {
        return BlockChainSerializer.fromFile(defaultFilePath);
    }
}
