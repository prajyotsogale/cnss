#include <iostream>
#include <string>

std::string vigenereCipherEncrypt(const std::string& plaintext, const std::string& key) {
    std::string encryptedText;
    int keyLength = key.length();
    
    for (int i = 0; i < plaintext.length(); i++) {
        char plainChar = plaintext[i];
        char keyChar = key[i % keyLength];
        
        if (isalpha(plainChar)) {
            char base = isupper(plainChar) ? 'A' : 'a';
            char encryptedChar = ((plainChar - base + keyChar - 'A') % 26) + base;
            encryptedText += encryptedChar;
        } else {
            encryptedText += plainChar;  // Keep non-alphabet characters as is.
        }
    }
    
    return encryptedText;
}

int main() {
    std::string plaintext;
    std::string key;
    
    std::cout << "Enter the plaintext: ";
    std::getline(std::cin, plaintext);
    
    std::cout << "Enter the Vigenere key: ";
    std::cin >> key;

    std::string encryptedText = vigenereCipherEncrypt(plaintext, key);
    std::cout << "Encrypted: " << encryptedText << std::endl;

    return 0;
}
