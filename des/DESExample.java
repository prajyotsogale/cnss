import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class DESExample {

    public static String encrypt(String plainText, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        Key key = generateKey(secretKey);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encryptedBytes);
    }

    public static String decrypt(String encryptedText, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        Key key = generateKey(secretKey);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(hexToBytes(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static Key generateKey(String secretKey) throws Exception {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes(StandardCharsets.UTF_8));
        return keyFactory.generateSecret(desKeySpec);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    public static void main(String[] args) {
        try {
            String secretKey = "12345678"; // 8 bytes key
            String originalText = "Hello, DES Encryption!";

            String encryptedText = encrypt(originalText, secretKey);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
