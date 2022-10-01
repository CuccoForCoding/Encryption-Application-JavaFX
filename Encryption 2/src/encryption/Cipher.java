package encryption;

import java.util.Scanner;

/**
 *
 * @author josephcucco
 */
public class Cipher {

    public static char cipher(char ciph, int key) {

        final int keyLength = 26;
        final char shuffledASCII = Character.isUpperCase(ciph) ? 'A' : 'a';
        final int cipherShift = key % keyLength;

        char shifted = (char) (ciph - shuffledASCII);

        shifted = (char) ((shifted + cipherShift + keyLength) % keyLength);

        return (char) (shifted + shuffledASCII);
    }

    // Rotate a string k-positions
    public static String cipher(String str, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(cipher(str.charAt(i), key));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String password;
        int key;

        System.out.print("Enter a Secure Password: ");
        password = keyboard.nextLine();

        do {
            System.out.println();
            System.out.print("Enter a key between 1 and 25: ");
            key = keyboard.nextInt();

            if (key < 1 || key > 25) {
                System.out.println();
                System.out.printf(" The key must be between 1 and 25, you entered %d.\n", key);
            }
        } while (key < 1 || key > 25);
        
        System.out.println("");
        System.out.println("Your Password:\t" + password);
        
        String encryption = cipher(password, key);
        System.out.println();
        System.out.println("**********************************************************************");
        System.out.println("Encrypted Password:\t" + encryption);
        System.out.println("**********************************************************************");
        System.out.println();
        System.out.println("**********************************************************************");
        System.out.println("Decrypted Password:\t" + cipher(encryption, -key));
        System.out.println("**********************************************************************");

    }
}
