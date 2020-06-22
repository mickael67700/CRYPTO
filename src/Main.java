import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        System.out.println("1. Generate new private key and new public key \n2. Encrypt file\n3. Decrypt file \nChoice: ");
        int choice = new Scanner(System.in).nextInt();
        if(choice == 1){
            RsaKeysGenerator rsaKeysGenerator = new RsaKeysGenerator();
            RsaKeysGenerator.generateKeys();
        }
        if (choice == 2 ){
            Encryption encryption = new Encryption();
            Encryption.encryption();
        }
        if(choice == 3 ){
            Decryption decryption = new Decryption();
            decryption.decryption();
        }
    }
}
