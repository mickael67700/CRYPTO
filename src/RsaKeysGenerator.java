import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/*
 * Class to generate a private/public key pair and to generate the save to files.
 */
public class RsaKeysGenerator {
    public static void generateKeys() {
        /* RSA generator */
        KeyPairGenerator keyPairGenerator = null;
        try {
            /* Choice of rsa algorithm */
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            /* 2048 bits or 3072 or 15360 */
            keyPairGenerator.initialize(3072);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error during initialization of the process key generator : " + e);
            System.exit(-1);
        }
        /* Key pair generation */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        /* Private key backup */
        System.out.println("File to save the private key (example: privateKey.bin) : ");
        String privateKeyFile = new Scanner(System.in).nextLine();
        RsaKeyManagement.privateKeyBackup(keyPair.getPrivate(), privateKeyFile);
        /* Public key backup */
        System.out.println("File to save the public key (example: publicKey.bin) : ");
        String publicKeyFile = new Scanner(System.in).nextLine();
        RsaKeyManagement.publicKeyBackup(keyPair.getPublic(), publicKeyFile);
        System.out.println("*** Keys saved ***");
    }
}
