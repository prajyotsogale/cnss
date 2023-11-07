from base64 import b64encode, b64decode
import hashlib
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

class AESCipher(object):
    def __init__(self, key):
        self.block_size = AES.block_size
        self.key = hashlib.sha256(key.encode()).digest()



    def encrypt(plain_text, key):
        private_key = hashlib.sha256(key.encode("utf-8")).digest()
        plain_text = pad(plain_text)
        print("After padding:", plain_text)
        iv = Random.new().read(self.block_size)
        cipher = AES.new(self.key, AES.MODE_CBC, iv)
        encrypted_text = cipher.encrypt(plain_text.encode())
        return b64encode(iv + encrypted_text).decode("utf-8")

    def decrypt(encrypted_msg, key):
        private_key = hashlib.sha256(key.encode("utf-8")).digest()
        encrypted_msg = base64.b64decode(encrypted_msg)
        iv = encrypted_msg[:16]
        cipher = AES.new(private_key, AES.MODE_CBC, iv)
        return unpad(cipher.decrypt(encrypted_msg[16:]))
    
    def pad(self, plain_text):
        number_of_bytes_to_pad = self.block_size - len(plain_text) % self.block_size
        ascii_string = chr(number_of_bytes_to_pad)
        padding_str = number_of_bytes_to_pad * ascii_string
        padded_plain_text = plain_text + padding_str
        return padded_plain_text

    @staticmethod
    def unpad(plain_text):
        last_character = plain_text[len(plain_text) - 1:]
        return plain_text[:-ord(last_character)]

    plain_text = input("Enter message to encrypt: ")
    key = input("Enter encryption key: ")

    encrypted_msg = encrypt(plain_text, key)
    print("Encrypted Message:", encrypted_msg)

    decrypted_msg = decrypt(encrypted_msg, key)
    print("Decrypted Message:", decrypted_msg.decode('utf-8'))