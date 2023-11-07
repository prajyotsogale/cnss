#include <iostream>
#include <openssl/hmac.h>
#include <openssl/sha.h>
#include <cstring>
#include <iomanip>
#include <sstream>

std::string calculate_hmac(const std::string& message, const std::string& key) {
    // Convert the key and message to bytes
    const unsigned char* key_bytes = reinterpret_cast<const unsigned char*>(key.c_str());
    const unsigned char* message_bytes = reinterpret_cast<const unsigned char*>(message.c_str());

    unsigned char hmac_digest[SHA256_DIGEST_LENGTH];

    // Calculate the HMAC using SHA-256
    HMAC_CTX* hmac_ctx = HMAC_CTX_new();
    HMAC_Init_ex(hmac_ctx, key_bytes, static_cast<int>(strlen(reinterpret_cast<const char*>(key_bytes))), EVP_sha256(), nullptr);
    HMAC_Update(hmac_ctx, message_bytes, strlen(reinterpret_cast<const char*>(message_bytes)));
    HMAC_Final(hmac_ctx, hmac_digest, nullptr);
    HMAC_CTX_free(hmac_ctx);

    // Convert the result to a hexadecimal string
    std::stringstream ss;
    for (int i = 0; i < SHA256_DIGEST_LENGTH; ++i) {
        ss << std::hex << std::setw(2) << std::setfill('0') << static_cast<int>(hmac_digest[i]);
    }

    return ss.str();
}

int main() {
    // Replace this key with a secure and unique key for your application
    std::string secret_key = "yourSecretKey";
    std::string message = "Hello, User!";

    // Calculate HMAC for the message using the secret key
    std::string hmac_result = calculate_hmac(message, secret_key);

    // Display the results
    std::cout << "Original Message: " << message << std::endl;
    std::cout << "HMAC: " << hmac_result << std::endl;

    return 0;
}
