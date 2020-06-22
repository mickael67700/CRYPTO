import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

/*
 * Class for decrypting a message using a private key.
 */
public class Decryption {
    /* Main method.
     * @param args[0] name of the file where the private key is located
     * @param args[1] message to decipher
     * */
    public static void main(String[] args) {
        /* Checking the arguments */
        if(args.length != 2) {
            System.err.println("Utilisation :");
            System.err.println("  java Decryption privateKey message output\n where: \n" +
                    "=> privateKey : name of the file containing the private key\n" +
                    "=> message : message to cipeher");
            System.exit(-1);
        }
        /* Private key retrieval */
        PrivateKey privateKey = RsaKeyManagement.readingPrivateKey(args[0]);
        /* Loading the encrypted message */
        byte[] encryptedMessage = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(args[1]);
            encryptedMessage = new byte[fileInputStream.available()];
            fileInputStream.read(encryptedMessage);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Deciphering the message */
        byte[] bytes= null;
        try {
            Cipher decipher = Cipher.getInstance("RSA");
            decipher.init(Cipher.DECRYPT_MODE, privateKey);
            assert encryptedMessage != null;
            bytes = decipher.doFinal(encryptedMessage);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        assert bytes != null;
        String messageDecrypted = new String(bytes);
        System.out.println("Message decrypted : " + messageDecrypted);
    }
}
