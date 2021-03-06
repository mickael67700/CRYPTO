import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Class to generate a private/public key pair and to generate the save to files.
 */
public class RsaKeysGenerator {
    public static void generateKeys() {
        /* RSA generator */
        KeyPairGenerator keyPairGenerator = null;
        try {
            System.out.println("Key size ? (2048, 3072, 15360 for example )  : ");
            int keySize = new Scanner(System.in).nextInt();
            /* Choice of rsa algorithm */
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            /* 2048 bits or 3072 or 15360 */
            keyPairGenerator.initialize(keySize);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error during initialization of the process key generator : " + e);
            System.exit(-1);
        }
        /* Key pair generation and generation time calculation  */
        long lStartTime = Instant.now().toEpochMilli();
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        long lEndTime = Instant.now().toEpochMilli();
        long result = lEndTime - lStartTime;
        System.out.println("Keys generated in milliseconds : " + result);

        /* Private key backup */
        System.out.println("File to save the private key (example: privateKey.bin) : ");
        String privateKeyFile = new Scanner(System.in).nextLine();
        RsaKeyManagement.privateKeyBackup(keyPair.getPrivate(), privateKeyFile);

        /* Public key backup */
        System.out.println("File to save the public key (example: publicKey.bin) : ");
        String publicKeyFile = new Scanner(System.in).nextLine();
        RsaKeyManagement.publicKeyBackup(keyPair.getPublic(), publicKeyFile);

        /* Display informations */
        System.out.println("*** Keys saved ***\n\nPrivate Key : " + privateKeyFile + "\nPublic Key  : " + publicKeyFile);
        System.out.println("\nAlgorithm public key: " + keyPair.getPublic().getAlgorithm() + "\nAlgorithm private key: " + keyPair.getPrivate().getAlgorithm());
        System.out.println(keyPair.getPublic().toString() + "\n");
        System.out.println(keyPair.getPrivate().toString() + "\n");
    }
}
