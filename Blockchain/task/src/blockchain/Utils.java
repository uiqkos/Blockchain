package blockchain;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Utils {
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkLeadingZeros(int leadingZeros, String str) {
        return str.substring(0, leadingZeros).equals("0".repeat(leadingZeros));
    }

    public static <T> List<T> nestedToList(T first, Function<T, T> getNested) {

        List<T> list = new ArrayList<>();

        new Iterator<T>(){
            private T current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                var prev = current;
                current = getNested.apply(current);
                return prev;
            }

        }.forEachRemaining(list::add);

        return list;
    }
}
