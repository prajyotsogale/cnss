import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class DynamicHMACExample {

    public static String calculateHMAC(String message, String key) {
        try {
            // Create a key for the HMAC algorithm
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");

            // Get an instance of the HMAC SHA-256 algorithm
            Mac mac = Mac.getInstance("HmacSHA256");

            // Initialize the Mac with the key
            mac.init(secretKeySpec);

            // Calculate the HMAC of the message
            byte[] hmacBytes = mac.doFinal(message.getBytes());

            // Encode the result in base64 and return as a string
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your secret key: ");
        String secretKey = scanner.nextLine();

        System.out.print("Enter the message: ");
        String message = scanner.nextLine();

        // Calculate HMAC for the message using the provided secret key
        String hmac = calculateHMAC(message, secretKey);

        // Display the results
        System.out.println("Original Message: " + message);
        System.out.println("HMAC: " + hmac);
    }
}
