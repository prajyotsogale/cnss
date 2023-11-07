def encrypt_caesar_cipher(text, shift):
    result = ""
    for char in text:
        if char.isalpha():
            base = ord('A') if char.isupper() else ord('a')
            result += chr((ord(char) - base + shift) % 26 + base)
        else:
            result += char
    return result

def decrypt_caesar_cipher(text, shift):
    return encrypt_caesar_cipher(text, 26 - shift)

plaintext = input("Enter the plaintext: ")
shift = int(input("Enter the shift value: "))

encrypted = encrypt_caesar_cipher(plaintext, shift)
decrypted = decrypt_caesar_cipher(encrypted, shift)

print("Original:", plaintext)
print("Encrypted:", encrypted)
print("Decrypted:", decrypted)
