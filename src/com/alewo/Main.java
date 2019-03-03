package com.alewo;

import com.alewo.aes.AESDecrypt;
import com.alewo.aes.AESEncrypt;
import com.alewo.sha.SHAEncrypt;

public class Main {

    public static void main(String[] args) {
        String username = "pah1230";
        String password = "pahpah";

        String salt = SHAEncrypt.encodeString(username);
        System.out.println("Salt:" + salt);
        password = SHAEncrypt.encodeString(password + salt);
        System.out.println("Hashed password: " + password);

        String encryptedUsername = AESEncrypt.encrypt(username, password);
        System.out.println("Encrypted username: " + encryptedUsername);
        System.out.println("Decrypted username: " + AESDecrypt.decrypt(encryptedUsername, password));
    }
}
