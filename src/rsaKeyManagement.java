import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

/*
*Class for saving and loading private or public keys
*from files.
 */
public class rsaKeyManagement {
    public static void publicKeyBackup(PublicKey publicKey, String fileName){
        RSAPublicKeySpec specification = null;
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            specification = factory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            file.writeObject(specification.getModulus());
            file.writeObject(specification.getPublicExponent());
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found : " + e);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Error when saving the key : " + e);
            System.exit(-1);
        }
    }
}
