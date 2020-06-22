import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Scanner;

/*
 * Class for decrypting a message using a private key.
 */
public class Decryption {
    public Decryption() {
    }

    public static void decryption() {
        System.out.println("Filename for private key (example privateKey.bin) : ");
        String privateKeyFile = new Scanner(System.in).nextLine();
        System.out.println("Filename of ciphered message (example output) : ");
        String encryptedMessageFile = new Scanner(System.in).nextLine();
        /* Private key retrieval */
        PrivateKey privateKey = RsaKeyManagement.readingPrivateKey(privateKeyFile);
        /* Loading the encrypted message */
        byte[] encryptedMessage = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(encryptedMessageFile);
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
