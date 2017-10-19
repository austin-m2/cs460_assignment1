/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs460_assignment1;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author morri
 */
public class Cs460_assignment1 {
    
    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choice = 0;
        
        while(choice != 4) {
            System.out.println("\nChoose an option:\n"
                + "1) Encrypt\n"
                + "2) Decrypt\n"
                + "3) Letter Frequency Attack\n"
                + "4) Exit");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
            case 1:
                encrypt();
                break;
            case 2:
                decrypt();
                break;
            case 3:
                break;
            default:
                break;
            }
        }
        
        
    }
    
    public static void encrypt() {
        System.out.println("Input string to be encrypted:");
        String input = sc.nextLine();
        String plaintext = input.toUpperCase();
        
        System.out.println("Input key:");
        int key = sc.nextInt();
        
        String ciphertext = caesarEncrypt(plaintext, key);        
        System.out.println("Encrypted text:\n" + ciphertext);
        
        
        freqAttack(ciphertext);
        
        
        
    }
    
    public static void decrypt() {
        System.out.println("Input string to be decrypted:");
        String input = sc.nextLine();
        String ciphertext = input.toUpperCase();
        
        System.out.println("Input key:");
        int key = sc.nextInt();
        
        String plaintext = caesarDecrypt(ciphertext, key);        
        System.out.println("Decrypted text:\n" + plaintext);
    }
    
    public static String caesarEncrypt(String plaintext, int key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);
            char newChar;
            if(Character.isAlphabetic(currentChar)) {
                newChar = (char) ((char) Math.floorMod(currentChar - 'A' + key, 26) + 'A');
            } else {
                newChar = currentChar;
            }
            ciphertext.append(newChar);
        }
        return ciphertext.toString();
    }
    
    public static String caesarDecrypt(String ciphertext, int key) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);
            char newChar;
            if(Character.isAlphabetic(currentChar)) {
                newChar = (char) ((char) Math.floorMod(currentChar - 'A' - key, 26) + 'A');
            } else {
                newChar = currentChar;
            }
            plaintext.append(newChar);
        }
        return plaintext.toString();
    }
    
    public static void freqAttack(String str) {
        double[] entropyArr  = new double[26];
        int[] entropyKeys = new int[26];
        
        for (int i = 0; i < 26; i++) {
            String ptCandidate = caesarDecrypt(str, i);
            entropyArr[i] = getEntropy(ptCandidate);
            entropyKeys[i] = i;
        }
        
        //bubble sort both arrays
        int n = 26;
        double temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (entropyArr[j - 1] > entropyArr[j]) {
                    temp = entropyArr[j - 1];
                    entropyArr[j - 1] = entropyArr[j];
                    entropyArr[j] = temp;
                    
                    temp = entropyKeys[j - 1];
                    entropyKeys[j - 1] = entropyKeys[j];
                    entropyKeys[j] = (int) temp;
                }
            }
        }
        
        System.out.println("Top 10 possible plaintexts:");
        for (int i = 0; i < 10; i++) {
            int key = entropyKeys[i];
            System.out.println((i + 1) + ") " + caesarDecrypt(str, key));
        }
        
    }
    
    public static double getEntropy(String str) {
        double[] freq = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228,
                         0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025,
                         0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987,
                         0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150,
                         0.01974, 0.00074};
        
        str = str.toUpperCase();
        double res = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                res += -Math.log(freq[ch - 'A']);
            }
        }
        return res;
    }
    
}
