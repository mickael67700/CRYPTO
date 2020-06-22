import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/*
 * Class to generate a private/public key pair and to generate the
 * save to files. The names of the output files must be specified on the command line.
 * For example: java rsaKeysGenerator privateKey.bin publicKey.bin
 * The private key is saved in 'private.bin' and the public key is saved in 'public.bin'.
 */
public class rsaKeysGenerator {
    /* Main method.
     * @param args[0] name of the file in which to save the private key
     * @param args[1] name of the file in which to save the public key
     */
    public static void main(String[] args) {
        /* Checking the arguments */
        if(args.length != 2) {
            System.err.println("Utilisation :");
            System.err.println("  java rsaKeyGenerator private public\n where:\n=> private: name of the file that will contain the private key\n" +
                    "=> public: name of the file that will contain the public key");
            System.exit(-1);
        }
        /* RSA generator */
        KeyPairGenerator keyPairGenerator = null;
        try {
            /* Choice of rsa algorithm */
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            /* 2048 bits or 1024 or 512 */
            keyPairGenerator.initialize(2048);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error during initialization of the process key generator : " + e);
            System.exit(-1);
        }
        /* Key pair generation */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        /* Private key backup */
        rsaKeyManagement.privateKeyBackup(keyPair.getPrivate(), args[0]);
        /* Public key backup */
        rsaKeyManagement.publicKeyBackup(keyPair.getPublic(), args[1]);
        System.out.println("*** Keys saved ***");
    }
}
