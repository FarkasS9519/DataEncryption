package com.alewo.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESEncrypt {

    /**
     * Encrypts the given string, using the given secret.
     *
     * @example
     * username: pah1230
     * secret: 696fc516953fdd5918f07a41ba8899d74e628f5f13ce7c7c1cce1ebdbf5716e7
     * encrypted username: UkMSiJRLZ8Ruyvw1tcF6rw==
     *
     * @param strToEncrypt = String to be encrypted. Email addresses, usernames, etc.
     * @param secret = The secret key is going to be hashed, using SHA-1 algorithm. {@link #setKey(String)}
     * @return = The encrypted string.
     */
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            SecretKeySpec secretKey = setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.err.println("Error while trying to encrypt string using AES.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Generates hash from the secret using SHA-1
     *
     * @param secret = The secret, which was given in {@link #encrypt(String, String)} and {@link AESDecrypt#decrypt(String, String)}
     * @return = SecretKeySpec generated from the hash of the secret
     */
    static SecretKeySpec setKey(String secret) {
        MessageDigest sha;
        try {
            byte[] key = secret.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            return new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error while trying to generate secret key for AES.");
            e.printStackTrace();
        }
        return null;
    }
}
