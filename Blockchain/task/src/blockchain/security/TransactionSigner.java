package blockchain.security;
import blockchain.cryptocurrency.Transaction;

import java.security.*;

public class TransactionSigner {

    public static void signTransaction(Transaction transaction, PrivateKey key)
            throws InvalidKeyException, NoSuchAlgorithmException, SignatureException
    {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(key);
        signature.update(transaction.toString().getBytes());
        transaction.setSignature(signature.sign());
    }

    public static boolean verifyTransaction(Transaction transaction)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
    {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(transaction.getKey());
        signature.update(transaction.toString().getBytes());
        return signature.verify(transaction.getSignature());
    }
}
