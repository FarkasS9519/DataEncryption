package com.alewo.sha;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAEncrypt {


    /**
     * Usage: Encode strings, which will be not displayed later (like passwords).
     * If we want to check if the user inserted the correct password, we have to encode the given pw, and comapre it
     * to the stored hash.
     *
     * Before the real encoding, call this method with another string (e.g. the username, user_id), and add that hash
     * to the original string.
     *
     * @example
     * username: pah1230
     * password: pahpah
     * salt generated from username: 897eac96bb310c53b24286043c023563708a2838449f3bd4cc51f9748f7833a2
     * generated hash: 696fc516953fdd5918f07a41ba8899d74e628f5f13ce7c7c1cce1ebdbf5716e7
     *
     * @param string_to_encode = a string to encode using SHA-256 algorithm
     * @return = a unique hash, which is irreversible.
     */
    public static String encodeString(String string_to_encode) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(string_to_encode.getBytes(StandardCharsets.UTF_8));
            return byteToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error while trying to encode string value using SHA.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts a byte array into hexadecimal string.
     *
     * @param hash = hashed string bytes
     * @return = byte array converted to hex and appended into a single string
     */
    private static String byteToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
