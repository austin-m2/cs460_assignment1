/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs460_assignment1;

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
                newChar = (char) ((((currentChar + key) - 'A') % 26) + 'A');   
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
                newChar = (char) ((((currentChar - key) - 'A') % 26) + 'A');   
            } else {
                newChar = currentChar;
            }
            plaintext.append(newChar);
        }
        return plaintext.toString();
    }
    
}
