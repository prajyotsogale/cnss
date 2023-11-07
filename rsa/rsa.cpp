#include <iostream>
#include <cmath>
#include <cstdlib>
#include <ctime>

int gcd(int a, int h) {
    int temp;
    while (1) {
        temp = a % h;
        if (temp == 0)
            return h;
        a = h;
        h = temp;
    }
}

int main() {
    // Input two prime numbers p and q
    int p, q;
    std::cout << "Enter the value of p (a prime number): ";
    std::cin >> p;
    std::cout << "Enter the value of q (a different prime number): ";
    std::cin >> q;

    if (p == q) {
        std::cerr << "p and q must be different prime numbers." << std::endl;
        return 1;
    }

    // First part of public key
    int n = p * q;
    // Finding other part of public key (e for encryption)
    int e = 2;
    int phi = (p - 1) * (q - 1);

    while (e < phi) {
        // e must be co-prime to phi and smaller than phi
        if (gcd(e, phi) == 1)
            break;
        else
            e++;
    }

    // Private key (d for decryption)
    // Choosing d such that it satisfies d * e = 1 + k * phi
    int k = 2; // A constant value
    int d = (1 + (k * phi)) / e;

    // Message to be encrypted
    double msg = 20;

    std::cout << "Message data = " << msg << std::endl;

    // Encryption c = (msg^e) % n
    int c = static_cast<int>(std::pow(msg, e));
    c = c % n;

    std::cout << "Encrypted data = " << c << std::endl;

    // Decryption m = (c^d) % n
    int m = static_cast<int>(std::pow(c, d));
    m = m % n;

    std::cout << "Original Message Sent = " << m << std::endl;

    return 0;
}
