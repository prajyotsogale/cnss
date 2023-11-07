import java.util.Scanner;

public class VigenereCipher {

    public static String vigenereCipherEncrypt(String plaintext, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(plainChar)) {
                char base = Character.isUpperCase(plainChar) ? 'A' : 'a';
                char encryptedChar = (char) (((plainChar - base + keyChar - 'A') % 26) + base);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(plainChar);  // Keep non-alphabet characters as is.
            }
        }

        return encryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the Vigenere key: ");
        String key = scanner.nextLine();

        String encryptedText = vigenereCipherEncrypt(plaintext, key);
        System.out.println("Encrypted: " + encryptedText);
    }
}
