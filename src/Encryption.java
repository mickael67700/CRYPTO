import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

/*
 * Class for encrypting a message using a public key.
 * The encrypted message is saved in a file
 */
public class Encryption {
    public static void main(String[] args) {
        /* Checking the arguments */
        if (args.length != 3) {
            System.err.println("Utilisation : java Encryption publicKey message output \n where:\n" +
                    "=> publicKey : name of the file containing the public key \n => message : message to encryption\n" +
                    "=> output : file containing the encrypted message");
            System.exit(-1);
        }
        System.out.println("Message to encryption : " + args[1]);

        /* Public Key recuperation */
        PublicKey publicKey = RsaKeyManagement.readPublicKey(args[0]);
        /* Message Encryption */
        byte[] bytes = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            bytes = cipher.doFinal(args[1].getBytes());
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        /* Backup encrypted message */
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(args[2]);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Encrypted message backup in " + args[2]);
    }
}