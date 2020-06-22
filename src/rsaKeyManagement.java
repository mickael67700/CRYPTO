import java.io.*;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/*
*Class for saving and loading private or public keys
*from files.
 */
public class rsaKeyManagement {
    /*
     *Saving the public key to a file.
     */
    public static void publicKeyBackup(PublicKey publicKey, String fileName){
        RSAPublicKeySpec rsaPublicKeySpec = null;
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            rsaPublicKeySpec = factory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            file.writeObject(rsaPublicKeySpec.getModulus());
            file.writeObject(rsaPublicKeySpec.getPublicExponent());
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found : " + e);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Error when saving the key : " + e);
            System.exit(-1);
        }
    }

    /*
     *Saving the private key in a file.
     */
    public static void privateKeyBackup(PrivateKey privateKey, String fileName){
       RSAPrivateKeySpec rsaPrivateKeySpec = null;
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            rsaPrivateKeySpec = factory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            file.writeObject(rsaPrivateKeySpec.getModulus());
            file.writeObject(rsaPrivateKeySpec.getPrivateExponent());
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found : " + e);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Error when saving the key : " + e);
            System.exit(-1);
        }
    }

    /*
     *Reading a private key from a file.
     */
    public static PrivateKey readingPrivateKey(String fileName){
        BigInteger modulo = null;
        BigInteger exposant = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            modulo = (BigInteger) objectInputStream.readObject();
            exposant = (BigInteger) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found : " + e);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Error when reading private key : " + e);
            System.exit(-1);
        } catch (ClassNotFoundException e) {
           System.out.println("Incorrect key file : " + e);
            System.exit(-1);
        }

        PrivateKey privateKey = null;
        try {
            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulo,exposant);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("RSA algorithm unknown : " + e);
            System.exit(-1);
        } catch (InvalidKeySpecException e) {
            System.out.println("Incorrect Key specification : " + e);
            System.exit(-1);
        }
        return privateKey;
    }

    /*
     *Reading a public key from a file.
     */

    public static PublicKey readPublicKey(String fileName){
        BigInteger modulo = null;
        BigInteger exposant = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            modulo = (BigInteger) objectInputStream.readObject();
            exposant = (BigInteger) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found : " + e);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Error when reading private key : " + e);
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            System.out.println("Incorrect key file : " + e);
            System.exit(-1);
        }
        PublicKey publicKey = null;
        try {
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulo,exposant);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("RSA algorithm unknown : " + e);
            System.exit(-1);
        } catch (InvalidKeySpecException e) {
            System.out.println("Incorrect Key specification : " + e);
            System.exit(-1);
        }
        return publicKey;
    }
}
