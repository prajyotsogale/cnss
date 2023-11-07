
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class AESExample {

    public static void main(String[] args) throws Exception {
        String message = "Hello, AES Encryption!";
        String key = "thisisaeskey1234"; // 16 bytes key

        byte[] encrypted = encrypt(message, key);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encrypted));

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Message: " + decrypted);
    }

    public static byte[] encrypt(String plainText, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] iv = new byte[16];
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParams);

        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return encrypted;
    }

    public static String decrypt(byte[] cipherText, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] iv = new byte[16];
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParams);

        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return new String(decryptedBytes, "UTF-8");
    }
}
