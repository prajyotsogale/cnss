import math

# Returns gcd of a and b
def gcd(a, h):
    while True:
        temp = a % h
        if temp == 0:
            return h
        a = h
        h = temp

# Two random prime numbers
p = 3
q = 7

# First part of the public key: n
n = p * q

# Finding the other part of the public key (e stands for encrypt)
e = 2
phi = (p - 1) * (q - 1)

while e < phi:
    # e must be coprime to phi and smaller than phi
    if gcd(e, phi) == 1:
        break
    else:
        e += 1

# Private key (d stands for decrypt)
# choosing d such that it satisfies d * e = 1 + k * totient
k = 2  # A constant value
d = (1 + (k * phi)) // e

# Message to be encrypted
msg = 20
print("Message data =", msg)

# Encryption: c = (msg ^ e) % n
c = pow(msg, e, n)
print("Encrypted data =", c)

# Decryption: m = (c ^ d) % n
m = pow(c, d, n)
print("Original Message Sent =", m)
