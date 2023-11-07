def vigenere_cipher_encrypt(plaintext, key):
    encrypted_text = []
    key_length = len(key)
    
    for i, char in enumerate(plaintext):
        if char.isalpha():
            key_char = key[i % key_length]
            shift = ord(key_char) - ord('A' if key_char.isupper() else 'a')
            if char.isupper():
                encrypted_char = chr((ord(char) - ord('A') + shift) % 26 + ord('A'))
            else:
                encrypted_char = chr((ord(char) - ord('a') + shift) % 26 + ord('a'))
            encrypted_text.append(encrypted_char)
        else:
            encrypted_text.append(char)  # Keep non-alphabet characters as is.
    
    return ''.join(encrypted_text)

plaintext = input("Enter the plaintext: ")
key = input("Enter the Vigenere key: ")

encrypted_text = vigenere_cipher_encrypt(plaintext, key)
print("Encrypted:", encrypted_text)
