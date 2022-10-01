/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * J.Cucco
 * and open the template in the editor.
 */
package encryption;

import java.util.*;



public class EncryptionSystem {
    
    private final Scanner scanner;
    private final Random random;
    private final ArrayList<Character> list;
    private ArrayList<Character> randomEncryptedList;
    private char character;
    private char[] letters;
    
    EncryptionSystem() {
        scanner = new Scanner(System.in);
        random = new Random();
        list = new ArrayList();
        randomEncryptedList = new ArrayList();
        character = ' ';
        
        newKey(1);
        askQuestion();
    }
    
    private void askQuestion() {
        while(true) {
            System.out.println("-----------------");
            System.out.println("Select an Option:");
            System.out.println("-----------------\n");
            System.out.println("'N' = New Key\n'G' = Get Key\n'E' = Encrypt a Message\n'D' = Decrypt a Message\n'Q' = Quit Program\n");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));
            
            switch(response) {
                case 'N' :
                    newKey(0);
                    break;   
                case 'G' :
                    getKey();
                    break;
                case 'E' :
                    encryptMessage();
                    break;
                case 'D' :
                    encryptMessage();
                    break;
                case 'Q' :
                    quitProgram();
                    break;
                default:
                    System.out.println("****************");
                    System.out.println("Input Not Valid.");
                    System.out.println("****************");
            }
        } 
    }
     public void newKey(int t) {
        character = ' ';
        list.clear();
        randomEncryptedList.clear();
        
        for(int i = 32; i < 127; i++) {
            list.add(character);
            character++;
        }
        
        randomEncryptedList = new ArrayList(list);
        Collections.shuffle(randomEncryptedList);
        if (t == 0) {
            System.out.println();
            System.out.println("*****  NEW KEY GENERATED!  *****");
            System.out.println();
            System.out.println("--> Type 'G' to See Full Key! <--");
            System.out.println();
        } else {
        }
    }
     public void getKey() {
        System.out.println();
        System.out.println("***************************");
        System.out.println("SECRET ENCRYPTION KEY BELOW");
        System.out.println("***************************");
        System.out.println();
        list.forEach(x -> {
            System.out.print(x);
        });
        System.out.println();
        randomEncryptedList.forEach(x -> {
            System.out.print(x);
        });
        System.out.println();
        System.out.println();
        System.out.println("(Use this key to Encrypt or Decrypt a Message)");
        System.out.println("               --OR--");
        System.out.println("--> Type 'N' for a New Encryption Key! <--");
        System.out.println();
    }
    
    private void encryptMessage() {
        System.out.println();
        System.out.println("Enter A Message to Encrypt: ");
        System.out.println();
        String message = scanner.nextLine();
        
        letters = message.toCharArray();
        
        for(int i = 0; i < letters.length; i++) {
            
            for(int e = 0; e < list.size(); e++) {
                if(letters[i] == list.get(e)) {
                    letters[i] = randomEncryptedList.get(e);
                    break;
                }
            }
        }
        System.out.println();
        System.out.println("Encrypted Message: ");
        System.out.println();
        for(char x : letters) {
            System.out.print(x);
        }
        System.out.println();
        System.out.println();
    }
    private void decryptMessage() {
        System.out.println();
      System.out.println("Enter A Message to Decrypt: ");
        System.out.println();
        String message = scanner.nextLine();
        
        letters = message.toCharArray();
        
        for(int i = 0; i < letters.length; i++) {
            
            for(int d = 0; d < randomEncryptedList.size(); d++) {
                if(letters[i] == randomEncryptedList.get(d)) {
                    letters[i] = list.get(d);
                    break;
                }
            }
        }
        System.out.println();
        System.out.println("Decrypted Message: "); 
        System.out.println();
        for(char x : letters) {
            System.out.print(x);
        }
        System.out.println();
        System.out.println();
    }
    private void quitProgram() {
        System.out.println();
        System.out.println("Thank you for using the Message Encryption System! Goodbye!");
        System.out.println();
        System.exit(0);
    }
}
