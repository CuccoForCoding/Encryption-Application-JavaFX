/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import static encryption.Cipher.cipher;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import java.lang.String;
import javafx.event.ActionEvent;

/**
 *
 * @author nofoo
 */
public class ESSController implements Initializable {
    
    //caesar cipher code
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
    
    public int kst;
    public String password;
    public String pt;
    public int key;
    public String keyv;
    private final Random random = new Random();
    private char character = ' ';
    private char[] letters;
    private final ArrayList<Character> list = new ArrayList();
    private ArrayList<Character> randomEncryptedList = new ArrayList();
    
    @FXML
    private ComboBox S1;
    
    @FXML
    private Button gb;

    @FXML
    private TextField inb;

    @FXML
    private ComboBox msb;

    @FXML
    public TextArea outb;
    
   
    public void message() throws IOException{
       String em = S1.getSelectionModel().getSelectedItem().toString();
       String os = msb.getSelectionModel().getSelectedItem().toString();
       
        //ASCII Code
       if(em.equals("ASCII")){
         if(os.equals("New Key")){
           newKey(0);
        }else if(os.equals("Get Key")){
           getKey();
        }else if(os.equals("Encrypt A Message")){
           encryptMessage();
        }else if(os.equals("Decrypt A Message")){
           decryptMessage();
       } 
      }
       
       //Vingenere Cipher
       if(em.equals("Vingenere Cipher")){
         if(os.equals("New Key")){
           outb.setText("Enter the key in UPPER Case ");
	   keyv = inb.getText();
        }else if(os.equals("Get Key")){
           outb.setText(keyv);
        }else if(os.equals("Encrypt A Message")){
           String EMessage = inb.getText();
           String encryptMessage = encrypt(EMessage, keyv);
           outb.setText("The encrypted message is: " + encryptMessage);
        }else if(os.equals("Decrypt A Message")){
           String DMessage = inb.getText();
           String decryptMessage = decrypt(DMessage, keyv);
           outb.setText("The decrypted message is: " + decryptMessage);
       }
       }
       
       
       //caesar cipher code
       if(em.equals("Caesar Cipher") && os.equals("New Key")){
           String keys = inb.getText();
           key = Integer.parseInt(keys);
           kst = key;
           outb.setText("Enter the amount you would like to shift your sentence by (between 1 and 25)");
       }else if(em.equals("Caesar Cipher") && os.equals("Get Key")){
           if (key < 1 || key > 25) {
                outb.setText("The key must be between 1 and 25, you entered " + key);
            }else{
           outb.setText(" " + key);
           }
       }else if(em.equals("Caesar Cipher") && os.equals("Encrypt A Message")){
           if(key < 1 || key > 25);
        
        password = inb.getText();
        outb.setText("Your Password:\t" + password);
        System.out.print(key);
        String encryption = cipher(password, key);
        outb.appendText("\n**********************************************************************");
        outb.appendText("\nEncrypted Password:\t" + encryption);
        outb.appendText("\n**********************************************************************");
        
       }else if(em.equals("Caesar Cipher") && os.equals("Decrypt A Message")){
           if(key < 1 || key > 25);
        kst = (kst*-1)+3;
        pt = inb.getText();
        outb.setText("Your Encrypted Password:\t" + pt);
        
        System.out.print(key);
        String encryption = cipher(pt, -key);
        outb.appendText("\n**********************************************************************");
        outb.appendText("\nDecrypted Password:\t" + encryption);
        outb.appendText("\n**********************************************************************"); 
       }
       
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> msb1 = FXCollections.observableArrayList("New Key", "Get Key" , "Encrypt A Message" , "Decrypt A Message");
       msb.setItems(msb1);
       ObservableList<String> S1L = FXCollections.observableArrayList("ASCII" , "Caesar Cipher", "Vingenere Cipher");
       S1.setItems(S1L);
    }

    //append text so it prints out nicely in the GUI (String Version)
    void appendTextC(Character x) {
        outb.setText(outb.getText() + x);
    }
    //Character Version
    public void appendText(String t){
        outb.setText(outb.getText() + t);
    }
    
    
    //functions to call that actually make the key and stuff
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
            outb.setText("*****  NEW KEY GENERATED! *****\n");
            appendText("Select the Get Key option to view it");
        } else {
        }
    }
    
    public void getKey() {
       outb.setText("*********** SECRET ENCRYPTION KEY BELOW ***********\n\n");
       for (int i = 0; i < list.size(); i++) {
        appendTextC(list.get(i));
        }
       for (int i = 0; i < randomEncryptedList.size(); i++) {
        appendTextC(randomEncryptedList.get(i));
        }
        appendText("\n\n(Use this key to Encrypt or Decrypt a Message)"); 
    }
    
    private void encryptMessage() {
        String message = inb.getText();
        
        letters = message.toCharArray();
        
        for(int i = 0; i < letters.length; i++) {
            
            for(int e = 0; e < list.size(); e++) {
                if(letters[i] == list.get(e)) {
                    letters[i] = randomEncryptedList.get(e);
                    break;
                }
            }
        }
        outb.setText("Encrypted Message: ");
        for(char x : letters) {
            appendTextC(x);
        }
    }
    private void decryptMessage() {
        String message = inb.getText();
        
        letters = message.toCharArray();
        
        for(int i = 0; i < letters.length; i++) {
            
            for(int d = 0; d < randomEncryptedList.size(); d++) {
                if(letters[i] == randomEncryptedList.get(d)) {
                    letters[i] = list.get(d);
                    break;
                }
            }
        }
        outb.setText("Decrypted Message: "); 
        for(char x : letters) {
            appendTextC(x);
        }
    }
    
    //Vingenere Cipher Methods
	public static String encrypt(String Message, String Key) {
		String EMessage = "";
		Message = Message.toUpperCase();
		for (int i = 0, j = 0; i < Message.length(); i++) {
			char letter = Message.charAt(i);
			EMessage += (char)(((letter - 65) + (Key.charAt(j)-65)) % 26 + 65);
			j = ++j % Key.length();
		}
		return EMessage;
	}

	public static String decrypt(String Message, String Key) {
		String DMessage = "";
		Message = Message.toUpperCase();
		for (int i = 0, j = 0; i < Message.length(); i++) {
			char letter = Message.charAt(i);
			DMessage += (char)((letter - Key.charAt(j) + 26) % 26 + 65);
			j = ++j % Key.length();
		}
		return DMessage;
	}
   
}
   
    
    


