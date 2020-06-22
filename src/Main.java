import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice = 0;
        while (choice != 4) {
            System.out.println("1. Generate new private key and new public key \n2. Encrypt file\n3. Decrypt file\n4. Exit \nChoice: ");
            choice = new Scanner(System.in).nextInt();
            if (choice == 1) {
                RsaKeysGenerator.generateKeys();
            }
            if (choice == 2) {
                Encryption.encryption();
            }
            if (choice == 3) {
                Decryption.decryption();
            }
            if (choice == 4){
                System.exit(-1);
            }
        }
    }
}
