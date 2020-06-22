import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Class for encrypting a message using a public key.
 * The encrypted message is saved in a file
 */
public class Encryption {
    public Encryption() {
    }

    public static void encryption() throws IOException {
        System.out.println("text to Encryption : ");
        String messageToCipher = new Scanner(System.in).nextLine();
        System.out.println("Filename for public key (example publicKey.bin) : ");
        String publicKeyFile = new Scanner(System.in).nextLine();
        System.out.println("Filename for backup encrypted message (example output) : ");
        String backupFile = new Scanner(System.in).nextLine();
        /* Public Key recuperation */
        PublicKey publicKey = RsaKeyManagement.readPublicKey(publicKeyFile);
        /* Message Encryption */
        byte[] bytes = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            bytes = cipher.doFinal(messageToCipher.getBytes());
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        /* Backup encrypted message */
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(backupFile);
            assert bytes != null;
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*Reading encrypted file lines*/
        //System.setProperty( "file.encoding", "UTF8" );
        System.out.println("Encrypted message backup in '" + backupFile+"' file\nCiphered message: \n");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(backupFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }
}