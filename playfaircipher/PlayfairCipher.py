class PlayfairCipher:
    def __init__(self, key):
        self.key_matrix = self.initialize_key_matrix(self.prepare_key(key))

    def initialize_key_matrix(self, key):
        key_matrix = [['' for _ in range(5)] for _ in range(5)]
        index = 0
        used_chars = [False] * 26

        for i in range(5):
            for j in range(5):
                while used_chars[ord(key[index]) - ord('A')]:
                    index += 1
                key_matrix[i][j] = key[index]
                used_chars[ord(key[index]) - ord('A')] = True
                index += 1

        return key_matrix

    def prepare_key(self, key):
        key = key.upper()
        key = ''.join(ch for ch in key if ch.isalpha())
        key = key.replace('J', 'I')
        key_set = set(key)

        for ch in 'ABCDEFGHIKLMNOPQRSTUVWXYZ':
            if ch != 'J' and ch not in key_set:
                key += ch

        return key

    def find_char(self, ch):
        if ch == 'J':
            ch = 'I'

        for i in range(5):
            for j in range(5):
                if self.key_matrix[i][j] == ch:
                    return i, j

    def encrypt_digraph(self, digraph):
        pos1 = self.find_char(digraph[0])
        pos2 = self.find_char(digraph[1])

        row1, col1 = pos1
        row2, col2 = pos2

        if row1 == row2:
            col1 = (col1 + 1) % 5
            col2 = (col2 + 1) % 5
        elif col1 == col2:
            row1 = (row1 + 1) % 5
            row2 = (row2 + 1) % 5
        else:
            col1, col2 = col2, col1

        return self.key_matrix[row1][col1] + self.key_matrix[row2][col2]

    def adjust_message(self, message):
        message = message.upper()
        message = ''.join(ch for ch in message if ch.isalpha())
        message = message.replace('J', 'I')

        for i in range(0, len(message) - 1, 2):
            if message[i] == message[i + 1]:
                message = message[:i + 1] + 'X' + message[i + 1:]

        if len(message) % 2 != 0:
            message += 'X'

        return message

    def encrypt(self, message):
        message = self.adjust_message(message)
        cipher_text = ''

        for i in range(0, len(message), 2):
            cipher_text += self.encrypt_digraph(message[i:i + 2])

        return cipher_text


# Example
key = input("Enter the key: ")
message = input("Enter the message: ")

playfair = PlayfairCipher(key)
encrypted_message = playfair.encrypt(message)

print("Original Message:", message)
print("Encrypted Message:", encrypted_message)