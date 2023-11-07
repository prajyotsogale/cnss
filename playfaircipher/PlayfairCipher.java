import java.util.Scanner;

public class PlayfairCipher {

    public static String prepareKey(String key) {
        key = key.replaceAll("\\s", "").toUpperCase();
        StringBuilder preparedKey = new StringBuilder();

        for (char c : key.toCharArray()) {
            if (Character.isLetter(c) && preparedKey.indexOf(String.valueOf(c)) == -1) {
                preparedKey.append(c);
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && preparedKey.indexOf(String.valueOf(c)) == -1) {
                preparedKey.append(c);
            }
        }

        return preparedKey.toString();
    }

    public static char[][] createMatrix(String key) {
        String preparedKey = prepareKey(key);
        char[][] matrix = new char[5][5];
        int index = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                matrix[row][col] = preparedKey.charAt(index);
                index++;
            }
        }

        return matrix;
    }

    public static String playfairCipherEncrypt(String plaintext, String key) {
        char[][] matrix = createMatrix(key);
        StringBuilder encryptedText = new StringBuilder();
        plaintext = plaintext.replaceAll("\\s", "").toUpperCase();
        
        for (int i = 0; i < plaintext.length(); i += 2) {
            char c1 = plaintext.charAt(i);
            char c2 = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

            int[] position1 = new int[2];
            int[] position2 = new int[2];

            findPositions(matrix, c1, position1);
            findPositions(matrix, c2, position2);

            if (position1[0] == position2[0]) {
                encryptedText.append(matrix[position1[0]][(position1[1] + 1) % 5]);
                encryptedText.append(matrix[position2[0]][(position2[1] + 1) % 5]);
            } else if (position1[1] == position2[1]) {
                encryptedText.append(matrix[(position1[0] + 1) % 5][position1[1]]);
                encryptedText.append(matrix[(position2[0] + 1) % 5][position2[1]]);
            } else {
                encryptedText.append(matrix[position1[0]][position2[1]]);
                encryptedText.append(matrix[position2[0]][position1[1]]);
            }
        }

        return encryptedText.toString();
    }

    public static void findPositions(char[][] matrix, char c, int[] position) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (matrix[row][col] == c) {
                    position[0] = row;
                    position[1] = col;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String encryptedText = playfairCipherEncrypt(plaintext, key);
        System.out.println("Encrypted: " + encryptedText);
    }
}
