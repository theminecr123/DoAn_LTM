/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author DongTrieu
 */
public class Ceasar {
    public static String encryptCaesar(String plaintext, int key) {
        // Mã hóa Caesar
        StringBuilder encryptedText = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encryptedText.append((char)(((c - base + key) % 26) + base));
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }
    public static String decrypt(String cipher, int key) {
        key = 26 - key;
        return encryptCaesar(cipher, key);
    }
}
